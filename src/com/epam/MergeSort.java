package com.epam;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSort {
    private MergeSort(){}

    public static void sort(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        forkJoinPool.invoke(new MergeSortTask(array, 0, array.length - 1));
    }


    private static class MergeSortTask extends RecursiveAction {
        private final int[] array;
        private final int[] buffer;
        private final int first;
        private final int last;

        public MergeSortTask(int[] array, int first, int last) {
            this.array = array;
            this.buffer = new int[array.length];
            this.first = first;
            this.last = last;
        }

        @Override
        protected void compute() {
            if (last > first) {
                int middle = first + (last - first) / 2;
                MergeSortTask firstHalf = new MergeSortTask(array,  first, middle);
                MergeSortTask secondHalf = new MergeSortTask(array, middle + 1, last);
                invokeAll(firstHalf, secondHalf);
                merge(this.array, this.first, middle, this.last);
            }
        }

        private void merge(int[] array, int first, int middle, int last) {
            int idx = first;
            while (idx <= last) {
                buffer[idx] = array[idx];
                idx++;
            }

            int firstHalfIndex = first;
            int secondHalfIndex = middle + 1;
            idx = first;
            while(idx <= last) {
                if (firstHalfIndex > middle) {
                    array[idx] = buffer[secondHalfIndex];
                    secondHalfIndex++;
                } else if (secondHalfIndex > last) {
                    array[idx] = buffer[firstHalfIndex];
                    firstHalfIndex++;
                } else if (buffer[firstHalfIndex]< buffer[secondHalfIndex]) {
                    array[idx] = buffer[firstHalfIndex];
                    firstHalfIndex++;
                } else {
                    array[idx] = buffer[secondHalfIndex];
                    secondHalfIndex++;
                }
                idx++;
            }
        }
    }
}

