/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga2;
/*//MKLVHLESLEVCGQMMLRSYVQIFYWHKFVSPVGKLTSPKGMRKQGLFQWLDSLQIDNLTSPDLQLTVGA
VIVEEMRAAIERETGFQCSAGISHNKVLAKLACGLNKPNRQTLVSHGSVPQLFSQMPIRKIRSLGGKLGA
SVIEILGIEYMGELTQFTESQLQSHFGEKNGSWLYAMCRGIEHDPVKPRQLPKTIGCSKNFPGKTALATR
EQVQWWLLQLAQELEERLTKDRNDNDRVATQLVVSIRVQGDKRLSSLRRCCALTRYDAHKMSHDAFTVIK
NCNTSGIQTEWSPPLTMLFLCATKFSASAPSSSTDITSFLSSDPSSLPKVPVTSSEAKTQGSGPAVTATK
KATTSLESFFQKAAERQKVKEASLSSLTAPTQAPMSNSPSKPSLPFQTSQSTGTEPFFKQKSLLLKQKQL
NNSSVSSPQQNPWSNCKALPNSLPTEYPGCVPVCEGVSKLEESSKATPAEMDLAHNSQSMHASSASKSVL
EVTQKATPNPSLLAAEDQVPCEKCGSLVPVWDMPEHMDYHFALELQKSFLQPHSSNPQVVSAVSHQGKRN
PKSPLACTNKRPRPEGMQTLESFFKPLTH /*/
        
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.Alignments.PairwiseSequenceAlignerType;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.alignment.template.GapPenalty;
import org.biojava.nbio.alignment.template.PairwiseSequenceAligner;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.core.alignment.template.SequencePair;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// testing

public class GA2  {

    public static void main(String[] args) throws Exception 
    {
        Test.main(args);
        
        /*
        ProteinSequence s1 = new ProteinSequence("VEVMEIMSRFAVBCIERASIDEAYVD");
        ProteinSequence s2 = new ProteinSequence(CallBlast.target);
        
        SubstitutionMatrix<AminoAcidCompound> matrix = SubstitutionMatrixHelper.getBlosum65();

		GapPenalty penalty = new SimpleGapPenalty();

		int gop = 8;
		int extend = 1;
		penalty.setOpenPenalty(gop);
		penalty.setExtensionPenalty(extend);


		PairwiseSequenceAligner<ProteinSequence, AminoAcidCompound> smithWaterman =
				Alignments.getPairwiseAligner(s1, s2, PairwiseSequenceAlignerType.LOCAL, penalty, matrix);

		SequencePair<ProteinSequence, AminoAcidCompound> pair = smithWaterman.getPair();

                
                System.out.println(pair.getPercentageOfIdentity(true));
		//System.out.println(pair.toString(60));
             */   
                
         //Test.main(args);
         //System.exit(0); 
        
         /*
          Gene g=new Gene(3135);
          System.out.println(g.getIdentity());
          System.out.println(g.getAASeq());
          String gString=Gene.ptMut(g,1);
                  
                  
          Gene g2 = new Gene(3135);
          System.out.println(g2.getIdentity());
          System.out.println(g2.getAASeq());
          String gString2=Gene.ptMut(g2,1);
          
          
          Gene child = new Gene(Gene.crossover(gString, gString2));
          System.out.println(child.getIdentity());
          System.out.println(child.getAASeq());
         
        //String codingStrand=" A AAG ATG GGC CGC CGA TAG TTTT"; 
        String template = ""+
"GTCAACGGCCCTTCGCAGCGGGCGCGCTGTCAGACCTCAGTCTGGCGGCTGCATTGCTGGGCGCGCCGCT\n" +
"CTCGTCTGATCCCTGCTGGGGACGGTTGCCCGGGCAGGATCCTTTACGATCCCTTCTCGGTTTCTCCGTC\n" +
"GTCACAGGGAATAAATCTCGCTCGAAACTCACTGGACCGCTCCTAGAAAGGCGAAAAGATATTCAGGAGC\n" +
"CCTTCCATTTTCCTTCCAGTAGGCACCGAACCCAGCATTTTCGGCAACCGCTGCTGGCAGTTTTGCCAGG\n" +
"TGTTTGTTACCTTGAATAATTGCAGTGAGTTATCAAGCTCGTGCATTTGGAGTCACTAGAAGTATGTGGG\n" +
"CAGATGATGCTAAGAAGTTATGTCCAGATCTTCTACTGGCACAAGTTCGTGAGTCCCGTGGGAAAGCTAA\n" +
"CCTCACCAAGTACCGGGAAGCCAGTGTTGAAGTGATGGAGATAATGTCTCGTTTTGCTGTGATTGAACGT\n" +
"GCCAGCATTGATGAGGCTTACGTAGATCTGACCAGTGCTGTACAAGAGAGACTACAAAAGCTACAAGGTC\n" +
"AGCCTATCTCGGCAGACTTGTTGCCAAGCACTTACATTGAAGGGTTGCCCCAAGGCCCTACAACGGCAGA\n" +
"AGAGACTGTTCAGAAAGAGGGGATGCGAAAACAAGGCTTATTTCAATGGCTCGATTCTCTTCAGATTGAT\n" +
"AACCTCACCTCTCCAGACCTGCAGCTCACCGTGGGAGCAGTGATTGTGGAGGAAATGAGAGCAGCCATAG\n" +
"AGAGGGAGACTGGTTTTCAGTGTTCAGCTGGAATTTCACACAATAAGGTCCTGGCAAAACTGGCCTGTGG\n" +
"ACTAAACAAGCCCAACCGCCAAACCCTGGTTTCACATGGGTCAGTCCCACAGCTCTTCAGCCAAATGCCC\n" +
"ATTCGCAAAATCCGTAGTCTTGGAGGAAAGCTAGGGGCCTCTGTCATTGAGATCCTAGGGATAGAATACA\n" +
"TGGGTGAACTGACCCAGTTCACTGAATCCCAGCTCCAGAGTCATTTTGGGGAGAAGAATGGGTCTTGGCT\n" +
"ATATGCCATGTGCCGAGGGATTGAACATGATCCAGTTAAACCCAGGCAACTACCCAAAACCATTGGCTGT\n" +
"AGTAAGAACTTCCCAGGAAAAACAGCTCTTGCTACTCGGGAACAGGTACAATGGTGGCTGTTGCAATTAG\n" +
"CCCAGGAACTAGAGGAGAGACTGACTAAAGACCGAAATGATAATGACAGGGTAGCCACCCAGCTGGTTGT\n" +
"GAGCATTCGTGTACAAGGAGACAAACGCCTCAGCAGCCTGCGCCGCTGCTGTGCCCTTACCCGCTATGAT\n" +
"GCTCACAAGATGAGCCATGATGCATTTACTGTCATCAAGAACTGTAATACTTCTGGAATCCAGACAGAAT\n" +
"GGTCTCCTCCTCTCACAATGCTTTTCCTCTGTGCTACAAAATTTTCTGCCTCTGCCCCTTCATCTTCTAC\n" +
"AGACATCACCAGCTTCTTGAGCAGTGACCCAAGTTCTCTGCCAAAGGTGCCAGTTACCAGCTCAGAAGCT\n" +
"AAGACCCAGGGAAGTGGCCCAGCGGTGACAGCCACTAAGAAAGCAACCACGTCTCTGGAATCATTCTTCC\n" +
"AAAAAGCTGCAGAAAGGCAGAAAGTTAAAGAAGCTTCGCTTTCATCTCTTACTGCTCCCACTCAGGCTCC\n" +
"CATGAGCAATTCACCATCCAAGCCCTCATTACCTTTTCAAACCAGTCAAAGTACAGGAACTGAGCCCTTC\n" +
"TTTAAGCAGAAAAGTCTGCTTCTAAAGCAGAAACAGCTTAATAATTCTTCAGTTTCTTCCCCCCAACAAA\n" +
"ACCCATGGTCCAACTGTAAAGCATTACCAAACTCTTTACCAACAGAGTATCCAGGGTGTGTCCCTGTTTG\n" +
"TGAAGGGGTGTCGAAGCTAGAAGAATCCTCTAAAGCAACTCCTGCAGAGATGGATTTGGCCCACAACAGC\n" +
"CAAAGCATGCACGCCTCTTCAGCTTCCAAATCTGTGCTGGAGGTGACTCAGAAAGCAACCCCAAATCCAA\n" +
"GTCTTCTAGCTGCTGAGGACCAAGTGCCCTGTGAGAAGTGTGGCTCCCTGGTACCGGTATGGGATATGCC\n" +
"AGAACACATGGACTATCATTTTGCATTGGAGTTGCAGAAATCCTTTTTGCAGCCCCACTCTTCAAACCCC\n" +
"CAGGTTGTTTCTGCCGTATCTCATCAAGGCAAAAGAAATCCCAAGAGCCCTTTGGCCTGCACTAATAAAC\n" +
"GCCCCAGGCCTGAGGGCATGCAAACATTGGAATCATTTTTTAAGCCATTAACACATTAGTGCTGCCCTCA\n" +
"GGCTTGCCTGTAGGATTTAATATTTTTTATCTTTACAGATCTTTATCTTTAATATTTTATCTTTACAGAT\n" +
"TTCCCTGAGAAAGGGAATTATGAAATTTTTAATACAAAAAATAATCCATTTAGGTGCTGAGTTACGGTCC\n" +
"CATCTCTTCACAGGCATGGATTCTAATCCCACTGCTGACAGAGATGTAAAAATTCATCCTACCAGAGTTT\n" +
"TTAATCTTTAGCATTTAGGGAGGCAGTGTCATAAAGTAAAAAGTGTGTGGGCCTTGGAGTCTAAGAGACG\n" +
"TGGTTGCAAACTTAGCTCTGGTTATTGCAATGAGGGCCTTGAACAAGTCATTTTCTTCACATTCTCATCT\n" +
"GTAAAATGGAGATAATACCTTACAGATTATTGCAGATTAATAACAATGTATTCAAATTATGTAACTCGGC\n" +
"CGGGTACAATGGCTCACGCCTGTAATCCTAACACTTTGGGAGGCCGAGGCAGACAGATCACCTGAGGTCA\n" +
"GGAGTTTGAGACCAGCCTGGCCAACATGGCAAAACCATCTCTACTAAAAATAGAAAAATTAGCCAGGCAC\n" +
"GTTCCAGGCACCTGTGATCCCAGCTACTTAGAGGCTGAGGCAGAAGAATTGCTTTAACCTTGGAGGCGGA\n" +
"GGTTGCATTGAGCTGAGATCATGCTAGTGCGCTCCAGCCTGGGCAACAGAGCGAGACTTCATCTCAGAAA\n" +
"ATAAAAAATAGGGGCCAGGCACAGTGGCTCATACCTGTAATGCCAGCACTTTGGGAGGCCAAGGCGGGCA\n" +
"GATCACGAGGTCAGGAGTTTCAGACCAATATGGTGAAACCCCATCTCTACTAAAA";
         */
        /*
        String codingStrand = "";
        
        for(int i = template.length()-1; i >= 0; i --)
        {
            char c = ' ';
            switch(template.charAt(i))
            {
                case 'A': c = 'T'; break;
                case 'T': c = 'A'; break;
                case 'C': c = 'G'; break;
                case 'G': c = 'C'; break;
            }
            if(c != ' ')
            codingStrand += c;
        }
        */
        
        /*
        String codingStrand = template;
        
        System.out.println(codingStrand.replaceAll("\\s+","").length());
        
        //Gene test = new Gene(codingStrand);
        //System.out.println(test.getIdentity());
        
        //System.out.println(codingStrand);
       */
        /*
        String aa = Translate.translate(codingStrand);
        
        System.out.println(aa);
        double pct = CallBlast.blast(aa);
        System.out.println(pct);
*/
        
    }
    
    
}