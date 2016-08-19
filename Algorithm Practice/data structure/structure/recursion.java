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
public class recursion {

    public static void main(String args[]) {
        int fibonacci = fibonacci(6);
        System.out.print(fibonacci);
    }
    
    public static int fibonacci(int number) {
        if (number == 1 || number == 2) {
            return 1;
        }
        
        return fibonacci(number - 1) + fibonacci(number - 2);
        
    }
}
