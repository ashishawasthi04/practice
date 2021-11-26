package com.practice;

import java.util.*;

class SkyLine {
    static class Block {
        int x, height;
        boolean isStart;
        Block(int x, int height, boolean isStart) {
            this.x = x;
            this.height = height;
            this.isStart = isStart;
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int length = buildings.length;
        List<List<Integer>> res = new ArrayList<>();
        Comparator<Block> comparator = (b1, b2) -> {
            if (b1.x != b2.x) return b1.x - b2.x;
            else return (b1.isStart ? -b1.height : b1.height) - (b2.isStart ? -b2.height : b2.height);
        };
        Block[] blocks = new Block[2 * length];
        TreeMap<Integer, Integer> hQueue = new TreeMap<>();
        int index = 0;
        for (int[] building : buildings) {
            blocks[index++] = new Block(building[0], building[2], true);
            blocks[index++] = new Block(building[1], building[2], false);
        }
        Arrays.sort(blocks, comparator);
        hQueue.put(0, 1);
        int prevMaxHeight = 0;
        for (Block block : blocks) {
            if (block.isStart) {
                hQueue.compute(block.height, (k, v) -> v == null ? 1 : v + 1);
            } else {
                hQueue.compute(block.height, (k, v) -> v == 1 ? null : v - 1);
            }
            int curMaxHeight = hQueue.lastKey();
            if (prevMaxHeight != curMaxHeight) {
                res.add(Arrays.asList(block.x, hQueue.lastKey()));
            }
            prevMaxHeight = curMaxHeight;
        }
        return res;
    }
}
