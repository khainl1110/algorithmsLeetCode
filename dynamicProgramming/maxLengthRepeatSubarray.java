//https://leetcode.com/problems/maximum-length-of-repeated-subarray/
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        // bottom up 
        /*
            nums1

                1   2   3   2   1 
   nums2    3   0   0   1   0   0 
            2   0   1   0   2   0 
            1   1   0   0   0   3   
            4   0   0   0   0   0
            7   0   0   0   0   0
        
        
        */
        int total = 0 ;
        int[][] dp = new int[nums1.length][nums2.length];
        
        for(int i = 0; i < nums1.length; i++) {
            for(int j = 0; j < nums2.length; j++) {
                int val = 0;
                if(nums1[i] == nums2[j]) {
                    val = 1;
                    if(i > 0 && j >0) {
                        val += dp[i-1][j-1];
                    }
                }
                
                dp[i][j] = val;
                //System.out.println(nums1[i] + " " + nums2[j] + " " + dp[i][j]);
                total = Math.max(total, val);
            }
        }
        return total;
    }
}
