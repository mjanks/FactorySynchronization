# FactorySynchronization Programming Assginment
# COSC 423 Operating Systems
# Eastern Michigan University Fall 2020
# Professor Matt Evett

Programming Assignment: Production Line
 

Write a program that simulates the use of a "production line" to create widgets. There should be four "workers", labelled, A, B, C and D, all members of the Worker class, each effected by a thread.

Worker A produces widgets, which are then passed to B, which sets myB to true, then passes the widget to C, and so on. The workers should handle their widgets at varying rates (i.e., use Math.random() to vary their production rate by calling sleep()) Consequently, there should be a buffer of objects, represented by an instance of the class ConveyerBelt, between each worker, simulating a conveyer belt between them. This buffer will be similar to the MessageQueue class in the textbook. A ConveyerBelt has a maximum capacity of three widgets. If a producing worker tries to produce into a full conveyer belt, print a warning message (e.g. "WARNING: worker B is waiting to put widget8 <handled by A,B> on conveyer") and block until there is space for its production. (The "blocking" is achieved by calls to wait(), as described below.) Likewise if a consuming worker try to consume from an empty conveyer belt, it should print a message ("WARNING: worker C is idle!"), and block until there is a widget to consume. Each time a worker handles a widget, it should print a message to that effect.  These messages will take three forms (only messages from B are shown here):

"Worker B is retrieving widget9<handled by A> from the belt "
"Worker B is working on widget9<handled by A,B>"
"Worker B is placing widget9<handled by A,B> on the belt "
Worker A will not be printing any messages of the first type, while Worker D will not be generating any messages of the third type.

The driver of your program should be a class called "Factory".

Hints:
You might base your code on a solution to the Bounded-Buffer problem (called the Producer-Consumer problem by some) given in Silberschatz's earlier books. The solution proposes three classes: BoundedBuffer, Producer, Consumer. In addition, there is driver class called Factory that creates the original threads. For this programming assignment you can consider there being a BoundedBuffer between each pair of workers. Worker A will behave strictly as a producer, and Worker D will behave strictly as a consumer. The other two workers will act as both consumers and producers.

The Widget class should provide a mechanism for keeping track of how many workers have handled it so far. One technique would be to have the Widget class provide a workUpon() method, which would be invoked by each Worker when it gets the Widget. This method might increment an instance variable counter within each widget. The counter should be 1 for any Widgets being passed from A to B, 2 for any Widgets being passed from B to C, etc.

The BoundedBuffer class uses Java's wait() and notify() methods. You might want to review my notes/lecture on thread synchronization in Java. In short, thought, you can assume that the wait() method causes the thread executing it to suspend itself on a BoundedBuffer (which are analogous to MessageQueues) until another thread calls notify() on the same BoundedBuffer. In the sample code, only one BoundedBuffer is used, as there are only two workers (one producer and one consumer), and hence there is only one worker interface ("interface" in the general sense, not the technical Java terminology.) Your program will have three interfaces, A-B, B-C, and C-D, so you'll need three of something like a BoundedBuffer.

You should only be creating new Widgets (via new Widget(...)) as part of the code for the first worker, A.

Your program should generate and properly handle 24 widgets, then terminate cleanly. If the program hangs, I will deduct at least 1/3 grade.

In terms of overall design, consider whether you want four instances of a single Worker class (using instance variables to differentiate their behaviors), or a three different Worker classes, maybe one for A, one for D and one for both B and C. Both designs can be effective.

To hand in:

Submit your code as a zip file of your Java source files.
Your name must be in the header comment of each file you submit.
If your program has any bugs that you know of, the zip file should include a file named "README.txt" containing any explanation.
Extra Credit (required for graduate students):
For a 10% bonus to your score (i.e., a perfect program would yield a GPA of 4.4, instead of 4.0): provide a graphical interface to your program. The interface should animate the progress of the widgets as they progress from belt to belt. Thus, each belt should be able to display up to 3 widgets simultaneously. The representation of each worker should provide a visual clue whenever that worker is having to wait either to receive a widget (because the incoming belt is empty) or to produce a widget (because the outgoing belt is full). For full extra credit, each widget should be uniquely identifiable as it makes its way through the system. I.e., you might provide each widget a different color, or a different label. For partial extra credit it is sufficient to represent each conveyer belt as a number, showing how many items are on that belt. It is not necessary that the animation be fancy--simple geometric shapes and colors will suffice. Feel free to have fun, though!

Here is a video of a student project demonstrating a graphical version of this assignment, completed by Kokouvi Djogbessi in 2013. The video starts soon after the program begins, but long enough into the program that all four workers are already busy. I believe Worker D is working on a widget that has already been handled by A, B, and C. At 18 seconds into the video, Work C is idle, waiting for B to place a widget onto the conveyer belt. At 26 seconds, Worker A is blocked because the conveyer belt to B is already full. At 1:48, Worker A finishes the last widget and remains idle for the duration. Over the remaining time, each of the workers will eventually become idle.

For the animation to work, you'll have to insert a judicious number of sleep() calls, so the workers don't work faster than the animation can animate. A good place for a worker thread to sleep is between its consumption of a widget, and then the production of that (altered) widget.

If you've not programmed a Java animation before, you might take a look at this overview.
