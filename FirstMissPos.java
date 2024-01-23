// Problem: https://leetcode.com/problems/first-missing-positive/description/

// Brute Force Approach: TC: O(10^5 * N), SC: O(1)
// We notice constraint: array length can be at max 10^5
// So for each +ve integer from 1 to 10^5 check do linear search in arr[].
// If current number being searched is not found, return that +ve integer value.

// Optimized Approach 1: TC: O(NlogN + 10^5logN), SC: O(N)
// Put elements into TreeSet (logN * N)
// For each +ve integer from 1 to 10^5 check if TreeSet contains it. (logN *
// 10^5)
// If current number being searched is not found, return that +ve integer value.

// Optimized Approach 2: TC: O(NlogN + 10^5logN), SC: O(1)
// Sort the array (NlogN)
// For each +ve integer from 1 to 10^5 check do binary search in arr[].
// If current number being searched is not found, return that +ve integer value.

// But we've been asked to solve in TC: O(N), SC: O(1)

// Optimized Approach 3: TC: O(N), SC: O(1)
// Sort the array using Cyclic Sort. O(N)
// Iterate over the sorted array, at whichever index i you don't have element as
// i+1, return i+1

// Logic:
// In order for the array of size N to not be missing any element, and be
// sorted, we will have array like:
// 1 2 3 4 .... N-1 N -> values
// 0 1 2 3 .... N-2 N-1 -> indices
// i.e. at each index i you must have value i+1.
// Hence, the first missing +ve integer will be found by having a value !=i+1 at
// index i.
// EDGE CASE: In the above case, since all values are present, the 1st missing
// value is N+1 i.e. arr.length+1

// Knowledge:
// But, sorting requires at least O(NlogN), no matter which "comparison-based"
// (the ones we know: merge sort, quick sort, bubble sort, insertion sort, etc.)
// sorting algo you use.
// But, we have a special condition that allows us to know what number must be
// at what index i.e. index i must contain i+1
// In such a situation where we have a condition that enables us to know what
// value must be at what index, we can make use of the "Non-Comparison based
// Sorting Algos" such as Cyclic Sort.

class Solution {
    public int firstMissingPositive(int[] nums) {
        // Sort using Cyclic Sort:
        // Sorted array having no missing +ve int: Each index i must have value i+1
        // Converse: Each value num has correct position as num-1
        // Put each value num at its correct position using swap, and as a result we'll
        // get a sorted array.
        for (int i = 0; i < nums.length;) {
            int num = nums[i];
            int correctPos = num - 1;

            // Ignore (just i++) for numbers not in range 1 to nums.length
            // Ignore (just i++) if number is already in correctPos
            // We use nums[i]!=nums[correctPos] instead of i!=correctPos to determine if num
            // is in correctPos so that we can ignore (just i++) for cases where num is
            // present at incorrect as well as correctPos. e.g.:
            // 1 2 3 1 0
            // 0 1 2 3 4
            // i = 3
            // Here, i!=correctPos but we don't wanna swap, nums[i] and nums[correctPos]
            // because nums[correctPos] is already having its correct value.
            if (num > 0 && num <= nums.length && nums[i] != nums[correctPos]) {
                int temp = nums[correctPos];
                nums[correctPos] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }

        // Now, the index i at which i+1 is not present, return i+1 as that's the 1st
        // missing +ve int
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // If all i have i+1, then the missing +ve int is array length + 1
        return nums.length + 1;
    }
}