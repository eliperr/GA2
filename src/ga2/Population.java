/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michael
 */
public class Population {
     private Gene[] people ;
     
     public Population(  Gene [] pop)
    {
        people = pop;
        
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
     
     
      public void setGenes( Gene[] pop)
     {
        this.people=pop;
     }
     
     public void setIdentities()
     {
      
        BioJavaWrapper.blastAll(people);
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
     
   public  List<Gene> chooseParents()
   { 
       List<Gene> genes=new ArrayList<>(); 
       
       
     for ( Gene g: people)
     {   double n=Math.random();
       if (n<=0.5)
       {
           genes.add(g);
       }     
         
     } 
     
     return genes;
   }          
           
     //star trek class TNG 
     
     
   public void nextGen( double mutRate )
    {
          Gene[] children = new Gene[people.length];
           for (int i=0; i<people.length; i=i+2)
           {
              Gene p1=  people[i];
              Gene p2=  people[i+1];
              String mutp1=Gene.ptMut(p1,mutRate);
              String mutp2=Gene.ptMut(p2,mutRate);
              String child=Gene.crossover(mutp1, mutp2);
                Gene kid=new Gene(child);
               children[i]=kid;
            
                mutp1=Gene.ptMut(p1,mutRate);
                mutp2=Gene.ptMut(p2,mutRate);
               child=Gene.crossover(mutp1, mutp2);
                kid=new Gene(child);
               children[i+1]=kid;   
               
               
           }    
               
          
          
          
          
          people = children;
          setIdentities();
    }  
}
