import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AssignmentOne extends LevenshteinDistance{
  public static List<String> logs = new ArrayList<String>();
  public static String vPat = "V04K4B63CL5BK0B";
  public static int count = 0;
  public static int workers = 2;

  public static void main(String[] args) {
    logs_to_list();
    logCheck(logs.get(0), 0);
    System.out.println(count);
    }

  //This method reads the log file and
  //copies its contents into a list of strings
  public static void logs_to_list() {
    try {
      File logFile = new File("vm_1.txt");
      Scanner scanner = new Scanner(logFile);
      while(scanner.hasNextLine()) {
        String data = scanner.nextLine();
        logs.add(data);
      }
      scanner.close();
    } 
    catch (FileNotFoundException e) {
        System.out.println("The log file could not be found.");
        e.printStackTrace();
    }
    for (int i = 0; i < logs.size(); i++){
      String temp = logs.get(i);
      temp = temp.substring((temp.indexOf(":")+1), temp.length());
      logs.set(i, temp);
    }

  }
  //this method recursively checks a single line of 
  //a log and leverages the LevenshteinDistance
  //class to determine if the vulnerability pattern
  //has been detected
  public static int logCheck(String log, int num) {
    /**
     * Thread code here
     */
    int len = vPat.length();
    log = log.substring(num, len);
    System.out.println("The current iteration is: " +num+ "The log value is: "+log +"\n");
    if (log.length() < 15) {
      return 0;
    }
    Calculate(vPat, log);
    if (isAcceptable_change() == true) {
      count++;
    }
    return logCheck(log, (num+1));
  }

  public static void threadInit(int numWorkers) {

  }
}
