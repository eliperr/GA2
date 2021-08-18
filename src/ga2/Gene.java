/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga2;

/**
 *
 * @author Michael & Eli
 */

public class  Gene implements Comparable <Gene> {
    

    
    private String codingStrand;
    private String aaseq;
    private double identity;
    
   
   
    public Gene(String coding)
    {
        this.codingStrand = coding;
        aaseq = Translate.translate(codingStrand);
        
    }
    
    public void setIdentity(CallBlast blaster)
    {
        identity = blaster.blast(aaseq);
    }
    
    public void setIdentity(double d)
    {
        identity = d;
    }
    
    public Gene(int len)
    {
        this(randomGene(len));
    }
    
    public String getAASeq()
    {
        return aaseq;
    }
    
    public double getIdentity()
    {
        return identity;
    }
    
    public String getCodingStrand()
    {
        return codingStrand;
    }
  
    public int compareTo(Gene g)
    {
        if (identity<g.getIdentity())    //sort parents high to low, pick highest
        {
            return 1; 
        }   
        
        else if (identity>g.getIdentity())
        {
            return -1; 
        } 
        
        else 
        {
            return 0; 
        }
            
    }
    public static String randomGene(int length)
    {  String gene="";
      for (int i = 0; i <length; i++)
       { double n=Math.random();
         if (n<=0.25)
            {
                gene=gene+"C";
            }
         else if (n>0.25 && n<0.5)
            {
                gene=gene+"T";
            }
          else if (n>=0.5 && n<0.75)
            {
                gene=gene+"A";
            } 
          else
            {
                gene=gene+"G";
            } 
      }
        return gene;
    }
    
    public static String crossover(String g1, String g2)
    {
        String s1 = g1;
        String s2 = g2;
        
        int location = (int)(Math.random() * ( s1.length()/2.0 + s2.length()/2.0));
        
        //System.out.println("crossover at "+location);
        String output = s1.substring(0, location) + s2.substring(location);
        
    
        return output;
    }
    
    public static int decideMutNum ( double mutRate)   //helper
    {
         //mutations are decided based on mutationRate such that number of mutations will be minumum the integer + the fraction remaining chance that there will be one more than that
        //i.e consider 1.5--> minumum 1 mutation plus a 50% chance of 2 mutations
         int mut= (int) Math.floor(mutRate); 
        if (Math.random() <= mutRate-mut)
        { mut++; }    
        return mut; 
        
    }        
    
    
    public static String ptMut(Gene g1, double mutationRate)
    {
        String s1 = g1.getCodingStrand();
       int mutNum=decideMutNum (mutationRate);
        
     for (int i=0; i<mutNum; i++)
      {  
        int location = (int)(Math.random() * ( s1.length()));
           //try to decide which point mutation but cannot be the same nucleotide
       String CGAT="CGAT";
        int index=CGAT.indexOf(s1.charAt(location));
         char changeNuc=CGAT.charAt( (index + (int) Math.floor(Math.random() *3 + 1)) %4); 
          //System.out.println( "nucleotide change to " + changeNuc);
        //System.out.println("ptMut location is  "+ location);
        s1 = s1.substring(0, location) + changeNuc +  s1.substring(location + 1 ,s1.length() );
       }
    
        return s1;
    }
    
    //duplicate a small section of DNA of random size up to maxDupSize
 public static String dup (Gene g1, double mutationRate, int maxDupSize)  //make sure maxDupSize less than= length gene!
    {
        String s1 = g1.getCodingStrand();
        if (maxDupSize>s1.length())
        {
            maxDupSize=s1.length();  //don't make dup size more than or equal to gene!
        }
       int mutNum=decideMutNum (mutationRate);  //number of mutations as decided by mutation rate
       
    
       for (int i=0; i<mutNum; i++)
       {
          int dupSize= (int) (Math.random()* (maxDupSize-1)+1); //duplication 1 to max dup size
           int location = (int)(Math.random() * ( s1.length()-dupSize));  //location of mutation is no more than size of duplication, else not enough nucleotides to duplicate
           
          s1= s1.substring(0, location) + s1.substring(location, location + dupSize) +s1.substring(location  ,s1.length());
       
       //System.out.println ("location is " + location + " duplication size is " +  dupSize  + " duplication is " + s1.substring (location, location + dupSize ) + " num mutations is " + mutNum ) ;
        
       }
       return s1;
    }
         //delete a small section of DNA of random size up to maxDelSize
  public static String del (Gene g1, double mutationRate, int maxDelSize)  
    {
        
       
        
        String s1 = g1.getCodingStrand();
        if (maxDelSize>s1.length())
        {
            maxDelSize=s1.length();  //don't make deletion size more than or equal to gene!
        }
     
       int mutNum=decideMutNum (mutationRate);
    
         for (int i=0; i<mutNum; i++)
       {
          int delSize= (int) (Math.random()* (maxDelSize-1)+1); // 1 to max del size
           int location = (int)(Math.random() * ( s1.length()-delSize));
           
          s1= s1.substring(0, location) +s1.substring(location +delSize ,s1.length());
       
       //System.out.println ("location is " + location + " deletion size is " +  delSize  + " deletion is " + s1.substring (location, location + delSize ) ) ;
        
       }
       return s1;
    }
     //insertion random sequence of nucleotides at most maxInsertSize
   public static String insertion (Gene g1, double mutationRate, int maxInsertSize)  
    {
        String s1 = g1.getCodingStrand();
       int mutNum=decideMutNum (mutationRate);
    
        for (int i=0; i<mutNum; i++)
       {
          int insertSize= (int) (Math.random()* (maxInsertSize-1)+1); // 1 to maxInsertSize
           int location = (int)(Math.random() * ( s1.length()));
            Gene insert=new Gene(insertSize);  //takes more time/energy?
           
          s1= s1.substring(0, location) + insert.getCodingStrand() + s1.substring(location ,s1.length());
       
      // System.out.println ("location is " + location + " insert size is " +  insertSize  + " insertion is " + insert.getCodingStrand() ) ;
       
       }
       return s1;
    }
 
}



//types: pt mutation, duplication, deletion, insertion       other?