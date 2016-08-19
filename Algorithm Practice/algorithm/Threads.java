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
public class Threads extends Thread{
    private String name;
    
    public Threads(String name){
    this.name=name;
    }
    
    
    public void run(){
    for(int i=0;i<5;i++){
        System.out.println(name +"running"+i);
    }
    try{
        
        sleep((int)Math.random()*10);
    
    }catch(Exception e){
    
    }
    }
    
    
    public static void main(String agrs[]){
    Threads tA=new Threads("A");
    Threads tB=new Threads("B");
    tA.start();
    tB.start();
    }
    
    
    
}
