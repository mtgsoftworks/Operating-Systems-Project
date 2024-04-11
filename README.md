# Operating-Systems-Project

Summary
A multi-programming system with a four-level priority dispatcher running within the constraints of limited available resources is simulated using the Java programming language.
Note: The required JAR file for the project is located in the "dist" folder of the project.

Keywords: Dispatcher, FCFS, process, round robin, quantum, feedback, java, timeout

DEVELOPED SOFTWARE
When the program is executed, processes read from the input file are stored in the Dispatcher object and placed into a queue holding processes. Processes have attributes such as arrival time, priority value, processing time, etc. Apart from these, they are subject to specific hardware resource constraints (CD Drive, Memory, Modem, Printer, Scanner). Processes placed into the queue are allocated the desired hardware first if the requested resources are available, then they are sorted according to their attributes and placed into appropriate priority queues.

Sorting and Queuing of Incoming Processes

Incoming processes from the input file are placed into priority queues based on their priority values. We have four queues for four priority values.

Then, since processes arrive in a mixed order, we sorted them based on the ArrivalTime variables. This spared us from having to search for processes every time. The first element is checked according to the Time variable, if the ArrivalTime of the first element is greater than our Time value, there is no need to check further. If we look into the sorting function; we sorted the incoming queue along with the SelectionShort algorithm based on ArrivalTime.

Operation of the Dispatcher

The dispatcher starts by traversing the queue containing processes with a priority value of zero. If there is a process arriving for scheduling, this process is executed according to the FCFS algorithm if there are enough resources available (All requested resources must be available, otherwise the process is suspended) and it does not exceed the maximum memory limit (max (64MB) for real-time processes, max (960 MB) for other processes). It continues until completion. While traversing this queue, the necessary resources are allocated or released at appropriate places using the get_Resource() and release_Resource() functions, and thus other queues are also checked. The feedback() and timeOutCheck functions are called for control. If there are no processes in the queue with zero priority, processes are executed according to priority in the feedback function. Since the quantum value is one, each process works for one second in feedback and then its priority is reduced and it is suspended. Suspended processes are kept in a different queue and wait in that queue until their turn comes again.
All these operations are synchronized with the timer variable. The timeOutCheck function detects processes that have not been running for twenty seconds, removes them from the queue, returns the resources, updates each hardware local variable, and writes messages indicating that the process has timed out to the console.

Possible Improvements

We could have made a more effective selection in the sorting algorithm used in the dispatcher. Apart from that, the dispatcher currently only works for 4 levels, additional code would be required to work for higher levels, and changing priorities could be costly. Although the get_Resource() and release_Resource() classes in hardware resource constraints seem useful, assigning and tracking local variables of a separate process channel is complex and difficult. Automating resource constraints according to the desired process channel as input parameters to these functions would be more understandable and would reduce the workload for possible additions.

Process Class

For each process read from the file, we create objects from our custom process class and assign the necessary variables.

To run the process by the dispatcher, the execute function of our process class must be called. This function runs the JAR file we previously created using Java processes and prints the process information to the console. The Java process is called using Runtime.exec().

ANSI escape sequences in the ColoredSystemOutPrintLn class are used to enable process messages to be colored. When a new process is created, one of the ANSI codes in the COLORS array is assigned as a colorId property for that process. These colorIds come at the beginning of the process messages in strings. Process messages are displayed on the screen in colored form.

Comparison of Dispatchers

The role of the dispatcher module on the operating system is to regulate the CPU usage of incoming processes, place processes in appropriate positions, and change the user mode. Since dispatcher modules do not serve a single purpose, there are multiple types of them. Therefore, we can write a dispatcher algorithm tailored to our needs. If we look at the dispatcher scheme used by the operating system (see Figure: 1), incoming processes are placed in a ready queue and the process in line is directed to the CPU to be executed.

If we look at our dispatcher mechanism:

Incoming processes are allowed to use the CPU according to their priorities if there are enough requested resources and they do not exceed the maximum memory limit [max (64MB) for real-time processes, max (960 MB) for other processes]. Then, if their priority is not zero, they are allowed to continue processing by reducing their priority by one level. If a process with a priority of zero is received while a process with a non-zero priority is running, the process in progress is placed in the suspension queue and waits until the process with zero priority finishes. If the waiting time exceeds 20 seconds, the process times out and is removed. While real operating systems fetch processes from the ready queue and process them and then add them back to the end of the same list, since we used 2 suspension queues, our storage performance remained low.
