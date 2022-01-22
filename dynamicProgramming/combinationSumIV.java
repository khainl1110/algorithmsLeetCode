// link: https://leetcode.com/problems/combination-sum-iv/
class Solution {
    HashMap<Integer, Integer> hashMap = new HashMap<>();
    public int combinationSum4(int[] nums, int target) {
        // define base case
        if(target == 0)
            return 1;
        if(target <0)
            return -1;
        
        // check cache
        if(hashMap.containsKey(target))
            return hashMap.get(target);
        
        int total = 0;
        // define recursion
        for(int i = 0; i < nums.length; i++) {
            int count = combinationSum4(nums, target - nums[i]);
            if(count != -1)
                // because there might be numerous way to react count
                total+=count;
        }
        
        // set cache
        hashMap.put(target, total);
        
        return total;
    }
    
}
