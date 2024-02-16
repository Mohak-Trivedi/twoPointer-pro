// Problem: https://www.geeksforgeeks.org/number-subarrays-sum-less-k/

// Brute Force: TC: O(N^2), SC: O(1)
// Maintain count.
// For each sub-array calculate the sum, and whenever it has sum <= k, increment
// count.
// Start with sub-arrays starting at s=0
// Then with sub-arrays starting at s=1
// Then with sub-arrays starting at s=2
// .
// .
// .
// Then with sub-arrays starting at s=n-1
// Start with subarrays starting with next s index as soon as sum > k.

// Optimized Approach: TC: O(N), SC: O(1)
// import java.io.*;

// class GFG {
//     static int countSubarray(int arr[], int n, int k) {
//         int s = 0, e = 0;
//         int sum = arr[s];
//         int totalCount = 0;

//         while (s < n && e < n) {
//             if (s == e) {
//                 if (sum >= k) {
//                     s++;
//                     e--;

//                     if (s < n) {
//                         sum = arr[s];
//                     }
//                 } else {
//                     e++;

//                     if (e < n) {
//                         sum += arr[e];
//                     }
//                 }
//             } else {
//                 if (sum >= k) {
//                     int count = (e - 1) - s + 1;
//                     totalCount += count;

//                     sum -= arr[s];
//                     s++;

//                     sum -= arr[e];
//                     e--;

//                     if (s > e) {
//                         e = s;
//                         sum = arr[s];
//                     }
//                 } else {
//                     e++;

//                     if (e < n) {
//                         sum += arr[e];
//                     }
//                 }
//             }
//         }

//         // in case you came out because s<n was true but j<n was false i.e. discovered
//         // largest subarray [si...n-1] at si, so avoided further s as start subarray as
//         // they'll be shorter only.
//         if ((s + 1) < n) { // further s start sub-arrays are yet to be explored
//             // [si...n-1] had sum <= k, then all the s AFTER si would ofcourse have
//             // subarrays till n-1 as end to be sum <= k.
//             // So, si+1 start, si+2 start, ..., n-1 start subarrays would be considered.
//             // si+1 start -> n-1 - si+1 + 1 subarrays
//             // si+2 start -> n-1 - si+2 + 1 subarrays
//             // ... and so on
//             // So, for l s starts, we will have l + l-1 + l-2 +...+ 1 subarrays = sum of
//             // first l natural nums subarrays = (l*(l+1)) / 2 subarrays
//             int l = ((n - 1) - (s + 1)) + 1;
//             totalCount += (l * (l + 1)) / 2;
//         }

//         return totalCount;
//     }

//     // Driver Code
//     public static void main(String[] args) {
//         int array[] = { 1, 11, 2, 3, 15 };
//         int k = 10;
//         int size = array.length;
//         int count = countSubarray(array, size, k);
//         System.out.println(count);
//     }
// }

// My approach:
import java.io.*;

public class GFG {
    // Function to find the length of the largest subarray
    // having a sum at most k.

    public static void main(String[] args) {
        int[] arr = { 1, 2, 1, 0, 1, 1, 0 };
        int n = arr.length;
        int k = 4;

        // Print the length of the longest
        // subarray with sum at most k.
        System.out.println(atMostSum(arr, n, k));
    }
}