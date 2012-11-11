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

public abstract class Scheduler {

	private int next_pid = 1;
	protected boolean realtime;
	protected GreenThread signaled = null;

	public abstract void start();
	public abstract int threadAdd(GreenThread t);
	public abstract GreenThread getThreadByID(int id);

	protected int next_pid()
	{
		return next_pid++;
	}

	public void signal(int pid, int signal)
	{
		GreenThread t = getThreadByID(pid);
		if (t != null)
		{
			t.signal(signal);
			signaled = t;
		}
	}
}
