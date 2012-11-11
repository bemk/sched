
public class PriorityScheduler extends Scheduler {
	private int prios;
	
	private GreenThread[] threads;
	private GreenThread queue = null;
	
	int no_threads = 0;
	int dbg = 0;
	
	public PriorityScheduler(int prios)
	{
		this.prios = prios;
		threads = new GreenThread[prios];
	}
	
	public void threadAdd(GreenThread t)
	{
		t.next(threads[t.priority()]);
		threads[t.priority()] = t;
		no_threads++;
	}
	
	private void schedule()
	{
		queue = threads[0];
		int i = 0;
		while (i < prios-1)
			threads[i] = threads[++i];
		
		threads[prios-1] = null;
	}
	
	private void exec()
	{
		GreenThread next = null;
		for (GreenThread t = queue; t != null; t = next)
		{
			next = t.next();
			
			if (t.killed())
			{
				no_threads--;
				continue;
			}
			
			t.run();
			
			t.next(threads[t.priority()]);
			threads[t.priority()] = t;
		}
	}
	
	public void start()
	{
		while(no_threads != 0)
		{
			//System.err.printf("Itteration: %d, %d threads\n", dbg++, no_threads);
			schedule();
			exec();
		}
	}
}
