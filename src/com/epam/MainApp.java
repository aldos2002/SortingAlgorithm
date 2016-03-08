package com.epam;

/**
 * Created by Almas_Doskozhin
 * on 2/21/2016.
 */
public class MainApp {

    public static void main(String[] args){
        MergeSort mergeSort = new MergeSort();
        int[] array = {2,3,4,1,5,0,6,7,8,9};
        mergeSort.sort(array);
        for(Integer i: array){
            System.out.println(i);
        }
    }
}
