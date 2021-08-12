/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga2;

/**
 *
 * @author michael
 */
public class Test {
    
    
    
     public static void main(String[] args) 
    {
        Population test = new Population(10, 3135);
        test.setIdentities();
        test.printThePeople();
        
        for(int i = 0; i < 9; i++)
        {
            System.out.println("***** GENERATION "+i);
            test.nextGen(1);
            test.printThePeople();
        }
        
  
        
        
        /*
        System.exit(0);
        Population p=new Population (10,3135 );
        p.printThePeople();
        */
    }
}
