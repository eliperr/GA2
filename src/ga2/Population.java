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
     private Person[] people ;
     
     public Population(  Person [] pop)
    {
        people = pop;
        
    }
     
     public Population( int num, int len )  //num = number of people in population, len=length of nucleotide in gene
    {
        Person[] pop=new Person[num];
        
        for (int i=0; i<num; i++)
            
        {
            pop[i]=new Person(len);
            
        }
       this.people = pop;          
                
    }
     
     
      public void setGenes( Person[] pop)
     {
        this.people=pop;
     }
     
     public void setIdentities()  //not to be confused with set identity in people class
     {
      
        BioJavaWrapper.blastAll(people);
     }
    
     public Person[] getGene()
    {
          return people;
    }
     public Person getGene( int i)
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
              
     
   public  List<Person> chooseParents()
   { 
       List<Person> genes=new ArrayList<>(); 
       
       
     for ( Person g: people)
     {   double n=Math.random();
       if (n<=0.5)
       {
           genes.add(g);
       }     
         
     } 
     
     return genes;
   }          
           
     //star trek class TNG 
     
     
   public Person[] pickParents(int selectNum) 
   {
       Arrays.sort(people); 
           //Gene[]half= new Person[people.length/selectNum];
           
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
        Person p1 = people[p1_num];

        int p2_num;
        Person p2;

        do
        {
            p2_num = pickParent(prob);
        }
        while(p2_num == p1_num);

        p2 = people[p2_num];
        
        return new Person[]{p1, p2};
   }
   
   public void nextGen( double mutRate, int selectNum )
    {
           Arrays.sort(people); 
           //Gene[]half= new Person[people.length/selectNum];   
           
           int range = people.length/selectNum; // 0 to this number   ///range could be double?
           
           
           int[] ranking = new int[range]; //arr of num people selected?

           int total = 0;
           
           for(int i = 0; i < ranking.length; i++)  //ranking.length is range, num people selected
           {
               ranking[ranking.length-i-1] = i+1;  //start at end of array (lowest), ranked 1,2, 3 etc  
               total += i+1;  //  i:0 total: 1, i:1, total: 3, i:2 total:6, i:3 tot: 10 , 15, 21, 28, 36, 45,55 etc
           }
           
           double[] prob = new double[range];         //probability array for each person in range

           for(int i = 0; i < prob.length; i++)  //highest num at start of ranking
           {
               prob[i] = (double)ranking[i] / total;  //probability is ranking/total for each person because easy to write 
                        //(no special significance of numbers  lowest ranking: for 1 person: 1, 2 persons: 1/3,  3 persons 1/6   highest ranking for 10 persons: 10/55=2/11 for each mating     

           }
           
           //System.out.println(Arrays.toString(prob));
           
            //printThePeople();
            
            
        
        //Gene[] children = new Person[ (int) Math.floor(people.length/2/selectNum ) *selectNum *2]
          Person[] children = new Person[people.length];      
           
          for(int i = 0; i < children.length; i++)  
          {
              int p1_num = pickParent(prob);  //promiscous genes, parents pair with any other parent for each child and may pair with a different partner for another child
              Person p1 = people[p1_num];
              
              int p2_num;
              Person p2;
              
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
            
              String mutp1=Person.ptMut(p1,mutRate);
              String mutp2=Person.ptMut(p2,mutRate);
               mutp1=Person.del(mutp1,mutRate,1);
              mutp2=Person.del(mutp2,mutRate,1);
              mutp1=Person.dup(mutp1,mutRate,1);
              mutp2=Person.dup(mutp2,mutRate,1);
              mutp1=Person.insertion(mutp1,mutRate,1);
              mutp2=Person.insertion(mutp2,mutRate,1);
              
              String child=Person.crossover(mutp1, mutp2);
                Person kid=new Person(child);
                
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
           //Gene[]half= new Person[people.length/selectNum];
           
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
            
            
        
        //Gene[] children = new Person[ (int) Math.floor(people.length/2/selectNum ) *selectNum *2]
          Person[] children = new Person[people.length];      
           
          
          for(int i = 0; i < children.length/selectNum; i++)
          {
              children[i] = people[i];
          }
          for(int i = children.length/selectNum; i < children.length; i++)
          {
              int p1_num = pickParent(prob);
              Person p1 = people[p1_num];
              
              int p2_num;
              Person p2;
              
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
            
              String mutp1=Person.ptMut(p1,mutRate);
              String mutp2=Person.ptMut(p2,mutRate);
               mutp1=Person.del(mutp1,mutRate,1);
              mutp2=Person.del(mutp2,mutRate,1);
              mutp1=Person.dup(mutp1,mutRate,1);
              mutp2=Person.dup(mutp2,mutRate,1);
              mutp1=Person.insertion(mutp1,mutRate,1);
              mutp2=Person.insertion(mutp2,mutRate,1);
              
              String child=Person.crossover(mutp1, mutp2);
                Person kid=new Person(child);
                
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
       
       Person[] parents = pickParents(selectNum);
       
       Person p1 = parents[0];
       Person p2 = parents[1];
       
       String mutp1=Person.ptMut(p1,mutRate);
        String mutp2=Person.ptMut(p2,mutRate);
         mutp1=Person.del(mutp1,mutRate,1);
        mutp2=Person.del(mutp2,mutRate,1);
        mutp1=Person.dup(mutp1,mutRate,1);
        mutp2=Person.dup(mutp2,mutRate,1);
        mutp1=Person.insertion(mutp1,mutRate,1);
        mutp2=Person.insertion(mutp2,mutRate,1);

        String child=Person.crossover(mutp1, mutp2);
          Person kid=new Person(child);
          
          people[people.length-1] = kid;
          //kid.setIdentity(BioJavaWrapper.blast(kid.getAASeq()));
          
          Arrays.sort(people);
   }
   
   public int pickParent(double[] prob)   //randomly choose parent (return index) with some persons having higher prob than others (using prob ranking system)
   {  //not be confused with pickParents  
       double p = Math.random();
       
       double total = 0;
       
       for(int i = 0; i < prob.length; i++)  //thru all prob for each person    starting at highest prob
       {
           total += prob[i];//adding up all probs from highest to lowest
           
           if(p < total)
           {
               return i;   //eventually prob adds up to one so need to return a parent

           }
           
       }
       
       return -1;  //this should never happen?but need it so comp doesn't yell at you
   }
}
