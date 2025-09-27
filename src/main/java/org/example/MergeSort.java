package org.example;

public class MergeSort{
    static void merge(int[] a, int l, int m, int r){

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for(int i = 0; i < n1; i++){
            L[i] = a[l + i];
        }
        for(int j = 0; j < n2; j++){
            R[j] = a[m + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while(i < n1 && j < n2){
            if(L[i] <= R[j]){
                a[k] = L[i];
                i++;
            }
            else{
                a[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1){
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n2){
            a[k] = R[j];
            j++;
            k++;
        }
    }
    static void mergeSort(int arr[], int l, int r){

        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }
    public static void main(String args[]){

        int arr[] = {47, 89, 56,23};

        mergeSort(arr, 0, arr.length - 1);

        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
