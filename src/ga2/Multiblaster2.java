/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga2;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author micha
 */
public class Multiblaster2 
{
    public static final int num_threads = 4;
    
    private static Multiblaster2 active;
    
    private WebDriver driver;
    
    private String[] tabs;
    
    public Multiblaster2(int len)
    {
        active = this;
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        
        for(int i = 0; i < num_threads; i++)
        {
            ((JavascriptExecutor)driver).executeScript("window.open()");
            
            /*
            String baseUrl = "http://www.google.co.uk/";
    driver.get(baseUrl);
    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
    */
            /*
            String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"tab"+i);
driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpeninNewTab);
            */
            
            //driver.ExecuteScript("window.open('your URL', 'tab"+"i"+"');");
            
        }
        
        tabs = new String[num_threads];
        
        ArrayList<String> tabs_ = new ArrayList<String>(driver.getWindowHandles());
    //driver.switchTo().window(tabs.get(1));
        for(int i = 0; i < tabs.length; i++)
        {
            tabs[i] = tabs_.get(i);
        }
        
        
    }
    
    
    public static void end()
    {
        active.endHelp();
        active = null;
    }
    
    private void endHelp()
    {
        driver.quit();
    }
    
    // 1 2 chrome windows
    // a b c d e f g genes
    // 1 2 1 2 1 2 1
    
    public static void blastAll(Gene[] genes)
    {
        if(active == null)
        {
            active = new Multiblaster2(num_threads);
        }
        active.blastAllHelp(genes);
    }
    private void blastAllHelp(Gene[] genes)
    {
        
        BlasterThread[] run = new BlasterThread[num_threads];
        
        
        for(int i = 0; i < num_threads; i++)
        {
            final int thread_num = i;
            
            run[i] = new BlasterThread(thread_num, genes);
        }
        
        for(int i = 0; i < run.length; i++)
        {
            Thread t = new Thread(run[i]);
            t.start();
        }
        
        outer:do
        {
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception ex){}
            
            
            
            for(BlasterThread t : run)
            {
                if(!t.isFinished())
                {
                    continue outer;
                }
                
            }
            
            break;
        }
        while(true);
    }
    
    class BlasterThread implements Runnable
    {
        final String target = ("MWADDAKKLCPDLLLAQVRESRGKANLTKYREASVEVMEIMSRFAVIERASIDEAYVDLTSAVQERLQKLQGQPISADLLPSTYIEGLPQGPTTAEETVQKEGMRKQGLFQWLDSLQIDNLTSPDLQLTVGAVIVEEMRAAIERETGFQCSAGISHNKVLAKLACGLNKPNRQTLVSHGSVPQLFSQMPIRKIRSLGGKLGASVIEILGIEYMGELTQFTESQLQSHFGEKNGSWLYAMCRGIEHDPVKPRQLPKTIGCSKNFPGKTALATREQVQWWLLQLAQELEERLTKDRNDNDRVATQLVVSIRVQGDKRLSSLRRCCALTRYDAHKMSHDAFTVIKNCNTSGIQTEWSPPLTMLFLCATKFSASAPSSSTDITSFLSSDPSSLPKVPVTSSEAKTQGSGPAVTATKKATTSLESFFQKAAERQKVKEASLSSLTAPTQAPMSNSPSKPSLPFQTSQSTGTEPFFKQKSLLLKQKQLNNSSVSSPQQNPWSNCKALPNSLPTEYPGCVPVCEGVSKLEESSKATPAEMDLAHNSQSMHASSASKSVLEVTQKATPNPSLLAAEDQVPCEKCGSLVPVWDMPEHMDYHFALELQKSFLQPHSSNPQVVSAVSHQGKRNPKSPLACTNKRPRPEGMQTLESFFKPLTH");

        private int thread_num;
        private Gene[] genes;
        private boolean finished;
        
        public BlasterThread(int thread_num, Gene[] genes)
        {
            this.thread_num = thread_num;
            this.genes = genes;
            finished = false;
        }
        
        public void run()
        {

            for(int j = thread_num; j < genes.length; j += num_threads)
            {
                genes[j].setIdentity(blast(genes[j].getAASeq()));
                //System.out.println(genes[j].getAASeq()+" "+genes[j].getIdentity());
            }
            
            finished = true;
        }
        
        public double blast(String aaseq)
        {
        
            if(aaseq.length() == 0)
            {
                return 0;
            }



            //System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Selenium\\chromedriver.exe");

            synchronized(driver)
            {
                driver.switchTo().window(tabs[thread_num]);
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

                  
                  seq2.sendKeys(target);
                  
                  seq2.submit();
            }
                  

           

           for(int x = 0; x < 10; x++)
           {
                try
                {
                 Thread.sleep(500);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace(System.err);
                }

                double output = 0.0;

                //WebElement table = driver.findElement(By.id("dscTable"));

                //System.out.println(table);
                try
                {
                    synchronized(driver)
                    {
                        driver.switchTo().window(tabs[thread_num]);

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
                     output = pctIdentity * aaseq.length() / target.length();
                    }

                     return output;
                }
                catch(Exception ex)
                {


                }
            }

           return 0;

        }
        public boolean isFinished()
        {
            return finished;
        }
    }
}

