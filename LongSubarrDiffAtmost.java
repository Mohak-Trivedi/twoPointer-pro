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
 * Max. Number of skyscrapers in the field of view angle = Length of subarray
 * whose abs(max element - min element) <= field of vision angle
 * 
 * Hence, the question is actually:
 * Given a sorted array of integers and an integer k, find the longest subarray
 * having whose absolute difference of its last and first elements <= k
 */

// Brute force approach: TC: O(N^2), SC: O(1)
// For each possible subarray find the abs(max - min) i.e. no. of skyscrapers
// and check get its length if abs(max - min) <= fov (field of vision angle)
// i.e. no. of skyscrapers in field of vision and keep track of max length i.e.
// max no. of skyscrapers in field of vision.

// Better approach: TC: O(N^2), SC: O(1)
// Find the abs(max - min) only for those subarrays whose abs(max - min) <= fov

// Optimized approach:

// Logic:
// We notice a monotonic function:
// abs(max-min) for arr[0...0] <= abs(max-min) for arr[0...1] <= abs(max-min)
// for arr[0...2] <= ... <= abs(max-min) for arr[0...n-2] <= abs(max-min)
// for arr[0...n-1]
// Same applies for sub-arrays starting from 1, 2, 3, ..., n-1.
// So, we are sure how to use the 2 pointers : s and e.
// For subarray of length 1 i.e. when s==e:
// curr subarray abs(max-min) > fov -> No more length to be explored for subarray
// starting with s -> s++ e++
// curr subarray abs(max-min) <= fov -> Check if we can increase length of current
// subarray starting with s -> e++
// For subarray of length > 1 i.e. s!=e:
// curr subarray abs(max-min) > fov -> previous subarray [s ... e-1] was valid
// (without that wouldn't have reached this point), so [s+1 ... e-1] will be valid as 
// well -> s++ e--. But, CAUTION: it might happen s>e after s++ e--, so if(s>e) -> e=s
// curr subarray abs(max-min) <= fov -> Check if we can increase length of current -> e++
/*package whatever //do not write package name here */

import java.io.*;
import java.util.Scanner;

class GFG {

    public static void main(String[] args) {
        int[] arr = { 5, 9, 20, 22, 28, 35, 60, 350, 358, 359, 360 };
        int n = arr.length;
        int k = 30;

        // Print the length of the longest
        // subarray with sum at most k.
        System.out.println(atMostDiff(arr, n, k));
    }

    public static int atMostSum(int[] b, int n, int fov) {

        int s = 0, e = 0;
        int diff = 0;
        int maxLen = 0;
        while (s < n && e < n) { // s>=n -> explored all subarrays, e>=n -> found longest subarray already, no
            // point in trying further s
            if (s == e) {
                // diff = b[e] - b[s];
                if (diff > fov) {
                    s++;
                    e++;

                    // abs(max - min) for next subarray starting with next s
                    if (s < n) {
                        diff = b[e] - b[s];
                    }
                } else {
                    int len = 1;
                    maxLen = Math.max(len, maxLen);

                    e++;
                    if (e < n) {
                        diff = b[e] - b[s];
                    }
                }
            } else {
                // diff = b[e] - b[s];
                if (diff > fov) {
                    s++;
                    e--;

                    // edge case: when prev subarray size 2 and diff > fov, then s++ e-- lead to
                    // this
                    if (s > e) {
                        e = s;
                    }

                    diff = b[e] - b[s];
                } else {
                    int len = e - s + 1;
                    maxLen = Math.max(maxLen, len);

                    e++;
                    if (e < n) {
                        diff = b[e] - b[s];
                    }
                }
            }
        }
    }
}
