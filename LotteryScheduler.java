import java.util.ArrayList;
import java.util.Random;

/*
 *  Sched - The brain damaged scheduler
 *  Copyright (C) 2012  Bart Kuivenhoven
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class LotteryScheduler extends Scheduler{
	private Random r = new Random();
	private ArrayList<GreenThread> threads = null;
	private GreenThread signaled = null;
	
	public LotteryScheduler(int priorities, boolean realtime)
	{
		super.realtime = realtime;
		threads = new ArrayList<GreenThread>();
	}
	
	private void exec()
	{
		if (super.realtime && signaled != null)
		{
			signaled.run();
			signaled = null;
			return;
		}

		int index = r.nextInt(threads.size());
		GreenThread t = threads.get(index);
		if (t.killed())
		{
			while (threads.contains(t))
			{
				threads.remove(t);
			}
			return;
		}
		else
			t.run();
	}
	public GreenThread getThreadByID(int id)
	{
		for (GreenThread t : threads)
		{
			if (t.pid() == id)
				return t;
		}
		return null;
	}
	
	@Override
	public void start() {
		while(threads.size() != 0)
		{
			exec();
		}
	}

	@Override
	public int threadAdd(GreenThread t) {
		t.pid(super.next_pid());
		for (int i = 0; i < t.priority(); i++)
			threads.add(t);
		return t.pid();
	}
}
