package com.dbx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Input: candidates = [2,3,6,7], target = 7
//Output: [[2,2,3],[7]]
//Explanation:
//2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
//7 is a candidate, and 7 = 7.
//These are the only two combinations.
class CombinationSum {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(candidates, target, new ArrayList<>(), 0);
        return ans;
    }

    public void helper(int[] candidates, int target, List<Integer> res, int idx) {
        if (target < 0) return;
        if (target == 0) {
            ans.add(new ArrayList<>(res));
            return;
        }
        while (idx < candidates.length && candidates[idx] <= target) {
            res.add(candidates[idx]);
            helper(candidates, target - candidates[idx], res, idx);
            res.remove(res.size() - 1);
            idx++;
        }
    }
}

