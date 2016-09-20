/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bittiger;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author mac
 */
class LURCache2<K, V> extends LinkedHashMap<K, V> {

    private int capacity;

    public LURCache2(int capacity) {
        super(128, 0.75f, true);
        this.capacity = capacity;
    }

    public boolean removeElements(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int capacity = 3;
        LURCache2 cache = new LURCache2(capacity);
        for (int i = 0; i < array.length; i++) {
            // System.out.println(array[i]);
           
            cache.put(array[i], array[i]);
        }
        System.out.println(cache.get(2));

    }

}
