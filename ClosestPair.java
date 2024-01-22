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

// Efficient Approach: TC: O(max(N, M)), SC: O(1)
// Use 2-pointers as follows:
// Place 1st pointer l at 0th index (smallest value of arr) of arr[], and 2nd
// pointer r at last index(largest value of brr) of brr[].
// Use while(l<n && r>=0)
// For each pair, get distance from x using absolute difference.
// If the current pair's distance is < the least distance until now, update the
// least distance and store the current pair using 2 int vars.
// Then move the pointers on the basis:
// if current pair sum == x -> break from while (100% sure current pair is
// closest)
// else if current pair sum > x -> r--
// else -> l++

// CAUTION: Use absolute difference Math.abs(arr[l] + brr[r] - x) to get the
// current sum's distance from x, don't use x - (arr[l] + brr[r]) as when
// (arr[l] + brr[r]) > x then we will get -ve difference which will indicate
// most closest value of x even though it won't be the closest one.
// e.g. x = 32, arr[l] + brr[r] = 41, then diff = x - (arr[l] + brr[r]) = -9,
// whereas actual distance is +9.
// And due to this wrong distance, the correct closest pair will get more
// distance incorrectly:
// e.g. x = 32, arr[l] + brr[r] = 31, then diff = x - (arr[l] + brr[r]) = 1
// (> -9, so diff and ans pair do not get updated)
// Hence use diff = Math.abs(arr[l] + brr[r] - x)
//{ Driver Code Starts
//Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class GFG {

    // Driver code
    public static void main(String[] args) throws IOException {
        // Taking input using buffered reader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcases = Integer.parseInt(br.readLine());

        // looping through all testcases
        while (testcases-- > 0) {
            String[] element = br.readLine().split(" ");
            int N = Integer.parseInt(element[0]);
            int M = Integer.parseInt(element[1]);
            int arr[] = new int[N];
            int brr[] = new int[M];
            String[] elements = br.readLine().split(" ");
            for (int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(elements[i]);

            String[] ele = br.readLine().split(" ");
            for (int i = 0; i < M; i++)
                brr[i] = Integer.parseInt(ele[i]);

            int X = Integer.parseInt(br.readLine());

            Solution obj = new Solution();
            ArrayList<Integer> ans;
            ans = obj.printClosest(arr, brr, N, M, X);
            System.out.println(Math.abs(ans.get(0) + ans.get(1) - X));
        }
    }
}

// } Driver Code Ends

// User function Template for Java

class Solution {
    // Function for finding maximum and value pair
    public static ArrayList<Integer> printClosest(int arr[], int brr[], int n, int m, int x) {
        // code here
        int res_l = 0, res_r = 0;

        int l = 0, r = m - 1;
        int diff = Integer.MAX_VALUE;
        while (l < n && r >= 0) {
            if (Math.abs(arr[l] + brr[r] - x) < diff) {
                diff = Math.abs(arr[l] + brr[r] - x);
                res_l = arr[l];
                res_r = brr[r];
            }

            if (arr[l] + brr[r] == x) {
                break;
            } else if (arr[l] + brr[r] > x) {
                r--;
            } else {
                l++;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(res_l);
        ans.add(res_r);
        return ans;
    }
}