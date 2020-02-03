package com.example.sortingtimechecker.Algorithms;

public class SortingAlgorithms {

    // Bubble sort algorithm that returns its execution time
    public static double bubbleSort( long[] array ){
        long start = System.currentTimeMillis();
        int in, out;
        int nElems = array.length;

        for( out=nElems-1; out>1; out-- ){
            for( in=0; in<out; in++ ){
                if(array[in] > array[in+1])
                    swap(in, in+1, array);
            }
        }
        long end = System.currentTimeMillis();
        return (end-start)/1000;
    }

    public static double selectionSort( long[] array ){
        long start = System.currentTimeMillis();
        int in, out, min;
        int nElems = array.length;

        for(out=0; out<nElems-1; out++){
            min=out;
            for(in=out+1; in<nElems; in++){
                if( array[in] < array[min] )
                    min = in;
            }
            swap(out, min,array);
        }
        long end = System.currentTimeMillis();
        return (end-start)/1000;
    }

    public static double insertionSort( long[] array ){
        long start = System.currentTimeMillis();
        int in, out;
        int nElems = array.length;
        for(out=1; out<nElems; out++){
            long temp = array[out];
            in = out;
            while(in>0 && array[in-1] >=temp){
                array[in] = array[in-1];
                --in;
            }
            array[in] = temp;
        }
        long end = System.currentTimeMillis();
        return (end-start)/1000;
    }

    //------------------------------------------------------------------------------------------------------
    // THIS CODE FOR MERGE SORT & QUICK SORT IS TAKEN FROM GEEKSFORGEEKS website and some changes have been made in this.
    //------------------------------------------------------------------------------------------------------
    private static void merge(long[] arr, int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        long[] L = new long[n1];
        long[] R = new long[n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    private static void recMergeSort(long[] arr, int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            recMergeSort(arr, l, m);
            recMergeSort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public static double mergeSort( long[] array ){
        long start = System.currentTimeMillis();
        int nElems = array.length;
        recMergeSort(array, 0, nElems-1);
        long end = System.currentTimeMillis();
        return (end-start)/1000;

    }

    private static int partition(long[] arr, int low, int high)
    {
        long pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                long temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        long temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    private static void recQuickSort(long[] arr, int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            recQuickSort(arr, low, pi-1);
            recQuickSort(arr, pi+1, high);
        }
    }

    public static double quickSort(long[] array){
        long start = System.currentTimeMillis();
        int nElems = array.length;
        recQuickSort(array, 0, nElems-1);
        long end = System.currentTimeMillis();
        return (end-start)/1000;

    }



    //------------------------------------------------------------------------------------------------------

    // swap method that swaps two elements of array
    private static void swap(int x, int y, long[] array){
        long temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
