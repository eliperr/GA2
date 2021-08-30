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
        translate(people, 0, people.length);
    }
    
    public static void translate(Person[] people, int start, int end)
    {
        if(!Person.WAIT_FOR_IDENTITY)
        {
            return;
        }
        
        if(active == null)
        {
            active = new MultiTranslate(4);
        }
        
        active.translate_helper(people, start, end);
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
    
    public void translate_helper(Person[] people, int start, int end)
    {
        for(MultiTranslateThread t : threads)
        {
            t.setPeople(people, start, end);
        }
        
        for(MultiTranslateThread r : threads)
        {
            Thread t = new Thread(r);
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
    
    
    class MultiTranslateThread implements Runnable
    {
        private int offset;
        private boolean finished;
        private int num_threads;
        private Person[] people;
        private int start, end;
        
        public MultiTranslateThread(int offset, int num_threads)
        {
            this.offset = offset;
            this.num_threads = num_threads;
        }
        
        public void setPeople(Person[] people, int start, int end)
        {
            this.people = people;
            this.start = start;
            this.end = end;
        }
        
        public boolean isFinished()
        {
            return finished; 
        }
        
        public void run()
        {
            finished = false;
            
            for(int i = start+offset; i < end; i += num_threads)
            {
                people[i].translate();
            }
            
            finished = true;
        }
    }
}
