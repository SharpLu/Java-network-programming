/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structure;

/**
 *
 * @author mac
 */
public class BubblSort {

    public static void main(String args[]) {

        int unsortedArray[] = new int[]{9, 8, 7, 6, 5, 4, 3, 12};
        bubblesort(unsortedArray);
        System.out.println();
//        for(int item: unsortedArray){
//       // System.out.print(item + " ");
//        }
    }

    public static void  bubblesort(int[] array) {
       int len=array.length;
       for(int i=0;i<len;i++){
       for(int item:array){
       System.out.println(item);
       }
       System.out.println();
       for(int j=1;j<len-1;j++){
       System.out.println(j+"The J is ");
       if(array[j-1]>array[j]){
           int tem=array[j-1];
           array[j-1]=array[j];
           array[j]=tem;
       
       }
       }
       }
       
    }
}
