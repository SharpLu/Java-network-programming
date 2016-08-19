/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author mac
 */
public class easy {

    public static void main(String args[]) {
        //  int value=0;
        // value = reverseInteger(123);
        int theValue = 0;
//        theValue = reverseInteger(123);
//        System.out.println();
//        System.out.println(theValue);
        
        //sumTwoNumber(1,2);
        addDigits(33);
        
    }
    
    public static int reverseInteger(int n) {
        int reversed_n = 0;
        
        while (n != 0) {
            int temp = reversed_n * 10 + n % 10;
            n = n / 10;
            if (temp / 10 != reversed_n) {
                reversed_n = 0;
                break;
            }
            reversed_n = temp;
        }
        return reversed_n;
    }
    
   public static int sumTwoNumber(int a, int b){
  
       return sumTwoNumber(a^b, (a&b) << 1);
   }

   
   public static int addDigits(int num) {
        return (num!=0 && num%9==0) ? 9 : num%9;
    }
}
