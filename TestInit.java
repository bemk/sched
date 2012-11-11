
public class TestInit extends GreenThread {
	
	private int child = 0;
	int cnt = 0;
	Sys sys;
	
	public TestInit(int prio)
	{
		sys = Sys.getInstance(null);
		child = sys.threadAdd(new TestSignal());
		sys.getThreadByID(child).priority(prio);
		this.priority(4);
	}
	
	@Override
	public void run() {
		if (cnt++ > 0x80)
		{
			sys.getThreadByID(child).kill();
			super.kill();
		}
		else
		{
			System.err.println("FIRE!");
			sys.signal(child, 1);
		}
	}

}
