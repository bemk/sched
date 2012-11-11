
public class TestNewThread extends GreenThread {

	private int forks;
	private char a = 'a';
	
	public TestNewThread(int forks, int priority)
	{
		this.priority(priority);
		this.forks = forks;
	}
	
	@Override
	public void run() {
		if (forks -- != 0)
			Sys.getInstance(null).threadAdd(new Test((char) ('a'+forks), 0, 20));
		else
			this.kill();
	}

}
