// Problem: https://leetcode.com/problems/move-zeroes/description/

// Brute Force: TC: O(N^2), SC: O(1)
// Use Bubble Sort to sort the array in descending order. 
// But, this breaks the rule that the "relative order of the non-zero elements must not change".

// Better Approach: TC: O(N), SC: O(N)
// Create a new array. Iterate over given array and store each non-zero element in the new array.
// Fill the empty positions of the new array with zeroes.
// But, this breaks the rule that we need to do it in-place.

// Optimized Approach: TC: O(N), SC: O(N)
// Use 2-pointers.
// i: index of first 0 (as this would the be the first to be swapped with current non-zero detected)
// j: index of current element
// Iterate over entire array using j.
// If current element is 0 simply move ahead i.e. j++
// If current element is non-0, swap with first 0 i.e. swap arr[j] and arr[i], then i++ (as current i is not 0) and j++ to check next element.

// How are we sure that i++ will always lead us to next first 0?
// Because the next element will always be 0.
// If next element after current i was 0 then ofc i++ will give the next first 0.
// If next element was not 0, it would have been already swapped with 0.
class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                // Swap this current non-0 with first 0
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                // Next 0 element
                i++;
            }

            j++;
        }
        return;
    }
}
