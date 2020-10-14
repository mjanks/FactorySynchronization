package factory;

/**
 * Modified by: Michael Janks
 * 
 * This was originally BoundedBuffer.java. I've made some modifications 
 * and changed the beltName of the class to ConveyerBelt. This program implements 
 * the bounded buffer (conveyer belt) using Java synchronization.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999
 * @version 1.1 - May, 2015 
 * Some modifications by Matt Evett  
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

public class ConveyerBelt { 
	public static final int    NAP_TIME = 5;
	private static final int   BUFFER_SIZE = 3;

	private int count;  // number of items in the buffer
	private int in;     // points to the next free position in the buffer
	private int out;    // points to the next full position in the buffer
	public String beltName; // conveyer belt name
	private final Widget[] buffer;

	public ConveyerBelt(String n) {
		count = 0;
		in = 0;
		out = 0;
		this.beltName = n;
		buffer = new Widget[BUFFER_SIZE];
	}

	public static void napping() {
		int sleepTime = 0;
		while(sleepTime == 0) {
			sleepTime = (int) (NAP_TIME * Math.random());
		}
		try { Thread.sleep(sleepTime*1000); }
		catch(InterruptedException e) { }
	}

	public synchronized void enter(Widget item, String worker) {
		while (count == BUFFER_SIZE) {
			try {
				System.out.println("WARNING: " + worker + " is waiting to put widget" + 
						item.id + "<handled by " + item.printHandler() + "> on belt " + beltName);
				wait();
			}
			catch (InterruptedException e) { }
		}

		++count;
		buffer[in] = item;
		in = (in + 1) % BUFFER_SIZE;

		notify();
	}

	public synchronized Widget remove(String worker) {
		Widget item;

		while (count == 0) {
			try {
				System.out.println("WARNING: " + worker + " is idle!");
				wait();

			}
			catch (InterruptedException e) { }
		}

		--count;
		item = buffer[out];
		out = (out + 1) % BUFFER_SIZE;

		notify();

		return item;
	}
}
