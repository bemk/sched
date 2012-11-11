
public class TestSignal extends GreenThread {

	public TestSignal()
	{
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (super.signal() != 0)
		{
			super.signal(0);
			System.err.println("HIT!");
		}
		else
		{
			System.err.println("MIS");
		}
	}

}
