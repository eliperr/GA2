package ga2;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
  import java.util.*;
  
public class Translate {
  
//Purpose: Translating from coding strand directionly into amino acid using mapped codon table

      
  public static String translate(String codingStrand)
  {

  
   
    
    String codingStrand2=codingStrand.replaceAll("\\s+","");
    
          String aaseq=""; //amino acid sequence--> going to add to this later
          
        
       
          File goodCodons = new File("goodCodons.txt"); //scanning codon table (goodCodons, text file) --> it is a good one! Thanks Michael! Yeah!
          
          Scanner scnr = null;
          try
          {
           scnr = new Scanner(goodCodons); //making a scanner to look at text of goodCodons
          }
          catch(IOException ex)
          {
              ex.printStackTrace(System.err);
              System.exit(0);
          }
           Map<String, String> codonTable
            = new HashMap<String, String>();
           
           int lineNumber = 1;
           
        while(scnr.hasNext()){
            String codonLine = scnr.next();
            String aaLine = scnr.next();
            
  
        codonTable.put( codonLine, aaLine);  //went through all of goodCodons and mapped each codon to an amino acid, sometimes multiple occurences
     
          
            lineNumber++;
        }

        //System.out.println(codonTable);
      
          
    int k=codingStrand2.indexOf("ATG")+3;  //find index, k to start translating amino acid at start codon ATG (AUG but DNA)
    
       if (k!=2)  //if start codon not found i.e. indexOf is -1
      {    aaseq="M"; //start amino acid with met
    for ( ; k<codingStrand2.length()-3; k=k+3)   //transverse throughout coding strand in intervals of three nucleotides i.e. per codon, stop when have less than 3 nucleotides left 
    {    
      

    
       //exit if reach stop codon
     if (codingStrand2.substring(k,k+3).equals("TAA")|| codingStrand2.substring(k,k+3).equals("TAG") || codingStrand2.substring(k,k+3).equals("TGA"))
    
  { 
      
      break; 
    
  }
       //add string (amino acid) from mapped codonTable to amino acid seq, aaseq
    else  {
           aaseq=aaseq + codonTable.get(codingStrand2.substring(k,k+3));
           
           if(!codonTable.containsKey(codingStrand2.substring(k,k+3)))
           {
               System.out.println(codingStrand2.substring(k,k+3));
           }
        }
    }
     }
     
    //System.out.println(aaseq);
    return aaseq;
 }
  

    
    //stop codons: UAA, UAG, UGA  --> TAA, TAG, TGA 
}
//edges:  if no start codon --> don't go to blast --> asnswer is zero
//what happens if no stop codon?  or start codon?
//

//1.edges, blast, random coding strands 
