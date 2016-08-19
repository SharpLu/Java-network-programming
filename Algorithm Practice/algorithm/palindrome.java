/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode;

/**
 *
 * @author mac
 */
public class palindrome {
    public static void main(String[] args) {
        
    }
    
    public boolean isPalindrome(String words){
    int i=0, j =words.length()-1;
    while(i<j){
    while(i<j && !Character.isLetterOrDigit(words.charAt(i))) i++;
    while(i<j && !Character.isLetterOrDigit(words.charAt(j))) j--;
    if(Character.toLowerCase(words.charAt(i))!= Character.toLowerCase(words.charAt(j))){
    return false;
    }
    }
        return true;
    }
}
