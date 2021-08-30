/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga2;

/**
 *
 * @author michael
 */
public class MultiTranslate 
{
    private static MultiTranslate active;
    
    public static void translate(Person[] people)
    {
        if(active == null)
        {
            active = new MultiTranslate(4);
        }
        
        active.translate_helper(people);
    }
    
    
    
    private int num_threads;

    private MultiTranslateThread[] threads;
    
    public MultiTranslate(int num_threads)
    {
        
        threads = new MultiTranslateThread[num_threads];
        
        for(int i = 0; i < threads.length; i++)
        {
            threads[i] = new MultiTranslateThread(i, num_threads);
        }
    }
    
    public void translate_helper(Person[] people)
    {
        for(MultiTranslateThread t : threads)
        {
            t.setPeople(people);
        }
        
        for(Thread t : threads)
        {
            t.start();
        }
        
        outer:do
        {
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception ex){}
            
            
            
            for(MultiTranslateThread t : threads)
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
    
    
    class MultiTranslateThread extends Thread
    {
        private int offset;
        private boolean finished;
        private int num_threads;
        private Person[] people;
        
        public MultiTranslateThread(int offset, int num_threads)
        {
            this.offset = offset;
            this.num_threads = num_threads;
        }
        
        public void setPeople(Person[] people)
        {
            this.people = people;
        }
        
        public boolean isFinished()
        {
            return finished; 
        }
        
        public void run()
        {
            finished = false;
            
            for(int i = offset; i < people.length; i += num_threads)
            {
                people[i].translate();
            }
            
            finished = true;
        }
    }
}
