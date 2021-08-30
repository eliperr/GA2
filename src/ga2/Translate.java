package ga2;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
  import java.util.*;
  
public class Translate {
  
    private static Map<String, Character> codonTable;
    
    
//Purpose: Translating from coding strand directionly into amino acid using mapped codon table
    //polycistronic mRNA--> translates multiple proteins per coding strand/mRNA

      //return arraylist of all proteins translated
  public static ArrayList<String> translate(String codingStrand)
  {
      
      if(codonTable == null)
      {
          readCodonTable();
      }
      
      String codingStrand2=codingStrand.replaceAll("\\s+","");
      
      //System.out.println(!codingStrand2.equals (""));
      
     ArrayList<String> proteome=new ArrayList<>(); 
     //int count=0;
     outer: while (!codingStrand2.equals (""))
   {
       
  
    
   
    
          String aaseq=""; //amino acid sequence--> going to add to this later
          
        
       
          
      
          
    int k=codingStrand2.indexOf("ATG")+3;  //find index, k to start translating amino acid at start codon ATG (AUG but DNA)
    
       if (k!=2)  //if start codon is found i.e. indexOf is not -1 (+3=2)
      {    aaseq="M"; //start amino acid with met
    for ( ; k<=codingStrand2.length()-3; k=k+3)   //transverse throughout coding strand in intervals of three nucleotides i.e. per codon, stop when have less than 3 nucleotides left 
    {    
      
        //System.out.println(codingStrand2.substring(k,k+3)); 
    
       //exit if reach stop codon
     if (codingStrand2.substring(k,k+3).equals("TAA")|| codingStrand2.substring(k,k+3).equals("TAG") || codingStrand2.substring(k,k+3).equals("TGA"))
    
  { 
        //System.out.println("stop codon " + count);
       proteome.add(aaseq);
      
      //found stop codon so make one aaseq to add to arraylist 
      //System.out.println(codingStrand2);
       codingStrand2=codingStrand2.substring(k+3,codingStrand2.length()); //keep track  of how much of coding strand is left to translate after finishing one protein
       
       //count++; 
       
        continue outer;  //if reach stop codon continue with outer (while) loop
    
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
     //System.out.println("break");
    
    break; 
     }
       
       else   //if no  start codon
       { // System.out.println("break");
    
    break; 
      } ///wont still be in while outer loop?
     
    //System.out.println(aaseq);
   
    
   }
   
   return proteome; 
 }
  
 
    
    //stop codons: UAA, UAG, UGA  --> TAA, TAG, TGA
  
  
  public static void readCodonTable()
  {
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
           codonTable
            = new HashMap<String, Character>();
           
           int lineNumber = 1;
           
        while(scnr.hasNext()){
            String codonLine = scnr.next();
            String aaLine = scnr.next();
            
  
        codonTable.put( codonLine, aaLine.charAt(0));  //went through all of goodCodons and mapped each codon to an amino acid, sometimes multiple occurences
     
          
            lineNumber++;
        }

        //System.out.println(codonTable);
  }
}
//edges:  if no start codon --> don't go to blast --> asnswer is zero
//what happens if no stop codon?  or start codon?
//

//1.edges, blast, random coding strands 





