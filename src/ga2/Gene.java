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
public class  Gene {
    
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
    
    public static String ptMut(Gene g1, double mutationRate)
    {
        String s1 = g1.getCodingStrand();
       double mutRate=mutationRate;  //try just 1
       
       boolean hasMutation=Math.random() <= mutationRate;
       
    
       
   
        int location = (int)(Math.random() * ( s1.length()));
           //try to decide which point mutation but cannot be the same nucleotide
       String CGAT="CGAT";
        int index=CGAT.indexOf(s1.charAt(location));
         char changeNuc=CGAT.charAt( (index + (int) Math.floor(Math.random() *3 + 1)) %4); 
          //System.out.println(changeNuc);
        //System.out.println("ptMut "+ location);
        String output = s1.substring(0, location) + changeNuc +  s1.substring(location + 1 ,s1.length() );
        
    
        return output;
    }
    
    
}
