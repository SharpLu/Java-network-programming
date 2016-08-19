/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode;

import data.structure.ListNode;
import java.util.LinkedList;

/**
 *
 * @author mac
 */
public class ReverseLinked {

    public static void main(String[] args) {

    }

    public static ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            dummy.next = cur;
            cur = temp;

        }
        return dummy.next;
    }


}
