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
    {   //main method: test generations having children
        //Population test = new Population(20, 3135);
       Population test = new Population(50, 12000);
        test.setIdentities();
        //test.printThePeople(); 
        
       
        
       
        
        for(int i = 0; i < 500; i++)
        {
            System.out.println("***** GENERATION "+i);
            //test.nextGenPartialReplace(1,2);  //1 is mutation rate, 2 is selectNum 
            test.nextGen(1, 2);
            //test.replaceWorst(1, 2);
            test.printTheBestPerson();
            
            
        }
        
    
        
         /*double mutRate=1.9;
        int mut= (int) Math.floor(mutRate); 
        if (Math.random() <= mutRate-mut)
        { mut++; }    
        System.out.println(mut); */
        
        //test mutation types
        /*Gene g=new Gene (5); 
        System.out.println(g.getCodingStrand());   
        String val=Gene.dup(g,1.5,2);             //valine 
         System.out.println(val);       
        */
        
        /*
        System.exit(0);
        Population p=new Population (10,3135 );
        p.printThePeople();
        */
    }
}
