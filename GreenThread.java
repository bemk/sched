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

public abstract class GreenThread {
	protected int priority;
	private GreenThread next;
	private boolean killed = false;
	private int signal = 0;
	private int pid = 0;

	public GreenThread next()
	{
		return this.next;
	}

	public void pid (int pid)
	{
		if (this.pid == 0)
			this.pid = pid;
	}

	public int pid ()
	{
		return this.pid;
	}

	public void signal(int signal)
	{
		this.signal = signal;
	}

	protected int signal()
	{
		return this.signal;
	}

	public boolean killed()
	{
		return this.killed;
	}

	public void kill()
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
