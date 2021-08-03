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
public class Population {
     private Gene[] people ;
     
     public Population(  Gene [] pop)
    {
        this.people = pop;
        
    }
     
     public Population( int num, int len )  //num = number of people in population, len=length of genes in nucleotides
    {
        Gene[] pop=new Gene[num];
        
        for (int i=0; i<num; i++)
            
        {
            pop[i]=new Gene(len);
            
        }
       this.people = pop;          
                
    }
    
     public Gene[] getGene()
    {
          return people;
    }
     public Gene getGene( int i)
    {
          return people[i];
    }
     public void printThePeople ( )    
             
     {        int len =people.length;
     
         for (int i=0;i<len;i++)
         {
            
              System.out.println("person number" + i + ":");  
             System.out.println(people[i].getAASeq());
         System.out.println(people[i].getIdentity());
           System.out.println("\n");   
         }    
             
             
     }         
     
     
}
