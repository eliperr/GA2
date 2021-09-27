/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga2;

/**
 *
 * @author eliperr
 */
public class DumbAlignment {
    
    
    
 public static double dumbBlast(String target, String evolved)
 {            
    double count=0;
    int length=Math.min(target.length(), evolved.length()); 
     for (int i=0; i<length; i++ )  
     {
         if (target.charAt(i)==evolved.charAt(i))
         { count++ ; }
         
         
         
     }     
     
    int maxLen=Math.max(target.length(), evolved.length());
    return count/maxLen; 
         
 
 } 
    
}
