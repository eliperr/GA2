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
       
       MultiTranslate.translate(people);
       Arrays.sort(people);          
                
    }
     
     
    
      public void setGenes( Person[] pop)
     {
        this.people=pop;
     }
     
      // don't use this because translate already goes to biojava
      /*
     public void setIdentities()  //not to be confused with set identity in people class
     {
      
        BioJavaWrapper.blastAll(people);
     }
      */
    
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
          
        //Arrays.sort(people);
        
           System.out.println(people[0].getAASeq());
         System.out.println("best person is  "+ people[0].getIdentity());
          
        
          
      }   
      
      public void printTheMeanPerson ( ) 
      {
          
   
          
         double sum=0;
         
        for (Person p: people)
        {
            
            sum=sum+p.getIdentity();
            
        }   
        double mean=sum/people.length; 
        
        System.out.println("mean is " + mean);
          
      } 
      
      
       public void printTheSTDPerson ( ) 
      {
          
   
          
         double first=0;
         double second=0;
         
        for (Person p: people)
        {
            
            first=first+p.getIdentity()*p.getIdentity();
            second=second+p.getIdentity();
            
        }   
        
        first=first/people.length;
        second=second/people.length;
        double std= Math.sqrt( first-second*second);
        
         System.out.println("STD is " + std);
          
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
       //Arrays.sort(people); 
           //Person[]half= new Person[people.length/selectNum];
        int range = people.length/selectNum;
        
        if(prob == null || prob.length != range)
        {
            // 0 to this number
           
           
           int[] ranking = new int[range];  
           int total = 0;
           
           for(int i = 0; i < ranking.length; i++)
           {
               //ranking[ranking.length-i-1] = 1;
               //total ++;
               ranking[ranking.length-i-1] = i+1;
               total += i+1;
           }
           
           prob = new double[range];
           for(int i = 0; i < prob.length; i++)  
           {
               prob[i] = (double)ranking[i] / total;
           }
           
        }

        
        int p1_num = pickParent();
        Person p1 = people[p1_num];

        int p2_num;
        Person p2;

        p2_num = pickParent(p1_num); // pick another parent but exclude p1_num
        
        if(p1_num == p2_num)
        {
            throw new RuntimeException("parents are the same!");
        }

        p2 = people[p2_num];
        
        return new Person[]{p1, p2};
   }
   
   private double[] prob;
   
   public void nextGen( double mutRate, int selectNum )
    {
           //Arrays.sort(people); // impose condition that people is always sorted; then avoid sorting it twice
           //Person[]half= new Person[people.length/selectNum];
           
           
           
           //System.out.println(Arrays.toString(prob));
           
            //printThePeople();
            
            
        
        //Person[] children = new Person[ (int) Math.floor(people.length/2/selectNum ) *selectNum *2]
          Person[] children = new Person[people.length];      
           
          for(int i = 0; i < children.length; i++)  
          {
              
              
              
              
              
           //for (int i=0; i<(Math.floor(people.length/selectNum)-1); i=i+2)  //start with selectNum=2 ---> take best half of parents
           {
            
            //for (int j=0; j<selectNum*2; j++)  //should be 4 children or 2X parents selected
             {    
               //Person p1=  people[i];
              //Person p2=  people[i+1];
            
                Person kid = createChild(pickParents(selectNum), mutRate);
                
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
          
          MultiTranslate.translate(people);
           Arrays.sort(people);
    }  
   
   public Person createChild(Person[] parents, double mutRate)
   {
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
      
        return kid;
   }
   
   public void nextGenPartialReplace( double mutRate, int selectNum )
    {
           //Arrays.sort(people); 
           //Person[]half= new Person[people.length/selectNum];
           

           
           //System.out.println(Arrays.toString(prob));
           
            //printThePeople();
            
            
        
        //Person[] children = new Person[ (int) Math.floor(people.length/2/selectNum ) *selectNum *2]
          Person[] children = new Person[people.length];      
           
          
          for(int i = 0; i < children.length/selectNum; i++)
          {
              children[i] = people[i];
          }
          for(int i = children.length/selectNum; i < children.length; i++)
          {
              
              
              
              
              
           //for (int i=0; i<(Math.floor(people.length/selectNum)-1); i=i+2)  //start with selectNum=2 ---> take best half of parents
           {
            
            //for (int j=0; j<selectNum*2; j++)  //should be 4 children or 2X parents selected
             {    
               //Person p1=  people[i];
              //Person p2=  people[i+1];
            

                Person kid=createChild(pickParents(selectNum), mutRate);
                
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
          MultiTranslate.translate(people, children.length/selectNum, people.length);
           Arrays.sort(people);
    }  
   
   public void replaceWorst(double mutRate, int selectNum)
   {
       //Arrays.sort(people); // impose condition: array always sorted after modification; then avoid sorting prior to creating kids
       
       
          
          people[people.length-1] = createChild(pickParents(selectNum), mutRate);
          people[people.length-1].translate();
          
          //kid.setIdentity(BioJavaWrapper.blast(kid.getAASeq()));
          
          Arrays.sort(people);
   }
   
   public int pickParent()
   {
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
   
   // pick a parent but exclude the first parent from the selection
   public int pickParent(int exclude)
   {
       double p = Math.random();
       double total = 0;
       
       double adjust = 1-prob[exclude]; // adjust the probabilities of the remaining possibilities to add to 1
       
       for(int i = 0; i < prob.length; i++)
       {
           if(i != exclude)
           {
                total += prob[i] / adjust;
                
                if(p < total)
                {
                    return i;
                }
           }
       }
       
       return -1;
   }
}
