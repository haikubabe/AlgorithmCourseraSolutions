package graph_search.exercises.hash_table;

import java.util.Arrays;
import java.util.Hashtable;

public class TwoSum
{
    private static boolean binarySearch(int[] nums, int n, int low, int high) {
        while (low <= high) {
            int mid = (low+high)/2;
            if (nums[mid] == n) {
                return true;
            }
            if (n < nums[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return false;
    }

    /**
     * naive approach
     * @param nums
     * @param target
     * @return
     */
    private static boolean twoSum1st(int[] nums, int target) {
        for (int i=0;i<nums.length-1;i++) {
            for (int j=i+1;j<nums.length;j++) {
                if (nums[i] + nums[j] == target) {
                   return true;
                }
            }
        }
        return false;
    }

    /**
     * using binary search
     * @param nums
     * @param target
     * @return
     */
    private static boolean twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++) {
            boolean found = binarySearch(nums, target-nums[i], i+1, nums.length-1);
            if (found) {
                return true;
            }
        }
        return false;
    }

    /**
     * using hashtable
     * @param nums
     * @param target
     * @return
     */
    private static boolean twoSum2nd(int[] nums, int target) {
        Hashtable<Integer,Integer> hashtable = new Hashtable<>();
        for (int i=0;i<nums.length;i++) {
            int x = nums[i];
            int y = target-x;
            if (hashtable.containsKey(y)) {
                return true;
            }
            hashtable.put(x,i);
        }
        return false;
    }

    public static void main(String[] args)
    {
        int[] nums = {5,2,3};
//        int[] nums = {0,4,3,0};
        System.out.println(twoSum(nums, 6));
    }
}
