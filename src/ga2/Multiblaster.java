/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga2;

/**
 *
 * @author micha
 */
public class Multiblaster 
{
    private CallBlast[] blasters;
    
    public Multiblaster(int len)
    {
        blasters = new CallBlast[len];
        
        for(int i = 0; i < len; i++)
        {
            blasters[i] = new CallBlast();
        }
    }
    
    // 1 2 chrome windows
    // a b c d e f g genes
    // 1 2 1 2 1 2 1
    
    public void blastAll(Gene[] genes)
    {
        BlasterThread[] run = new BlasterThread[blasters.length];
        
        
        for(int i = 0; i < blasters.length; i++)
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

            for(int j = thread_num; j < genes.length; j += blasters.length)
            {
                genes[j].setIdentity(blasters[thread_num]);
                //System.out.println(genes[j].getAASeq()+" "+genes[j].getIdentity());
            }
            
            finished = true;
        }
        public boolean isFinished()
        {
            return finished;
        }
    }
}

