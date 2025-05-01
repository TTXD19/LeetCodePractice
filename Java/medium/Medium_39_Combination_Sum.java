package Java.medium;

import java.util.ArrayList;
import java.util.List;

public class Medium_39_Combination_Sum {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        helper(candidates, target, 0, new ArrayList<Integer>());
        return res;
    }

    private void helper(int[] candidates, int target, int stIdx, List<Integer> curList) {
        if (target == 0) {
            res.add(curList);
        }

        for (int i = stIdx; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                List<Integer> list = new ArrayList<>(curList);
                list.add(candidates[i]);
                helper(candidates, target - candidates[i], i, list);
            }
        }
    }
}
