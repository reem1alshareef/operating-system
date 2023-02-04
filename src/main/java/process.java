public class process {
    public static int counter=1;//to set pid value
    public int pid;
    public int priority;//1-5
    public int arrival_time;
    public int CPU_burst_time;
    public int remaining_time;
    public int terminat_time;
    public int turn_around_time;
    public int Waiting_time;
    public int Response_time;
    public int start_time;
    public int readyQEntryTime;
    
     public process(int priority, int arrival_time, int CPU_burst_time) {
        this.pid=counter++;
        this.priority = priority;
        this.arrival_time = arrival_time;
        this.CPU_burst_time = CPU_burst_time;
        this.remaining_time =CPU_burst_time;
        this.Response_time=-1;
        this.Waiting_time=-1;
        this.start_time=-1;
        this.terminat_time=-1;
        this.turn_around_time=-1;
        this.readyQEntryTime=0;
     }

    public void setRemaining_time(int remaining_time) {
        this.remaining_time = remaining_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public void setTerminat_time(int terminat_time) {
        this.terminat_time = terminat_time;
    }

    public void setResponse_time(int Response_time) {
        this.Response_time = Response_time;
    }

    public void setTurn_around_time(int turn_around_time) {
        this.turn_around_time = turn_around_time;
    }

    public void setWaiting_time(int Waiting_time) {
        this.Waiting_time = Waiting_time;
    }

    public void setReadyQEntryTime(int readyQEntryTime) {
        this.readyQEntryTime = readyQEntryTime;
    }

    @Override
    public String toString() {
        return    "Pid: " + pid+ "\n" 
                        + "     CPU Burst Time: " +  CPU_burst_time  + "\n"
                        + "     Priority: " +    priority      + "\n"
                        + "     Arrival Time: " + arrival_time  + "\n"
                        + "     Start Time: " + start_time + "\n"
                        + "     Terminate Time: " + terminat_time + "\n"
                        + "     TurnAround Time: " + turn_around_time  + "\n"
                        + "     Waiting Time: " + Waiting_time + "\n"
                        + "     Response Time: " + Response_time +"\n"; 
    }
     
     
}