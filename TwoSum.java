// Brute Force Approach:
// Using Nested for-loops: TC: O(n^2), SC: O(1)
// Form each possible pair and check if its sum == target.

// Optimized Approach 1:
// Using HashMap: TC: O(n), SC: O(n)
// Create HashMap<Number, Index>
// At each number in nums[]
// If target-num is present in HashMap, return array of indices
// Else put the current num and its index in HashMap
// Return [] in the end as never returned before

// Optimized Approach 2:
// Using 2 pointer approach: TC: O(nlogn), SC: O(n)
// Create pairs array <idx, val> so that you don't lose real index of each element even after sorting.
// Sort the pairs array, so that you can apply 2-pointer approach on it as follows:
// Set 2 pointers l at index 0 and r at last index, to get current pair.
// Get current pair's sum, and return their indices if sum==target
// Else if sum<target, l++
// else r--
// Continue the above until l<r
// Return [] in the end as never returned before

// Logic:
// Why only l++ and not r++, when sum<target?
// If we do r++ then sum will decrease further as the array is sorted, whereas we want to increase the sum.

// Why only r-- and not l--, when sum>target?
// If we do l-- then sum will increase further as the array is sorted, whereas we want to decrease the sum.

// Why not l<=r, why l<r?
// Because we want the indices of the pairs to be distinct.

// How did we come up with this approach?
// By observing Brute-Force.
// We see, in brute-force it goes to O(n^2) because we use nested-for-loop.
// We need to use nested-for-loop, because at each element we are not sure whether the other number for the pair will be in right or left.
// This happens because the array is not sorted. Once we sort it, then we can keep both pointers at extreme ends and we are sure that:
// if sum < target: r--
// else if sum > target: l++
// else: found sum!

class Solution {
    static class Pair {
        int val, idx;

        public Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;

        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(i, nums[i]);
        }

        Arrays.sort(pairs, Comparator.comparingInt(o -> o.val));

        int l = 0, r = n - 1;
        while (l < r) {
            int sum = pairs[l].val + pairs[r].val;
            if (sum == target) {
                return new int[] { pairs[l].idx, pairs[r].idx };
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[] {};
    }
}