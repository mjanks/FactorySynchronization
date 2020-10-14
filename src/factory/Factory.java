package factory;

/**
 * Created by: Michael Janks
 * 
 * Factory.java
 *
 * This creates the conveyer belts (buffers) and the worker threads.
 */

public class Factory
{
	public static void main(String args[]) {
		ConveyerBelt conveyerAB = new ConveyerBelt("conveyerAB");
		ConveyerBelt conveyerBC = new ConveyerBelt("conveyerBC");
		ConveyerBelt conveyerCD = new ConveyerBelt("conveyerCD");

		Worker workerA = new Worker("Worker A", conveyerAB, null);
		Worker workerB = new Worker("Worker B", conveyerBC, conveyerAB);
		Worker workerC = new Worker("Worker C", conveyerCD, conveyerBC);
		Worker workerD = new Worker("Worker D", null, conveyerCD);

		workerA.start();
		workerB.start();
		workerC.start();
		workerD.start();
	}
}
