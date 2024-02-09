// Problem: https://www.geeksforgeeks.org/count-pairs-array-whose-sum-less-x/

// Brute Force Approach: TC: O(N^2), SC: O(1)
// Form each possible pair and keep track of count of pairs having sum < k.
// static int findPairs(int arr[], int n, int x) {
//     int count = 0;
//     for(int i = 0;i < n;i++) {
//         for(int j = i;j < n;j++) {
//             int sum = arr[l] + arr[r];
//             if(sum < x) {
//                 count++;
//             }
//         }
//     }

//     return count;
// }

// Optimized Approach: 
// Using extreme 2-pointer: TC: O(N), SC: O(1)
// if a[l] + a[r] >= x -> r--
// else -> found a pair with sum < x -> count += (r - l), l++

// Logic:
// Why count += (r - l), why not count++ as we found only 1 pair a[l] and a[r]?
// e.g.:
// x = 7, 
// value: 1 1 2 3 7 9
// index: 0 1 2 3 4 5
//        l     r
// As a[l] + a[r] < sum, and array is SORTED, ofc, pairs of a[l] with all other elements from l+1 to r-1  will also have sum < k.
// i.e. for a[l] + a[r] < sum, we can have r-l pairs.

static int findPairs(int arr[], int n, int x) {
    int count = 0;
    int l = 0, r = n - 1;
    while(l < r) { // not <= because we need pair of elements
        int sum = arr[l] + arr[r];
        if(sum >= x) {
            r--;
        } else {
            count += (r - l);
            l++;
        }
    }
    
    return count;
}