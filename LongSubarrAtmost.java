// Problem:
// https://www.geeksforgeeks.org/longest-subarray-sum-elements-atmost-k/

// Brute Force Approach: TC: O(N^2), SC: O(1)
// Calculate sum for each subarray having sum <= k, and keep track of max length.
// No need to look for subarrays starting from next i if j reach n for current i, as the next subarrays would be shorter only.
// public static int atMostSum(int[] arr, int n, int k) {
//     int maxLen = 0;

//     for (int s = 0; s < n; s++) {
//         int sum = 0;
//         int len = 0;

//         int e = s;
//         while (e < n) {
//             sum += arr[e];
//             if (sum <= k) {
//                 len++;
//                 e++;
//             } else {
//                 break;
//             }
//         }

//         if (e == n) {
//             return len;
//         } else {
//             maxLen = Math.max(maxLen, len);
//         }
//     }

//     return maxLen;
// }

// Optimized Approach: TC: O(N), SC: O(1)
// Same approach as above, but when you start with next s start subarray, instead of exploring from entire s to end
// i.e. totally from start, directly start exploring from previous e.
// Logic:
// [prev s...e] had sum > k, but [prev s...e-1] had sum <= k
// so [new s...prev e-1] will ofc have sum <= k. 
// so just start with [next s ... e] excluding arr[e] from sum as it causes >k (and ofc excluding arr[s] as it wont be part of new subarray), so sum = sum - arr[s] - arr[e]
// also len-- before as the prev s will not be part of subarray.
public static int atMostSum(int[] arr, int n, int k) {
    int maxLen = 0;
    int e = 0;
    int sum = 0;
    int len = 0;
    for (int s = 0; s < n; s++) {
        while (e < n) {
            sum += arr[e];
            if (sum <= k) {
                len++;
                e++;
            } else {
                break;
            }
        }

        if (e == n) {
            return len;
        } else {
            maxLen = Math.max(maxLen, len);
            sum = sum - arr[s] - arr[e];
            len--;
        }
    }

    return maxLen;
}