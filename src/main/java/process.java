/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author artis
 */
public class process {
    public static int counter=1;
    private int pid;
    private int priority;//1-5
    private int arrival_time;
    private int CPU_burst_time;
    private int cpu_b;//edit may be removed 

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
    }

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
  
}
