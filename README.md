
# Multi-Programming System with Priority-Based Dispatcher

### Overview
This project simulates a multi-programming system with a four-level priority dispatcher implemented in Java. 
The dispatcher operates under constraints of limited resources, managing multiple processes and dynamically 
prioritizing them based on a variety of factors. This system leverages several scheduling algorithms, 
resource allocation checks, and timeouts to maintain efficient process management. 
The required JAR file for the project is located in the `dist` folder.

---

### Key Concepts

- **Dispatcher**: Manages the allocation of CPU time and resources to processes based on priority and availability.
- **Scheduling Algorithms**:
  - **FCFS (First-Come-First-Serve)**
  - **Round Robin with Quantum** (1-second time slice)
  - **Feedback Mechanism**
- **Process Attributes**: Each process includes `arrival time`, `priority level`, `processing time`, and other properties. 
  Processes also request specific hardware resources.

---

### Project Details

#### 1. Sorting and Queuing of Incoming Processes
- **Process Initialization**: Processes are read from an input file and instantiated as objects with assigned attributes.
- **Queue Assignment**: Incoming processes are placed into four priority queues according to their priority levels.
- **Sorting**: Processes are sorted based on `ArrivalTime` to avoid repeated searches. The sorting algorithm uses the 
  **Selection Short** method based on `ArrivalTime`.

#### 2. Dispatcher Operation
- **Zero Priority Handling**: The dispatcher starts with zero-priority processes. If all requested resources are available, 
  the process executes following the **FCFS** algorithm within memory constraints (64 MB for real-time, 960 MB for others).
- **Resource Management**: Resources are allocated and released via `get_Resource()` and `release_Resource()` functions.
- **Feedback Mechanism**: Non-zero priority processes execute for one second, then have their priority reduced and are 
  suspended. Suspended processes wait in a separate queue.
- **Timeout Handling**: `timeOutCheck` removes processes exceeding a 20-second wait, releasing resources and logging 
  a timeout message.

#### 3. Improvements and Considerations
- **Sorting Efficiency**: The sorting algorithm could be optimized for better performance.
- **Level Adaptation**: The dispatcher currently supports four levels; higher levels would require additional code.
- **Resource Management**: Automating the `get_Resource()` and `release_Resource()` functions could simplify resource handling.

#### 4. Process Class
- **Process Object Creation**: Each process read from the input file is instantiated from a custom Process class.
- **Execution**: The `execute` function of each process object runs a JAR file via `Runtime.exec()`, displaying process 
  information on the console.
- **Colored Output**: Using the `ColoredSystemOutPrintLn` class, ANSI escape codes are applied to process messages, 
  displaying them in different colors.

#### 5. Dispatcher Comparison
- **Dispatcher Role**: Manages CPU usage, places processes, and adjusts user mode as needed.
- **CPU Access Control**: Processes access the CPU based on priority and available resources.
- **Priority Handling**: Higher priority processes can suspend lower priority ones. Processes exceeding a 20-second wait are timed out.
---

### Required JAR File
The JAR file for this project can be found in the `dist` directory.
