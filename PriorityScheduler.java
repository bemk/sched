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

public class PriorityScheduler extends Scheduler {
	private int prios;
	
	private GreenThread[] threads;
	private GreenThread queue = null;
	
	int no_threads = 0;
	int dbg = 0;
	
	public PriorityScheduler(int prios, boolean realtime)
	{
		super.realtime = realtime;
		this.prios = prios;
		threads = new GreenThread[prios];
	}
	
	public int threadAdd(GreenThread t)
	{
		t.pid(super.next_pid());
		t.next(threads[t.priority()]);
		threads[t.priority()] = t;
		no_threads++;
		return t.pid();
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
			if (super.realtime && super.signaled != null)
			{
				super.signaled.run();
				super.signaled = null;
			}
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

	public GreenThread getThreadByID(int id)
	{
		for (int i = 0; i < prios; i++)
		{
			for (GreenThread t = threads[i]; t != null; t = t.next())
			{
				if (t.pid() == id)
					return t;
			}
		}
		for (GreenThread t = queue; t != null; t = t.next())
		{
			if (t.pid() == id)
				return t;
		}
		return null;
	}
}
