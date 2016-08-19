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
public class SelectionSort {

 public static void main(String[] args) {
          int unsortedArray[] = new int[]{8, 5, 2, 6, 9, 3, 1, 4, 0, 7};
         // selectionSort(unsortedArray);
          Arrays.sort(unsortedArray);
          System.out.println("After sort: ");
          for (int item : unsortedArray) {
              System.out.print(item + " ");
          }
}

//  public static void selectionSort(int[] array) {
//          int len = array.length;
//          for (int i = 0; i < len; i++) {
//              for (int item : array) {
//                  System.out.print(item + " ");
//              }
//              System.out.println();
//              int min_index = i;
//              for (int j = i + 1; j < len; j++) {
//                  if (array[j] < array[min_index]) {
//                      min_index = j;
//} }
//              int temp = array[min_index];
//              array[min_index] = array[i];
//              array[i] = temp;
//} }
}
