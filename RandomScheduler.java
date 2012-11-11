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

public class RandomScheduler extends Scheduler{
	private Random r = new Random();
	ArrayList<GreenThread> threads = null;
	int priorities = 0;
	
	public RandomScheduler(int priorities)
	{
		this.priorities = priorities;
		threads = new ArrayList<GreenThread>();
	}
	
	private void exec()
	{
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
	
	@Override
	public void start() {
		while(threads.size() != 0)
		{
			exec();
		}
	}

	@Override
	public void threadAdd(GreenThread t) {
		for (int i = 0; i < (priorities - t.priority); i++)
			threads.add(t);
	}

}
