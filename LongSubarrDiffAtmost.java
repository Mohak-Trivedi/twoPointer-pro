// Problem:
// https://leetcode.com/discuss/interview-question/3549864/Google-or-sliding-window

/*
 * Understand the problem:
 * Given field of view angle (only that which fits in this angle degree can be
 * visible in camera) and the angle at which each skyscraper is located, we need
 * to find what is the max number of skyscrapers we can capture.
 * E.g.:
 * Input:
 * arr = [5, 9, 20, 22, 28, 35, 60, 350, 358, 359, 360]
 * field of view = 30
 * Output:
 * 6
 * Explanation:
 * The 6 skyscrapers at angles [5, 9, 20, 22, 28, 35] i.e. sub-array [0..5] are
 * the most number of skyscrapers that can fit in 30 degree angle field of
 * vision.
 * Some other skyscrapers that fit in the field of vision are:
 * Smaller subarrays within the above one
 * And
 * [35, 60]
 * [350, 358, 359, 360]
 * But all of these were containing lesser skyscrapers.
 * 
 * So, we can conclude:
 * Number of skyscrapers in the field of view angle = Length of subarray whose
 * abs(max element - min element) <= field of vision angle
 * 
 * Max. Number of skyscrapers in the field of view angle = Length of longest subarray
 * whose abs(max element - min element) <= field of vision angle
 * 
 * Hence, the question is actually:
 * Given a sorted array of integers and an integer k, find the longest subarray
 * having absolute difference of its last and first elements <= k
 */

// Brute force approach: TC: O(N^2), SC: O(1)
// For each possible subarray find the abs(max - min) i.e. no. of skyscrapers
// and check get its length if abs(max - min) <= fov (field of vision angle)
// i.e. no. of skyscrapers in field of vision and keep track of max length i.e.
// max no. of skyscrapers in field of vision.

// Better approach: TC: O(N^2), SC: O(1)
// Find the abs(max - min) only for those subarrays whose abs(max - min) <= fov

// Optimized approach: TC: O(N), SC: O(N)
// Same as Longest Subarray with Sum at most K, but calculate difference of arr[e] and arr[s] for current subarray instead of updating sum with arr[e]
// [prev s...e] had diff > k, but [prev s...e-1] had diff <= k
// so [new s...prev e-1] will ofc have diff <= k. 
// so just start with [next s ... e] excluding arr[e] from diff as it causes >k (and ofc excluding arr[s] as it wont be part of new subarray), so diff = arr[e - 1] - arr[s + 1];
// also len-- before as the prev s will not be part of subarray.
public static int atMostDiff(int[] arr, int n, int fov) {
    int maxLen = 0;
    int e = 0;
    int diff = 0;
    int len = 0;

    for (int s = 0; s < n; s++) {
        while (e < n) {
            diff = arr[e] - arr[s];
            if (diff <= fov) {
                len++;
                e++;
            } else {
                break;
            }
        }

        if (e == n) {
            maxLen = Math.max(maxLen, len);
            return maxLen;
        } else {
            maxLen = Math.max(maxLen, len);
            if ((s + 1) < n) {
                diff = arr[e - 1] - arr[s + 1];
                len--;
            }
        }
    }

    return maxLen;
}