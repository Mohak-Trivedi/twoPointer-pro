// Problem:
// https://www.geeksforgeeks.org/longest-subarray-sum-elements-atmost-k/

// Brute Force Approach: TC: O(N^2), SC: O(1)
// Calculate sum for each subarray having sum <= k, and keep track of max length.
// No need to look for subarrays starting from next i if j reach n for current i, as the next subarrays would be shorter only.
// public static int atMostSum(int[] arr, int n, int k) {
//     int maxLen = 0;
//     int j;
//     for(int i = 0;i < n;i++) {
//         int sum = arr[i]; // sum of subarray starting from index i
//         for(j = i;j < n;) {
//             if(sum <= k) {
//                 j++;
//                 if(j < n) {
//                     sum += arr[j];
//                 }
//             } else {
//                 int len = j - i;
//                 maxLen = Math.max(maxLen, len);
//                 break;
//             }
//         }
//         if(j > n-1) {
//             int len = j - i;
//             maxLen = Math.max(maxLen, len);
//             return maxLen;
//         }
//     }
    
//     return maxLen;
// }

// Optimized Approach: TC: O(N), SC: O(1)
// Same approach as above, but when you start with next s start subarray, instead of exploring from entire s to end
// i.e. totally from start, directly start exploring from previous e.
// Logic:
// [prev s...e] had sum > k, but [prev s...e-1] had sum <= k
// so [new s...prev e-1] will have sum <= k. But, it will ofc be shorter than [prev s ... e],
// so just start with [next s ... e]
public static int atMostSum(int[] arr, int n, int k) {
    int maxLen = 0;
    int j = 0;
    int sum = arr[0];
    for(int i = 0;i < n;i++) {
        for(;j < n;) {
            if(sum <= k) {
                j++;
                if(j < n) {
                    sum += arr[j];
                }
            } else {
                int len = j - i;
                maxLen = Math.max(maxLen, len);
                sum -= arr[i];
                break;
            }
        }
        if(j > n-1) {
            int len = j - i;
            maxLen = Math.max(maxLen, len);
            return maxLen;
        }
    }
    
    return maxLen;
}