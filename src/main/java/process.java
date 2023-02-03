
import java.io.Serializable;

public class process implements Serializable{
    public static int counter=1;
    private int pid;
    private int priority;//1-5
    private int arrival_time;
    private int CPU_burst_time;
    private int cpu_b;//edit may be removed 
    private int start_time;
    private int terminat_time;
    private int turn_around_time;
    private int Waiting_time;
    private int Response_time;
    
    public process(int priority, int arrival_time, int CPU_burst_time) {
        this.pid=counter;
        this.priority = priority;
        this.arrival_time = arrival_time;
        this.CPU_burst_time = CPU_burst_time;
        this.cpu_b= CPU_burst_time;
        counter++;
    }

    @Override
    public String toString() {
        
        return "process{" + "pid=" + pid + ", priority=" + priority + ", arrival_time=" + arrival_time + ", CPU_burst_time=" + CPU_burst_time + '}';
    }//(task )add the rest if the varibles and make the format similer to the repot in the pdf 
    //make it as orderd as you can :) not like mine

    public int getPriority() {
        return priority;
    }

    public int getCpu_b() {
        return cpu_b;
    }

    public void setCpu_b(int cpu_b) {
        this.cpu_b = cpu_b;
    }

    public int getPid() {
        return pid;
    }

    public int getArrival_time() {
        return arrival_time;
    }
//(task) calculat  these varibles
    // from PCB array(
    public int getStart_time() {
        return start_time;
    }

    public int getTerminat_time() {
        return terminat_time;
    }

    public int getTurn_around_time() {
        return turn_around_time;
    }

    public int getWaiting_time() {
        return Waiting_time;
    }

    public int getResponse_time() {
        return Response_time;
    }
//)    
  
}
