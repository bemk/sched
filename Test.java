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
