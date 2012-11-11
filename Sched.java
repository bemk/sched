public class Sched
{
	public static void main(String args[])
	{
		new Sched();
	}
	
	public Sched()
	{
		Scheduler s = new PriorityScheduler(16);
		s.threadAdd(new Test('a', 0, 256));
		s.threadAdd(new Test('b', 1, 128));
		s.threadAdd(new Test('c', 3, 64));
		System.err.println("Starting!");
		s.start();
		System.exit(0);
	}
}