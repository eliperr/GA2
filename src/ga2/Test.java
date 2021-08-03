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
        Gene[] test = new Gene[10];
        
        for(int i = 0; i < test.length; i++)
        {
            test[i] = new Gene(3135);
        }
        
        Multiblaster multi = new Multiblaster(4);
        multi.blastAll(test);
        
        for(int i = 0; i < test.length; i++)
        {
            System.out.println(test[i].getAASeq()+" "+test[i].getIdentity());
        }
        
        
        /*
        System.exit(0);
        Population p=new Population (10,3135 );
        p.printThePeople();
        */
    }
}
