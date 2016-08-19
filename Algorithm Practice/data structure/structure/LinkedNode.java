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
public class LinkedNode {
    
    public ListNode reverse(ListNode head){
    ListNode prev= null;
    while(head != null){
    ListNode next=head.next;
    head.next=prev;
    prev=head;
    head=next;
    }
        return prev;
    }

}
