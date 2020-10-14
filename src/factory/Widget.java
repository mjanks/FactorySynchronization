package factory;

/**
 * Created by: Michael Janks
 * 
 * Widget.java
 *
 * The Widget class keeps track of the widget id and 
 * which workers have handled which widget.
 */

public class Widget {
	int id;
	int numHandlers = 0;

	public Widget(int n) {
		this.id = n;
	}

	public void workedOn() {
		numHandlers++;
	}

	public String printHandler() {
		switch(numHandlers) {
		case(1):
			return "A";
		case(2):
			return "A,B";
		case(3):
			return "A,B,C";
		case(4):
			return "A,B,C,D";
		}
		return "Something went wrong with printHandlers()";
	}
}
