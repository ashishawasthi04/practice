package com.dbx;

import java.util.BitSet;

/*
If no need to maintain an ordering allocation, can use just one set called available to achieve the above.
Allocate is get and remove the first element in the set.
(available.iterator().first())

BitSet approach:
Space is much efficient: O(c), won’t say it is O(1) because we still need max_size number of bits
Time: worst case O(n) in searching for next available id
*/
public class Allocator2{
    private final int MAX_ID;
    private BitSet bitSet;
    private int nextAvailable;

    public Allocator2(int maxId) {
        this.MAX_ID = maxId;
        this.bitSet = new BitSet(maxId);
        this.nextAvailable = 0;
    }

    public int allocate() {
        if(nextAvailable==MAX_ID) return -1;
        int num = nextAvailable;
        bitSet.set(num);
        nextAvailable = bitSet.nextClearBit(num);
        return num;
    }

    public void release(int id) {
        if(id<0 || id>=MAX_ID) return;
        if(bitSet.get(id)) {
            bitSet.clear(id);
            nextAvailable = Math.min(nextAvailable, id);
        }
    }

    public boolean check(int id) {
        if(id<0 || id>=MAX_ID) return false;
        return !bitSet.get(id);
    }
}
