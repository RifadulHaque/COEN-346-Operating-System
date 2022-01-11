import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class RoundRobinScheduler {
    //declaring the variables
    List<Process> threadQueue;
    int quantum;
    int time;

    RoundRobinScheduler(int quantum){
        //declaring the array list
        threadQueue = new ArrayList<>();
        this.quantum = quantum;
        //time starts from 1
        time=1;
    }

    //starting thread as process
    void addProcess(Process t){
        threadQueue.add(t);
        t.setStart(time);
    }

    void runScheduler() {

        //checks the list until it is empty
        while(!threadQueue.isEmpty()){

            Process t=threadQueue.get(0);
            threadQueue.remove(0);

            //adding the process to the threadqueue of the arrival time is greater than time
            if(t.arrivalTime>time) {
                threadQueue.add(t);
                continue;
            }

            //if the remaining time is less than quantum than the remaining time is given
            if(t.remainingTime<quantum){
                t.runProcess(time,t.remainingTime);
                //update time
                time+=t.remainingTime;

                //else the full quantum time is given
            }else{
                t.runProcess(time,quantum);
                //update time
                time+=quantum;
            }

            //makes the scheduler sleep until the quantum time when a process is running for the quantum time
            try {
                sleep(quantum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //statement for pause when the quantum is over
            System.out.println("Time "+time+", Process "+t.id+", paused");

            //if the total time or the remaining time is 0
            if (t.remainingTime==0){
                System.out.println("Time "+time+", Process "+t.id+", finished");
                t.setEnd(time);
                //adding it to the queue at the end if there is remaining time
            }else{
                threadQueue.add(t);
            }
        }
    }
}
