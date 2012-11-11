
public abstract class GreenThread {
	protected int priority;
	private GreenThread next;
	private boolean killed = false;
	
	public GreenThread next()
	{
		return this.next;
	}
	
	public boolean killed()
	{
		return this.killed;
	}
	
	protected void kill()
	{
		this.killed = true;
	}
	
	public void next(GreenThread next)
	{
		this.next = next;
	}
	
	public int priority()
	{
		return this.priority;
	}
	
	protected void priority(int priority)
	{
		this.priority = priority;
	}
	
	public abstract void run();
}
