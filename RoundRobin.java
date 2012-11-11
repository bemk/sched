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
public class RoundRobin extends Scheduler {
	
	private int no_threads = 0;
	private GreenThread threads = null;
	private GreenThread signaled = null;

	public RoundRobin(int prios, boolean realtime)
	{
		super.realtime = realtime;
		// We're just gonna leave priorities for what they are
	}

	private void exec()
	{
		GreenThread next = null;
		GreenThread prev = null;
		prev = threads;
		for (GreenThread t = threads; t != null; t = next)
		{
			if (super.realtime && signaled != null)
			{
				signaled.run();
			}

			next = t.next();
			if (t.killed())
			{
				if (prev == t)
					threads = t.next();
				else
					prev.next(t.next());

				no_threads--;
				continue;

			}
			t.run();
			prev = t;
		}
	}

	public GreenThread getThreadByID(int id)
	{
		for (GreenThread t = threads; t != null; t = t.next())
		{
			if (t.pid() == id)
				return t;
		}
		return null;
	}

	@Override
	public void start() {
		while (no_threads != 0)
			exec();
	}

	@Override
	public int threadAdd(GreenThread t) {
		t.pid(super.next_pid());
		t.next(threads);
		threads = t;
		no_threads++;
		return t.pid();
	}
}
