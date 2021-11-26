package com.practice;

/*

This problem was asked by Amazon.

Implement a bit array.

A bit array is a space efficient array that holds a value of 1 or 0 at each index.

init(size): initialize the array with size
set(i, val): updates index at i with val where val is either 1 or 0.
get(i): gets the value at index i.

https://www.dailycodingproblem.com/solution/823?token=725527c91d6e022edf3f5023cf920477c0fc474546e9860d736b19f0b59eb3cbff05a8a6
*/

class BitArray {
    private int allOnes = 0xFFFFFFFF;
    private int[] store = null;
    public void init(int size){
        store = new int[size/32 + (size%32 == 0 ? 0 : 1)];
    }

    public void set(int idx, int val){
        int intIdx = idx/32;
        int num = store[intIdx];

        if(val == 0) num &= allOnes - (1 << (32 - idx%32));
        else num |= (1 << (32 - idx%32));

        store[intIdx] = num;
    }

    public int get(int idx){
        int intIdx = idx/32;
        return (store[intIdx] & (1 << (32 - idx%32))) == 0 ? 0 : 1;
    }
}