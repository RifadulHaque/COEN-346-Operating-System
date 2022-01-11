import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesScheduler {
    public static void main(String args[]) throws FileNotFoundException {
        RoundRobinScheduler scheduler=new RoundRobinScheduler(1);
        Scanner scn =new Scanner(new File("./input.txt"));
        PrintStream fileOut = new PrintStream("./out.txt");
        System.setOut(fileOut);

        //declaring a list of process from Process
        List<Process> processes = new ArrayList<>();

        //initializing id as 1
        int id=1;


        while (scn.hasNextInt()){
            Process p1 = new Process(id++,scn.nextInt(),scn.nextInt());
            //scheduler object is used to start process a thread from the round robin class
            scheduler.addProcess(p1);
            //adding process to list of process
            processes.add(p1);
        }

        //using the scheduler object to run the scheduler
        scheduler.runScheduler();

        System.out.println("-------------------------------------");

        //for printing the waiting time
        System.out.println("Waiting Times:");
        for (int i=0;i<processes.size();i++){
            Process p = processes.get(i);
            System.out.println("Process "+p.id+": "+p.getWaitingTime());
        }
    }
}
