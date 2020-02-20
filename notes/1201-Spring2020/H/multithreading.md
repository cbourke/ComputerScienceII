
# Concurrency

  * Asynchronous: a task that does not *block* the remainder of the program
    (execute this task, but continue with the next instruction)
    ```
    task1;
    task2;
    async task3;
    task4;
    ```
  * Multithreading: multiple threads running independent (may be on one core, 
    allowing for scheduling, waiting processes; waiting on I/O)
  * Parallel Computing: multiple processes or threads running on different cores
    (generally same machine) at the same time
  * Distributed Computing: multiple processes/threads running on physically different
    hardware

  * Benefits:
      * Efficiency: Time $\rightarrow$ Time / n
  * Costs:
      * Not free, context switching
      * Synchronization
      * Race conditions
      * Starvation
      * Not all problems are parallelizable 

## Demonstrations

* A *process* is an executing program with its own memory/resources
* Demo: `htop`
* Demo: Launching lots of processes on CSE
* Demo: fork bomb?
* Demo: `limit`

* A *thread* is an entity *within* a process that can execute
independent of other threads.  Threads have their own *context*
(own local memory, stack, etc.) but may *share* resources 

* Java:
  * `Thread` (full thread implementation)
  * `Callable` (returns a result, blocks until done)
  * `Runnable` (does not return a result)
  * `Future` (provides a "future" value, runs at its convenience, blocks when
  asked for a result that is not complete)

* Demonstration
