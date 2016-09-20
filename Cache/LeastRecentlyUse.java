/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bittiger;

import java.util.HashMap;

/**
 *
 * @author mac
 */
class LURCache {

    class Node {

        Integer Key;
        Integer Value;
        Node prev;
        Node next;

        Node(Integer Key, Integer Value) {
            this.Key = Key;
            this.Value = Value;
            prev = null;
            next = null;
        }
    }

    HashMap<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

    public LURCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
        head = new Node(null, null);
        tail = new Node(null, null);
        head.prev = tail;
        head.next = tail;
        tail.prev = head;
        tail.next = head;
        System.out.println(map.size() + " map size ");
    }

    public void put(int key, int value) {
        System.out.println(key + "Key" + value + " Value ");
        Node node = map.get(key);
        
        if (node == null) {
            if (map.size() == capacity) {
                map.remove(head.next.Key);
                detach(head.next);
                //  attach(node);
            }
            node = new Node(key, value);
            map.put(key, node);
            attach(node);
        } else {
            node.Value = value;
            detach(node);
            attach(node);
        }
    }

    public Integer get(int key) {
        Node node = map.get(key);
        if (node != null) {
            detach(node);
            attach(node);
        }

        return node == null ? null : node.Value;
    }

    public void attach(Node node) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

    public void detach(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;

    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int capacity = 3;
        LURCache cache = new LURCache(capacity);
        for (int i = 0; i < array.length; i++) {
           // System.out.println(array[i]);
            cache.put(array[i], array[i]);
        }
        System.out.println(cache.get(1));

    }

}
