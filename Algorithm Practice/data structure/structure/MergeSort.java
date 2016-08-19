/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structure;

import java.util.Arrays;

/**
 *
 * @author mac
 */
public class MergeSort {
     public static void main(String[] args) {
          int unsortedArray[] = new int[]{8, 5, 2, 6, 9, 3, 1, 4, 0, 7};
        // mergeSort(unsortedArray);
          
          }
     public static void mergeSort(int[] array,int low, int high){
     
     int[] helper = new int[array.length];
     //copy array to the helper
     for(int k=low; k<high;k++){
         helper[k]=array[k];
         System.out.print(k);
     }
     }
}
