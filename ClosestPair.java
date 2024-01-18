// Problem:
// https://www.geeksforgeeks.org/problems/find-the-closest-pair-from-two-arrays4215/1

// Brute-force approach: TC: O(N^2), SC: O(1). We get TLE
// Find sum of all possible pairs. Whenever sum<=target, check if it is the
// closest by checking if sum>closest.

// Optimized approach: TC: O(2 * max(m, n)), SC: O(1). We get TLE
// Using 2-pointers: both pointers at start of both arrays.
// Shouldn't go with such an approach for 2-pointers as it breaks the Golden
// Rule of 2-pointers.
// Golden Rule of 2-pointers: There must never be >1 options for moving the
// pointers. There must be only 1 option to move i and j.
// E.g. in 2sum problem, it was clear: when sum < target: i++, when sum >
// target: move j--.

// import java.io.*;
// import java.util.*;

// // User function Template for Java

// class Solution {
// // Function for finding maximum and value pair
// public static ArrayList<Integer> printClosest (int arr[], int brr[], int n,
// int m, int x) {
// // code here
// ArrayList<Integer> list = new ArrayList<>();

// for(int i = 0, j = 0;i < n && j < m;) {
// int num1 = arr[i];
// int num2 = brr[j];
// int sum = num1 + num2;
// if(sum == x) {
// list.add(num1);
// list.add(num2);
// return list;
// } else if(sum > x) {
// // no matter which pointer you move the sum will increase further
// list.add(0);
// list.add(0);
// return list;
// } else { // sum < x
// if(j+1 < m && i+1 < n)
// {
// int nextSum1 = arr[i] + brr[j+1];
// int nextSum2 = brr[j] + arr[i+1];
// if(nextSum1 <= x && nextSum2 <= x) {
// if(nextSum1 > nextSum2) {
// i++;
// } else {
// j++;
// }
// } else if(nextSum1>x && nextSum2>x) {
// list.add(num1);
// list.add(num2);
// return list;
// } else if(nextSum2 > x) {
// i++;
// } else {
// j++;
// }
// }

// }
// }

// return list;
// }
// }
