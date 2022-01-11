public class Process {
    //initializing thread and varibls of each process

    Thread t;
    int remainingTime;
    int totalTime;
    int id;
    int start,end;
    int arrivalTime;

    //parametrized constructor
    Process(int id,int arrivalTime, int burstTime){
        //initialized thead
        t = new Thread();
        this.id = id;

        //declaring total time is equal to burst time
        totalTime = burstTime;
        //at the begining burst time is the remaining time
        remainingTime = burstTime;
        this.arrivalTime = arrivalTime;
    }


    //time == quantum time
    void runProcess(int currentTime,int time){
        //at the beginning of each process
        if (remainingTime==totalTime) System.out.println("Time "+currentTime+", Process "+id+", started");
        System.out.println("Time "+currentTime+", Process "+id+", resumed");
        this.remainingTime = this.remainingTime - time;
    }

    //for stating the start and end time
    void setStart(int time){
        this.start = time;
    }
    void setEnd(int time){
        this.end = time;
    }

    //total waiting time using this formula
    int getWaitingTime(){
        return this.end - this.start - this.totalTime;
    }
}
