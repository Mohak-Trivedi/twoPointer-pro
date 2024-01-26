// Problem:
// https://www.geeksforgeeks.org/longest-subarray-sum-elements-atmost-k/

// Brute Force Approach: TC: O(N^2), SC: O(1)
// Find sum of each possible sub-array, and keep track of longest one.
// How?
// Find sum of every unique sub-array that starts from index 0, and update
// longest as encountered
// Find sum of every unique sub-array that starts from index 1, and update
// longest as encountered
// Find sum of every unique sub-array that starts from index 2, and update
// longest as encountered
// .
// .
// .
// Find sum of every unique sub-array that starts from index n-1, and update
// longest as encountered
// int maxLen = 0; // length of longest sub-array having sum <= k encountered
// until now
// for(int s = 0;s < arr.length;s++) {
// int len = 0; // length of longest sub-array starting from s having sum <= k
// encountered until now
// int sum = 0; // sum of current sub-array starting from s and ending at e
// for(int e = s;e < arr.length;e++) {
// sum += arr[e];
// if(sum <= k) {
// len++;
// maxLen = Math.max(maxLen, len);
// }
// }
// }
// return maxLen;

// Better Approach: TC: O(N^2), SC: O(1)
// Instead of finding sum of each possible sub-array, find sum of only those
// sub-arrays whose sum <= k and keep track of longest subarray length.
// public static int atMostSum(int[] arr, int n, int k) {
// int maxLen = 0; // length of longest sub-array having sum <= k encountered
// until now
// for(int s = 0;s < arr.length;s++) {
// int len = 0; // length of longest sub-array starting from s having sum <= k
// encountered until now
// int sum = 0; // sum of current sub-array starting from s and ending at e
// for(int e = s;e < arr.length;e++) {
// if(sum + arr[e] <= k) {
// sum += arr[e];
// len++;
// maxLen = Math.max(maxLen, len);
// if(e+1 == arr.length) { // subarray starting with any of the further sth
// element will be shorter only
// return maxLen;
// }
// } else {
// break;
// }
// }
// }
// return maxLen;
// }

// Optimized Approach: TC: O(N), SC: O(1)
// Same approach as above, but when you start with next s start subarray, instead of exploring from entire s to end
// i.e. totally from start, directly start exploring from previous e - 1.
// Logic:
// [prev s...e] had sum > k, but [prev s...e-1] had sum <= k
// so [new s...prev e-1] will have sum <= k, as its sum for sure <= [prev s...e-1]
// In one line: sum of [s+1...e-1] <= sum of [s...e-1] <= k
public static int atMostSum(int[] arr, int n, int k) {
    int s = 0, e = 0;
    int maxLen = 0;
    int sum = arr[s];
    while(s<n && e<n) { //s>=n -> checked subarray starting from each index, e>=n -> no need to check any further s start subarray as they all would be smaller than the current
        if(s == n) { // sub-array with new s start of length 1
            if(sum > k) {
                // check next s start sub-array sum
                s++;
                e++;
                sum = arr[s]; 
            } else {
                int len = 1;
                maxLen = Math.max(len, maxLen);
                
                // check if current s start subarray length can be increased
                e++;
                if(e < n) {
                    sum += arr[e];
                }
            }
        } else {
            if(sum > k) {
                // move to next s start subarray, as increasing e to check more length is not logical as already sum>k.
                // [s...e] has sum > k, but [s...e-1] has sum <= k (else e wouldn't even have reached here)
                // so [s+1...e-1] will have sum <= k, as its sum for sure <= [s...e-1]
                e--;
                sum -= arr[e];
                s++; // sub-array with next s start as the previous s start has been explored
                sum -= arr[s];
                // no need to check if current length is longest, as it won't be as it is shorter than [s...e-1] 
            } else {
                int len = e - s + 1;
                maxLen = Math.max(len, maxLen);
                
                e++;
                if(e < n) {
                    sum += arr[e];
                }
            }
        }
    }
    
    return maxLen;
}