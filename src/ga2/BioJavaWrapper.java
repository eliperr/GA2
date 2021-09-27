/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga2;


import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.alignment.template.GapPenalty;
import org.biojava.nbio.alignment.template.PairwiseSequenceAligner;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.core.alignment.template.SequencePair;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;

/**
 *
 * @author micha
 */
public class BioJavaWrapper 
{
    private static BioJavaWrapper active = new BioJavaWrapper();
    
    public BioJavaWrapper()
    {
        active = this;
        matrix = SubstitutionMatrixHelper.getBlosum65();
        penalty = new SimpleGapPenalty();
        
        int gop = 8;
        int extend = 1;
        penalty.setOpenPenalty(gop);
        penalty.setExtensionPenalty(extend);
        
        try
        {
            targetProtein = new ProteinSequence(CallBlast.target);

        }
        catch(CompoundNotFoundException ex)
        {
            ex.printStackTrace(System.err);
            System.exit(0);
        }
    }
    
    private static ProteinSequence targetProtein;
    
    public static void blastAll(Person[] genes)
    {
        for(Person g : genes)
        {
            g.setIdentity(blast(g.getAASeq()));
        }
    }
    
    public static double blast(String aaseq)
    {
        if(aaseq.length() == 0)
        {
            return 0;
        }
        

        
        try
        {
            ProteinSequence given = new ProteinSequence(aaseq);
            
            //return active.getpid(given, targetProtein) * Math.min(aaseq.length(), CallBlast.target.length()) / Math.max(aaseq.length(), CallBlast.target.length());
            
           
           return active.getpid(given, targetProtein);
            
        }
        catch(CompoundNotFoundException ex)
        {
            ex.printStackTrace(System.err);
            System.exit(0);
            return 0;
        }
                    
        
    }
    
    
    public static double blast(String aaseq, String aaseq2)
    {
        if(aaseq.length() == 0)
        {
            return 0;
        }
        

        
        try
        {
            ProteinSequence given = new ProteinSequence(aaseq);
            ProteinSequence given2 = new ProteinSequence(aaseq2);
            
            //return active.getAlignment(given, targetProtein) * Math.min(aaseq.length(), CallBlast.target.length()) / Math.max(aaseq.length(), CallBlast.target.length());
            return active.getAlignment(given, given2);
        }
        catch(CompoundNotFoundException ex)
        {
            ex.printStackTrace(System.err);
            System.exit(0);
            return 0;
        }
                    
        
    }
    
    private SubstitutionMatrix<AminoAcidCompound> matrix;
    private GapPenalty penalty;
    
    public double getAlignment(ProteinSequence s1, ProteinSequence s2)
    {
        PairwiseSequenceAligner<ProteinSequence, AminoAcidCompound> smithWaterman =
                        Alignments.getPairwiseAligner(s1, s2, Alignments.PairwiseSequenceAlignerType.GLOBAL, penalty, matrix);

        
        SequencePair<ProteinSequence, AminoAcidCompound> pair = smithWaterman.getPair();
        //return pair.getNumIdenticals() / pair.getLength();
        
        
        return smithWaterman.getSimilarity();
    }
    //test
    public double getpid(ProteinSequence s1, ProteinSequence s2)
    {
        PairwiseSequenceAligner<ProteinSequence, AminoAcidCompound> smithWaterman =
                        Alignments.getPairwiseAligner(s1, s2, Alignments.PairwiseSequenceAlignerType.GLOBAL, penalty, matrix);

        
        SequencePair<ProteinSequence, AminoAcidCompound> pair = smithWaterman.getPair();
        //eturn pair.getNumIdenticals() / pair.getLength();
        
        
        return pair. getPercentageOfIdentity(true);
    }
    
    
}
