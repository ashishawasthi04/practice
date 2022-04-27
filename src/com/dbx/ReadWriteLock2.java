package com.dbx;

import java.util.HashMap;
import java.util.Map;

/*
Reentrant
If no reentrant and the writer tries to acquire read access before releasing write access, will cause infinite lock. confounded face
Read -> Read
The thread holding the read access should be able to reenter if there is no writer writing.
Write -> Write
Currently holding write lock
Read -> write
if it is the only reader
Write -> read
should always grant for writer
*/
public class ReadWriteLock2 {
    private final Map<Thread, Integer> readingThreads = new HashMap<>();
    private int writeAccesses = 0;
    private int writeRequests = 0;
    private Thread writingThread = null;

    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)) {
            wait();
        }

        readingThreads.put(callingThread,
                (getReadAccessCount(callingThread) + 1));
    }

    private boolean canGrantReadAccess(Thread callingThread) {
        if (isWriter(callingThread) || isReader(callingThread)) return true;
        if (hasWriter()) return false;
        return !hasWriteRequests();
    }

    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        if (!isReader(callingThread)) {
            throw new IllegalMonitorStateException("Calling Thread does not" +
                    " hold a read lock on this ReadWriteLock");
        }
        int accessCount = getReadAccessCount(callingThread);
        if (accessCount == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.put(callingThread, (accessCount - 1));
        }
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        Thread callingThread = Thread.currentThread();
        while (!canGrantWriteAccess(callingThread)) {
            wait();
        }
        writeRequests--;
        writeAccesses++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite() throws InterruptedException {
        if (!isWriter(Thread.currentThread())) {
            throw new IllegalMonitorStateException("Calling Thread does not" +
                    " hold the write lock on this ReadWriteLock");
        }
        writeAccesses--;
        if (writeAccesses == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        if (isOnlyReader(callingThread)) return true;
        if (hasReaders()) return false;
        if (writingThread == null) return true;
        return isWriter(callingThread);
    }

    private int getReadAccessCount(Thread callingThread) {
        return readingThreads.getOrDefault(callingThread, 0);
    }

    private boolean hasReaders() {
        return !readingThreads.isEmpty();
    }

    private boolean isReader(Thread callingThread) {
        return readingThreads.containsKey(callingThread);
    }

    private boolean isOnlyReader(Thread callingThread) {
        return readingThreads.size() == 1 &&
                readingThreads.containsKey(callingThread);
    }

    private boolean hasWriter() {
        return writingThread != null;
    }

    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }

    private boolean hasWriteRequests() {
        return this.writeRequests > 0;
    }
}

