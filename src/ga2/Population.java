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
     
     
   public Gene[] pickParents(int selectNum)
   {
       Arrays.sort(people); 
           //Gene[]half= new Gene[people.length/selectNum];
           
           int range = people.length/selectNum; // 0 to this number
           
           
           int[] ranking = new int[range];
           int total = 0;
           
           for(int i = 0; i < ranking.length; i++)
           {
               //ranking[ranking.length-i-1] = 1;
               //total ++;
               ranking[ranking.length-i-1] = i+1;
               total += i+1;
           }
           
           double[] prob = new double[range];
           for(int i = 0; i < prob.length; i++)
           {
               prob[i] = (double)ranking[i] / total;
           }
           

        
        int p1_num = pickParent(prob);
        Gene p1 = people[p1_num];

        int p2_num;
        Gene p2;

        do
        {
            p2_num = pickParent(prob);
        }
        while(p2_num == p1_num);

        p2 = people[p2_num];
        
        return new Gene[]{p1, p2};
   }
   
   public void nextGen( double mutRate, int selectNum )
    {
           Arrays.sort(people); 
           //Gene[]half= new Gene[people.length/selectNum];
           
           int range = people.length/selectNum; // 0 to this number
           
           
           int[] ranking = new int[range];
           int total = 0;
           
           for(int i = 0; i < ranking.length; i++)
           {
               ranking[ranking.length-i-1] = i+1;
               total += i+1;
           }
           
           double[] prob = new double[range];
           for(int i = 0; i < prob.length; i++)
           {
               prob[i] = (double)ranking[i] / total;
           }
           
           //System.out.println(Arrays.toString(prob));
           
            //printThePeople();
            
            
        
        //Gene[] children = new Gene[ (int) Math.floor(people.length/2/selectNum ) *selectNum *2]
          Gene[] children = new Gene[people.length];      
           
          for(int i = 0; i < children.length; i++)
          {
              int p1_num = pickParent(prob);
              Gene p1 = people[p1_num];
              
              int p2_num;
              Gene p2;
              
              do
              {
                  p2_num = pickParent(prob);
              }
              while(p2_num == p1_num);
              
              p2 = people[p2_num];
              
              
              
              
           //for (int i=0; i<(Math.floor(people.length/selectNum)-1); i=i+2)  //start with selectNum=2 ---> take best half of parents
           {
            
            //for (int j=0; j<selectNum*2; j++)  //should be 4 children or 2X parents selected
             {    
               //Gene p1=  people[i];
              //Gene p2=  people[i+1];
            
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
                
                /*
                System.out.println(p1_num+" and "+p2_num+" had a kid! Mazel tov!");
                System.out.println("p1 "+p1.getAASeq()+" "+p1.getIdentity());
                System.out.println("p2 "+p2.getAASeq()+" "+p2.getIdentity());
                
                kid.setIdentity(BioJavaWrapper.blast(kid.getAASeq()));
                System.out.println("kid "+kid.getAASeq()+" "+kid.getIdentity());
                */
                
               // System.out.println("i is " + i + " j is " + j);
               //children[i*selectNum+j]=kid;
              children[i] = kid;
                 
               } 
               
           }  
          }
               
          
          
          
          
          people = children;
           Arrays.sort(people);
    }  
   
   public void nextGenPartialReplace( double mutRate, int selectNum )
    {
           Arrays.sort(people); 
           //Gene[]half= new Gene[people.length/selectNum];
           
           int range = people.length/selectNum; // 0 to this number
           
           
           int[] ranking = new int[range];
           int total = 0;
           
           for(int i = 0; i < ranking.length; i++)
           {
               ranking[ranking.length-i-1] = i+1;
               total += i+1;
           }
           
           double[] prob = new double[range];
           for(int i = 0; i < prob.length; i++)
           {
               prob[i] = (double)ranking[i] / total;
           }
           
           //System.out.println(Arrays.toString(prob));
           
            //printThePeople();
            
            
        
        //Gene[] children = new Gene[ (int) Math.floor(people.length/2/selectNum ) *selectNum *2]
          Gene[] children = new Gene[people.length];      
           
          
          for(int i = 0; i < children.length/selectNum; i++)
          {
              children[i] = people[i];
          }
          for(int i = children.length/selectNum; i < children.length; i++)
          {
              int p1_num = pickParent(prob);
              Gene p1 = people[p1_num];
              
              int p2_num;
              Gene p2;
              
              do
              {
                  p2_num = pickParent(prob);
              }
              while(p2_num == p1_num);
              
              p2 = people[p2_num];
              
              
              
              
           //for (int i=0; i<(Math.floor(people.length/selectNum)-1); i=i+2)  //start with selectNum=2 ---> take best half of parents
           {
            
            //for (int j=0; j<selectNum*2; j++)  //should be 4 children or 2X parents selected
             {    
               //Gene p1=  people[i];
              //Gene p2=  people[i+1];
            
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
                
                /*
                System.out.println(p1_num+" and "+p2_num+" had a kid! Mazel tov!");
                System.out.println("p1 "+p1.getAASeq()+" "+p1.getIdentity());
                System.out.println("p2 "+p2.getAASeq()+" "+p2.getIdentity());
                
                kid.setIdentity(BioJavaWrapper.blast(kid.getAASeq()));
                System.out.println("kid "+kid.getAASeq()+" "+kid.getIdentity());
                */
                
               // System.out.println("i is " + i + " j is " + j);
               //children[i*selectNum+j]=kid;
              children[i] = kid;
                 
               } 
               
           }  
          }
               
          
          
          
          
          people = children;
           Arrays.sort(people);
    }  
   
   public void replaceWorst(double mutRate, int selectNum)
   {
       Arrays.sort(people);
       
       Gene[] parents = pickParents(selectNum);
       
       Gene p1 = parents[0];
       Gene p2 = parents[1];
       
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
          
          people[people.length-1] = kid;
          //kid.setIdentity(BioJavaWrapper.blast(kid.getAASeq()));
          
          Arrays.sort(people);
   }
   
   public int pickParent(double[] prob)
   {
       double p = Math.random();
       
       double total = 0;
       
       for(int i = 0; i < prob.length; i++)
       {
           total += prob[i];
           
           if(p < total)
           {
               return i;
           }
           
       }
       
       return -1;
   }
}
