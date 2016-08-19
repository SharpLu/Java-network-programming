/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mac
 */
public class TwoSum {
    
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15,17,18};
        int target = 9;
        TwoSum s = new TwoSum();
        int[] result = s.twoSum(numbers, target);
        for (int i = 0; i < result.length; i++) {
            System.out.println("result "+result[i]);
        }
    }
    
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int x = numbers[i];
            System.out.println("x "+x);
            int k=target - x;
               System.out.println("k "+k);
            if (map.containsKey(target - x)) {
                return new int[]{map.get(target - x) , i};
            }
            map.put(x, i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    
}
