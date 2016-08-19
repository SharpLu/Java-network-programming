/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode;

import java.util.HashSet;

/**
 *
 * @author mac
 */
public class happynumber {

    public static void main(String[] args) {
        System.out.println(getNextHappy(19));
   
    }

    public static int getNextHappy(int number) {
        int sum = 0;
        while (number != 0) {
            sum += (number % 10) * (number % 10);
            number /= 10;
        }
        return sum;
    }

    public boolean isHappy(int n) {

        HashSet<Integer> hash = new HashSet<Integer>();
        while (n != 1) {
            if (hash.contains(n)) {
                return false;
            }
            hash.add(n);
            n = getNextHappy(n);
        }
        return true;
    }
}
