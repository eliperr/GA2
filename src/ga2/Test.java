/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga2;

import java.util.Arrays;

/**
 *
 * @author michael
 */
public class Test {
    
    
    
     public static void main(String[] args) 
    {   
       /* 
        System.out.println(BioJavaWrapper.blast(CallBlast.target.substring(100)));
        
        System.exit(0);
        
        //main method: test generations having children
        //Population test = new Population(20, 3135);
        Population test = new Population(300, 12000);
        int gen=500;
        
       double[] best = new double [gen] ; //keep track of to determine plateau
       double[] mean = new double [gen] ;
        double[] std = new double [gen] ;
        
        
         int prev=0;*/
       
       /*
        for(int i = 0; i < gen; i++)
        {
            System.out.println("***** GENERATION "+i);
            //test.nextGenPartialReplace(1,2);  //1 is mutation rate, 2 is selectNum 
            test.nextGen(1, 2);
            //test.replaceWorst(1, 2);
             //best[i]=test.printTheBestPerson();
             //mean[i]=test.printTheMeanPerson(); 
             //std[i]=test.printTheSTDPerson(); 
             printPlateau("best", best,i);
             printPlateau("mean", mean,i);
            printPlateau("std", std,i);
        }
        
    */
        
        
        
        //test biojava local alignment 
        //System.out.println ( "replace gly with trp: " + BioJavaWrapper.blast(CallBlast.target.replaceAll("G","W")));
        //System.out.println ( "replace trp with gly: " + BioJavaWrapper.blast(CallBlast.target.replaceAll("W","G")));
        //System.out.println ( "replace ala with trp: " + BioJavaWrapper.blast(CallBlast.target.replaceAll("A","W")));
        // System.out.println ( "replace trp with ala: " + BioJavaWrapper.blast(CallBlast.target.replaceAll("W","A")));
        //System.out.println ( "bad aaseq: " + BioJavaWrapper.blast("oubzxj")); 
        //System.out.println ( "lower case: " + BioJavaWrapper.blast(CallBlast.target.toLowerCase()));
        //System.out.println ( "half target: " + BioJavaWrapper.blast(CallBlast.target.substring(0,CallBlast.target.length()/2)));
        //System.out.println ( "inverted half target: " + BioJavaWrapper.blast(CallBlast.target.substring(CallBlast.target.length()/2,CallBlast.target.length()) + CallBlast.target.substring(0,CallBlast.target.length()/2) ));
     /*System.out.println ( "The target idendity: " + BioJavaWrapper.blast(CallBlast.target)); //should be 1
      System.out.println ("evolved aaseq test: " + BioJavaWrapper.blast( "MVPRIAAAYVYVERPTTRNWNLALLRFVHVLLVAFANVNGRGDKMTSIRGTDYRVWPELADSMKWVLLRHRTHGRYKIGDLGCPKVEPCLSLGNLGTLGRLGVVKFSMDLTILRTSHCLKTKMTFTIITDRQRKQGRNILSCGLNKPTSPVGFESPSRGKFCGVIKVSAHYTSNLLVITAPHYLWTEQRNVPSTVEWRRRFFLVLSVGGWDILGSSSSRGSSFTFCNCTLRASHVGSPQRNTTELVQLILVYKNRLEQLCQLWVTCTSWSPHGIILRLCSSTGDNHRAALTQAVNPRKDRLTLGTLSPRLCNECYTGCQFFVRIAKTLEMRWTIVGDLSRMVVIRTALDVKYRTWSIIDLVVISIRTNVFFIIVYQNLLIRRVCCSDIPRSPDVKHFVEIDRPMGVARNKPERYYTLSTASHIDSGNLSVNKNGHWMLGKSGWQFLALAITFLAKEGIGRLGRDSVYGYCVIDVRCLKRSKSGRSLQLCKVDHSPSYKRPEMRLGSSEAGDMGEDNGAPQLIGRAWELNRLITLSEVAKESCTCIIANLFTIQTMTSPLSIHVRGLDMRQRRPLMIYDRKTRGHGATVKQSNRALRTPRPLSALQSRSPADGVPSAHWYEIEARYCPQRGLSITLSRIAQSGPRTVRMCIILSRSAQIVSYWGNLSVVELIPQSSPDVFSRRRNNFNFKCHKVRTLQCSCAASEFLSVCPWYRFDDVADNFSQQFTGCSHHHGRNVWEQLVTPRTSTLKKFSIIRESLRVIKSLHRREPHVQDIRTPQFCVIERERAQDGRIRDTSNPRPSYVEAKTGVADYLVQSYTMCKGCCPSIRCAQDAFSFSMRHEQGQRLHFRLTPELGSGPNPSVYARMKNRCRWTNRVTVYTKLIYIHQVASKPGGLNFRRRLSSGLKSVRPAPSYFPLESFRIMFRSAGRMHLSLNIRNGHCSRPGICASPCSLPWFTNQKGACKAQPNPARSTMMSYTRHKFSLQIHMVTSSMHREGRCELLRHGQGQGCLGAFSWRLWEANLIVGILVLTQISLVYEHFVGYELFDNNGMHFEDPRWKRWKSRGTPRAIVLGEVGEAGRLRSADCVGILESPAGEKPIRTGGVRLGDRINAGDGPTRLRTPPRRLLDIVITFYLSCRYSNPCYPYSRKAINHTHDSKRKVVPPYTRTPLVISIYTMYSNPIPCRSQANHAKHGVHQMKMTKMTLAPGKTSSLTKGAATVKTNRRLPTPPYLFIVYSCQFISAGTQSPSRPSSETYYRFWPATKLLLNSVMFSSRCHGTYMLIEAYFLKVQLRPTRHPHGNSEGQLGLRTRLGSTCQRLRFEIFTSGYLWPESSQITEITTRTVRGGNNTWDCHQGFAVPAATGVLVFLSHCIRVNIPLARGLARARLPISGPFGSRSVLGLDSTQS"));
      System.out.println ("diff size but same local alignment " + BioJavaWrapper.blast(CallBlast.target + "APRN")); //should still be 1 for local alignment
      System.out.println ("2 X target  " + BioJavaWrapper.blast(CallBlast.target + CallBlast.target)); //should still be 1 for local alignment
      System.out.println ("3 X target  " + BioJavaWrapper.blast(CallBlast.target + CallBlast.target + CallBlast.target));
      System.out.println ("string insertion in middle " +  BioJavaWrapper.blast(CallBlast.target.substring (0,50) + "APRNGGGGGGHHHHHLLLLLLLL"+  CallBlast.target.substring(50,CallBlast.target.length())));  //should be less than 1
      */  //System.out.println(Arrays.stream(test.getGene()).mapToDouble(Person::getIdentity) .average());
        
        
        
         /*double mutRate=1.9;
        int mut= (int) Math.floor(mutRate); 
        if (Math.random() <= mutRate-mut)
        { mut++; }    
        System.out.println(mut); */
        
        //test mutation types
        /*Gene g=new Gene (5); 
        System.out.println(g.getCodingStrand());   
        String val=Gene.dup(g,1.5,2);             //valine 
         System.out.println(val);       
        */
        
        /*
        System.exit(0);
        Population p=new Population (10,3135 );
        p.printThePeople();
        */
    }
    public static void printPlateau (String val, double[] vals, int i )   
    {
        
      
             if (i>=50)  //test plateau      arbitratry numbers 
             { int prev=i-50;
                 if ( vals[i]<=vals[prev] + 0.001  && vals[i]>=vals[prev] -0.001 )  //if pretty much the same over 50 generations probably plateauted
                 { 
                 System.out.println( val +  "plateau from gen " + prev + "to" + i + " is " + vals[i]); 
                 }
                 
             }      
                   
        
        
    }        
                     
     
}


//callblast.target   //break  //simil and idend.  package    global alignment