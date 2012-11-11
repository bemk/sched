
public class Test extends GreenThread {
	private char c;
	int ttl = 2;
	
	public Test(char c)
	{
		this(c, 15, 2);
	}
	
	public Test(char c, int priority)
	{
		this (c, priority, 2);
	}
	
	public Test(char c, int priority, int ttl)
	{
		this.ttl = ttl;
		super.priority = priority;
		this.c = c;
	}
	
	@Override
	public void run()
	{
		//System.out.println("RAN!!");
		if (--ttl == 0)
			super.kill();
		System.out.printf("%c", c);
	}
}
