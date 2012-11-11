
public class Sys {
	private static Sys sys = null;
	private Scheduler sched = null;

	private Sys(Scheduler s)
	{
		this.sched = s;
	}

	public static Sys getInstance(Scheduler s)
	{
		if (s == null && sys == null)
			return null;

		if (sys == null)
			sys = new Sys(s);

		return sys;
	}

	public void threadAdd(GreenThread t)
	{
		sched.threadAdd(t);
	}

	public void start()
	{
		sched.start();
	}
}
