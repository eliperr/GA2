package ga2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CallBlast
{
    private WebDriver driver;
    
    public CallBlast()
    {
 
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }
    
    public void end()
    {
        driver.quit();
    }
    
    public double blast(String aaseq)
    {
        if(aaseq.length() == 0)
        {
            return 0;
        }
        
        
        
        //System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Selenium\\chromedriver.exe");
        
        
            
            driver.get("https://blast.ncbi.nlm.nih.gov/Blast.cgi?PAGE=Proteins&PROGRAM=blastp&BLAST_PROGRAMS=blastp&PAGE_TYPE=BlastSearch&BLAST_SPEC=blast2seq&DATABASE=n/a&QUERY=&SUBJECTS=");
            
            WebElement seq1 = driver.findElement(By.name("QUERY"));
            WebElement seq2 = driver.findElement(By.name("SUBJECTS"));
            
            /*
            seq1.sendKeys("MKLVHLESLEVCGQMMLRSYVQIFYWHKFVSPVGKLTSPKGMRKQGLFQWLDSLQIDNLTSPDLQLTVGA\n" +
"VIVEEMRAAIERETGFQCSAGISHNKVLAKLACGLNKPNRQTLVSHGSVPQLFSQMPIRKIRSLGGKLGA\n" +
"SVIEILGIEYMGELTQFTESQLQSHFGEKNGSWLYAMCRGIEHDPVKPRQLPKTIGCSKNFPGKTALATR\n" +
"EQVQWWLLQLAQELEERLTKDRNDNDRVATQLVVSIRVQGDKRLSSLRRCCALTRYDAHKMSHDAFTVIK\n" +
"NCNTSGIQTEWSPPLTMLFLCATKFSASAPSSSTDITSFLSSDPSSLPKVPVTSSEAKTQGSGPAVTATK\n" +
"KATTSLESFFQKAAERQKVKEASLSSLTAPTQAPMSNSPSKPSLPFQTSQSTGTEPFFKQKSLLLKQKQL\n" +
"NNSSVSSPQQNPWSNCKALPNSLPTEYPGCVPVCEGVSKLEESSKATPAEMDLAHNSQSMHASSASKSVL\n" +
"EVTQKATPNPSLLAAEDQVPCEKCGSLVPVWDMPEHMDYHFALELQKSFLQPHSSNPQVVSAVSHQGKRN\n" +
"PKSPLACTNKRPRPEGMQTLESFFKPLTH");
*/
            seq1.sendKeys(aaseq);
            
              String target = ("MWADDAKKLCPDLLLAQVRESRGKANLTKYREASVEVMEIMSRFAVIERASIDEAYVDLTSAVQERLQKLQGQPISADLLPSTYIEGLPQGPTTAEETVQKEGMRKQGLFQWLDSLQIDNLTSPDLQLTVGAVIVEEMRAAIERETGFQCSAGISHNKVLAKLACGLNKPNRQTLVSHGSVPQLFSQMPIRKIRSLGGKLGASVIEILGIEYMGELTQFTESQLQSHFGEKNGSWLYAMCRGIEHDPVKPRQLPKTIGCSKNFPGKTALATREQVQWWLLQLAQELEERLTKDRNDNDRVATQLVVSIRVQGDKRLSSLRRCCALTRYDAHKMSHDAFTVIKNCNTSGIQTEWSPPLTMLFLCATKFSASAPSSSTDITSFLSSDPSSLPKVPVTSSEAKTQGSGPAVTATKKATTSLESFFQKAAERQKVKEASLSSLTAPTQAPMSNSPSKPSLPFQTSQSTGTEPFFKQKSLLLKQKQLNNSSVSSPQQNPWSNCKALPNSLPTEYPGCVPVCEGVSKLEESSKATPAEMDLAHNSQSMHASSASKSVLEVTQKATPNPSLLAAEDQVPCEKCGSLVPVWDMPEHMDYHFALELQKSFLQPHSSNPQVVSAVSHQGKRNPKSPLACTNKRPRPEGMQTLESFFKPLTH");
            
              seq2.sendKeys(target);
      
              /*
        try
       {
        Thread.sleep(1000*15);
       }
       catch(Exception ex)
       {
           ex.printStackTrace(System.err);
       }
*/
              
       seq2.submit();
       
       boolean found = false;
       
       for(int x = 0; x < 6; x++)
       {
           if(found)
           {
               break;
           }
           
            try
            {
             Thread.sleep(500);
            }
            catch(Exception ex)
            {
                ex.printStackTrace(System.err);
            }


            //WebElement table = driver.findElement(By.id("dscTable"));

            //System.out.println(table);
            try
            {
                //WebElement testElement = driver.findElement(By.id("ui-nciexternallink-4"));
                
                //found = true;
                 WebElement id = driver.findElement(By.xpath("//*[@id=\"ui-ncbipopper-5\"]"));
                 String i = id.getAttribute("seqid");

                 //System.out.println(i);

                 WebElement search = driver.findElement(By.xpath("//*[@id=\"dtr_"+i+"\"]/td[10]"));


            //System.out.println(search);
            //System.out.println(search.getText());

                 String s = search.getText();

                 // this is the % identity
                 double pctIdentity = Double.parseDouble(s.substring(0, s.length()-1));

                 //System.out.println(pctIdentity+" "+aaseq.length()+" "+target.length());
                 double output = pctIdentity * aaseq.length() / target.length();

                 return output;
            }
            catch(Exception ex)
            {


            }
        }
    
       return 0;
    }
}