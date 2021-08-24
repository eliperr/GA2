/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga2;

import java.util.ArrayList;
import java.util.Arrays;
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
      
      public void printTheBestPerson ( ) 
      {
          
        Arrays.sort(people);
        
           System.out.println(people[0].getAASeq());
         System.out.println(people[0].getIdentity());
          
        
          
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
     
     
   public void nextGen( double mutRate, int selectNum )
    {
           Arrays.sort(people); 
           Gene[]half= new Gene[people.length/selectNum];
           
            //printThePeople();
            
            
        
        Gene[] children = new Gene[ (int) Math.floor(people.length/2/selectNum ) *selectNum *2];
           for (int i=0; i<(Math.floor(people.length/selectNum)-1); i=i+2)  //start with selectNum=2 ---> take best half of parents
           {
            
            for (int j=0; j<selectNum*2; j++)  //should be 4 children or 2X parents selected
             {    
               Gene p1=  people[i];
              Gene p2=  people[i+1];
            
              String mutp1=Gene.ptMut(p1,mutRate);
              String mutp2=Gene.ptMut(p2,mutRate);
               mutp1=Gene.del(mutp1,mutRate,1);
              mutp2=Gene.del(mutp2,mutRate,1);
              mutp1=Gene.dup(mutp1,mutRate,1);
              mutp2=Gene.dup(mutp2,mutRate,1);
              mutp1=Gene.insertion(mutp1,mutRate,1);
              mutp2=Gene.insertion(mutp2,mutRate,1);
              
              String child=Gene.crossover(mutp1, mutp2);
                Gene kid=new Gene(child);
                
               // System.out.println("i is " + i + " j is " + j);
               children[i*selectNum+j]=kid;
              
                 
               } 
               
           }    
               
          
          
          
          
          people = children;
          setIdentities();
           Arrays.sort(people);
    }  
}
