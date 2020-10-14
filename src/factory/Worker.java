package factory;

/**
 * Created by: Michael Janks
 * 
 * Worker.java
 * 
 * This is the Worker class that directs the Worker threads to produce, 
 * consume, or both. The MAX_WIDGETS can be set here as well.
 */

public class Worker extends Thread {
	public static final int MAX_WIDGETS = 24;
	
	String name = "";
	ConveyerBelt addToBelt;
	ConveyerBelt removeFromBelt;
	int widgetCount = 0;
	Widget widget;
	boolean flagB = true;
	int totalProcessedB = 0;
	int totalProcessedC = 0;
	int totalProcessedD = 0;

	public Worker(String n, ConveyerBelt belt1, ConveyerBelt belt2) {
		this.name = n;
		this.addToBelt = belt1;
		this.removeFromBelt = belt2;
	}
	
	public void workerInfo(String choice, int id, String handlers) {
		switch(choice) {
		case("retrieve"):
			System.out.println(name + " is retrieving widget" + id + 
					"<handled by " + handlers + "> from the belt " + 
					removeFromBelt.name);
			break;
		case("work"):
			System.out.println(name + " is working on widget" + id +
					"<handled by " + handlers + ">");
			break;
		case("place"):
			System.out.println(name + " is placing widget" + id +
					"<handled by " + handlers + "> on the belt " + 
					addToBelt.name);
			break;
		}
	}

	public void run() {
		switch(name) {
		case "Worker A":
			while (true) {
				ConveyerBelt.napping();
				if(widgetCount < MAX_WIDGETS) {
					widgetCount++;
					Widget widget = new Widget(widgetCount);
					widget.workedOn();
					workerInfo("work", widget.id, widget.printHandler());
					addToBelt.enter(widget, name);
					workerInfo("place", widget.id, widget.printHandler());
				} else {
					interrupt();
					return;
				}
			} 
		case "Worker B":
			while (true) {
				ConveyerBelt.napping();
				if(totalProcessedB >= MAX_WIDGETS) {
					interrupt();
					return;
				} else {
					widget = removeFromBelt.remove(name);
					workerInfo("retrieve", widget.id, widget.printHandler());
					widget.workedOn();
					workerInfo("work", widget.id, widget.printHandler());
					addToBelt.enter(widget, name);
					workerInfo("place", widget.id, widget.printHandler());
					totalProcessedB++;
				}
			}
		case "Worker C":
			while (true) {
				ConveyerBelt.napping();
				if(totalProcessedC >= MAX_WIDGETS) {
					interrupt();
					return;
				} else {
					widget = removeFromBelt.remove(name);
					workerInfo("retrieve", widget.id, widget.printHandler());
					widget.workedOn();
					workerInfo("work", widget.id, widget.printHandler());
					addToBelt.enter(widget, name);
					workerInfo("place", widget.id, widget.printHandler());
					totalProcessedC++;
				}
			}
		case "Worker D":
			while (true) {
				ConveyerBelt.napping();
				if(totalProcessedD >= MAX_WIDGETS) {
					interrupt();
					System.out.println("All threads terminated");
					System.out.println("Program finished processing " + totalProcessedD + " widgets");
					return;
				}
				widget = removeFromBelt.remove(name);
				workerInfo("retrieve", widget.id, widget.printHandler());
				widget.workedOn();
				workerInfo("work", widget.id, widget.printHandler());
				totalProcessedD++;
			}
		}
	}
}
