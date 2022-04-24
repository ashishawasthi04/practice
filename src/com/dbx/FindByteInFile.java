package com.dbx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FindByteInFile {
    public boolean containBytes(byte[] pattern, byte[] text) {
        for (int i = 0; i <= text.length - pattern.length; i++) {
            int j = 0;
            while (j < pattern.length && pattern[j] == text[i + j]) {
                j++;
            }
            if (j == pattern.length) return true;
        }
        return false;
    }
 /*
 Use rolling hash to check substring match
    - Mod a large prime number to avoid overflow
    - the parameters a and prime number determines the collision rate. We want the slot large enough,
    N2 such that the expected collision for n elements is just 1/N, there are n numbers,
    so total expected collision is n 1/n =1
    - We need to check if this is really the substring match if the hash code is the same
  */

    public boolean containsBytesRollingHash(byte[] pattern, byte[] text) {
        if (text.length < pattern.length)
            return false;

        int m = pattern.length, n = text.length;
        byte[] initialBytes = Arrays.copyOfRange(text, 0, m);
        RollingHash hashFun = new RollingHash(31, initialBytes);

        long patternHashVal = hashFun.hash(pattern);
        for (int i = 0; i <= n - m; i++) {
            if (patternHashVal == hashFun.currHashValue) {
                //need to check byte by byte to ensure
                int j = 0;
                while (j < m && pattern[j] == text[i + j]) j++;
                if (j == m) return true;
            }
            if (i < n - m) {
                hashFun.recompute(text[i], text[i + m]);
            }
        }
        return false;
    }

    public boolean containsBytesFileRollingHash(byte[] pattern, File file) throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] initialBytes = new byte[pattern.length];

            if (fis.read(initialBytes) != pattern.length) return false;

            RollingHash hashFun = new RollingHash(31, initialBytes);
            long patternHashVal = hashFun.hash(pattern);
            if (patternHashVal == hashFun.currHashValue) return true;

            Queue<Byte> window = new LinkedList<>();
            for (byte initialByte : initialBytes) {
                window.offer(initialByte);
            }

            int b;
            while ((b = fis.read()) != -1) {
                System.out.println(b);

                hashFun.recompute(window.poll(), (byte) b);
                window.offer((byte) b);
                if (patternHashVal == hashFun.currHashValue) return true;
            }
        }
        return false;
    }

    static class RollingHash {
        private final int LARGE_PRIME = 105613;
        private final int a;
        private int h = 1;
        private long currHashValue;

        public RollingHash(int a, byte[] initialBytes) {
            this.a = a;
            int WINDOW_LENGTH = initialBytes.length;

            // The value of h would be "pow(a, WINDOW_LENGTH-1)%q
            for (int i = 0; i < WINDOW_LENGTH - 1; i++) {
                //a^n % p = (a^n-1 % p * a%p)%p;
                h = (h * a) % LARGE_PRIME;
            }
            currHashValue = hash(initialBytes);
        }

        public long hash(byte[] bytes) {
            int hashVal = 0;
            for (byte aByte : bytes) {
                hashVal = (a * hashVal + aByte) % LARGE_PRIME;
            }
            return hashVal;
        }

        public void recompute(byte removed, byte incoming) {
            //(a+b) %p = (a%p + b%p) %p
            //(a-b) %p = (a%p - b%p) %p might give negative
            //(a*b) %p = (a%p * b%p) %p
            currHashValue = (a * (currHashValue - (long) removed * h) + incoming) % LARGE_PRIME;
            // We might get negative value of t, converting it to positive
            if (currHashValue < 0)
                currHashValue += LARGE_PRIME;
        }
    }
}
