import java.io.*;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
        
public class test {

    
    public static int fixedTimer;
    public static int timer;
    public static process readyQ[]= new process[30];
    public static process PCB[]=new process[100];
    public static int PCBcounter=0;
    public static int readyCounter=0;
    public static int P=0;
    public static double AVGwating=0;
    public static double AVGturnAround=0;
    public static double AVGresponce=0;
    public static void main(String[]args){
    Scanner read = new Scanner(System.in);
    
    int ch=-1;
    process p=null;
    
    do{
        
        System.out.println("choose one of the following options by entering its number");
        System.out.println("------------------------------");
        System.out.println("1)to create new processes ");
        System.out.println("2)to display detailed information about each process in the system");
        System.out.println("3)to exit");
        System.out.println("------------------------------");
        ch=read.nextInt();
    if(ch<1||ch>3)
      System.out.println("invaild option");
    
  
    if (ch==1){
        System.out.println("enter the number of process you want to create");
        P=read.nextInt();
        for(int i=0;i<P;i++){
        System.out.println("enter process priority (range from 1 to 5) ");
            int prior=read.nextInt();
            while (prior<1 ||prior>5 ){
                System.out.println("please enter priority  (range from 1 to 5) ");
                prior=read.nextInt();
             } 
        System.out.println("enter arrival time");
            int arrival=read.nextInt();
        System.out.println("enter CPU burst time");
            int cpu=read.nextInt();
        p = new process(prior,arrival,cpu);
        readyQ[readyCounter++]=p;
        }
        margeSort(0,P-1);
        fixedTimer=timer=readyQ[0].arrival_time;          
        appedToPCB();
        calculateAVG();
        writeInFile();
  
    }             
    if(ch==2){
       readFromFile();
    }
    }while(ch!=3);
    }
    ////////////////////////////////////    
    public static void margeSort(int l,int r){
        if(l>=r)
            return;
        int m=(l+r)/2;
        margeSort(l,m);
        margeSort(m+1,r);
        marge(l,m,r);
    }
    public static void marge(int l,int m,int r){
         process B []= new process[r-l+1];
         int i=l,j=m+1,k=0;
         while(i<=m && j<=r){
         if(readyQ[i].arrival_time<readyQ[j].arrival_time){
         B[k++]=readyQ[i];
         i++;
         } 
         else if(readyQ[i].arrival_time==readyQ[j].arrival_time){
             if(readyQ[i].pid<readyQ[j].pid){
                  B[k++]=readyQ[i];
                  i++;
                }
              else{
                  B[k++]=readyQ[j];
                  j++;        
         }
         }
         else{
         B[k++]=readyQ[j];
         j++;        
         }
         }
         if(i>m)
            while(j<=r){
         B[k++]=readyQ[j];
         j++;
         }
         else   
            while(i<=m){
         B[k++]=readyQ[i];
         i++;     
        }
         for(k=0;k<B.length;k++)
         readyQ[k+l]=B[k];
    } 
    
    /////////////////////////////////
    public static void appedToPCB(){     
        int pointer=0;
        int i;
        boolean flag=true; 
    while(flag){
        if(readyQ[pointer].remaining_time==0){//this to not get stock in the same process in case it has the hightest priorioty
            i=0;   
             while(i<P && readyQ[i].arrival_time<=timer){ 
                 if(readyQ[i].remaining_time>0){//geve the pointer to any other process we will deal with the highest proiority below
                      pointer=i;
                 break;
             }
                 i++;
             }
         }   
       i=0;
     while(i<P && readyQ[i].arrival_time<=timer){ //change the pointer to the highest priority process
     if(readyQ[i].remaining_time>0 && (readyQ[i].priority<readyQ[pointer].priority || (readyQ[i].priority==readyQ[pointer].priority && readyQ[i].readyQEntryTime > readyQ[pointer].readyQEntryTime)))
         pointer=i;   
    i++;
    }
     
    if(PCBcounter!=0 && PCB[PCBcounter-1].pid==readyQ[pointer].pid){//if same as priveous process
    readyQ[pointer].setRemaining_time(readyQ[pointer].remaining_time-1);//just update remaning time
    }            
    else{//if it is not the same privous process
      if(PCBcounter!=0)// no cs in the first time    
        timer++;//cs
      
      while(i<P && readyQ[i].arrival_time<=timer){
        if(readyQ[i].arrival_time==timer){
        readyQ[i].setReadyQEntryTime(timer);
        
        }
      i++;
      }
    readyQ[pointer].setRemaining_time(readyQ[pointer].remaining_time-1);//update remaning time
    PCB[PCBcounter++]=readyQ[pointer];//add to process control block
      if(PCB[PCBcounter-1].start_time==-1){//if first time a process enters set start time as the value of timer
        PCB[PCBcounter-1].setStart_time(timer);
        }
      if(PCB[PCBcounter-1].Response_time==-1){
      calculateResponceTime();
      }
    }
     timer++;//normal timer
     
    if(PCB[PCBcounter-1].terminat_time==-1&& PCB[PCBcounter-1].remaining_time==0){//if on more remaning time set terminat time value
        PCB[PCBcounter-1].setTerminat_time(timer);
    }
    if(PCB[PCBcounter-1].Waiting_time==-1&& PCB[PCBcounter-1].remaining_time==0){//if on more remaning time set wating time value
    calculateWatingTimeAndTurnAround();
        }
    
    flag=false;
    for(i=0;i<P;i++){
    if(readyQ[i].remaining_time>0){//if any process still have some remaing time do not exit
        flag=true;
        break;
    }
    }
    
    }
}
    ///////////////////////////
    
    public static void printall(){
    for(int i=0;i<PCBcounter;i++){
    System.out.print("P"+PCB[i].pid+"|CS|");
    }
    
    }
    
          public static void writeInFile(){
        try {
            File f = new File("report1.txt");
            FileOutputStream f2 = new FileOutputStream(f);
            PrintWriter pr= new PrintWriter(f2);
                           int i=1;
                    int n;
                    while(i!=process.counter){
                        n=0;
                        while(PCB[n]!=null){
                    if(PCB[n].pid==i){
                    pr.print(PCB[n]);
                    i++;
                    break;
                    }
                    n++;
                    }
                    }
                    //
                    i=0;
                    String str="[";
                    
                    while(PCB[i]!=null){
                    str+="P"+PCB[i].pid+"|CS|";
                    i++;
                    }
                    str=str.substring(0, str.length() - 4);
                    str+="]";
                    pr.println( "---------------------------------");
                    pr.println(str);
                    pr.println( "---------------------------------");
                    pr.println(" Average  Turn around time= "+AVGturnAround);
                    pr.println(" Average  Waiting time= "+AVGwating);
                    pr.println(" Average  Response time= "+AVGresponce);
                    pr.println( "---------------------------------");
              pr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    //
      public static void readFromFile(){
      try{
        File f = new File("report1.txt");
        Scanner sc = new Scanner(f);
             while(sc.hasNext()){
                System.out.println(sc.nextLine());
              }
             sc.close();
        }catch(IOException ex){
         Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    //
    
    public static void calculateResponceTime(){ 
        boolean flag=false;    
        int arr[]=new int[PCBcounter] ;  
        int n=0;
        int j=0;
        int i=0;
        int responceCounter=0;
        while(PCB[i].pid!=PCB[PCBcounter-1].pid){  
            j=0;
            while(j<n){
            if(arr[j]==PCB[i].pid)
                flag=true;
            j++;
            }
        if(!flag){    
        responceCounter+=Math.abs(PCB[i].remaining_time-PCB[i].CPU_burst_time);//=runing time for other processes
        responceCounter++;//+CS time
        arr[n++]=PCB[i].pid;
        i++;
        }
        if(flag){
        responceCounter++;//+CS time
        i++;
        }
        flag=false;
        }
        PCB[PCBcounter-1].setResponse_time((fixedTimer+responceCounter)-PCB[PCBcounter-1].arrival_time);  
    } 
      
      
        public static void calculateWatingTimeAndTurnAround(){
               boolean flag=false;    
        int arr[]=new int[PCBcounter] ;  
        int n=1;
        int j=0;
        int i=0;
        int watingCounter=0;
        arr[0]=PCB[PCBcounter-1].pid;
        while(i<PCBcounter-1){  
            j=0;
            while(j<n){
            if(arr[j]==PCB[i].pid)
                flag=true;
            j++;
            }
        if(!flag){    
        watingCounter+=Math.abs(PCB[i].remaining_time-PCB[i].CPU_burst_time);//=runing time for other processes
        watingCounter++;//+CS time
        arr[n++]=PCB[i].pid;
        i++;
        }
        if(flag){
        watingCounter++;//+CS time
        i++;
        }
        flag=false;
        }
        PCB[PCBcounter-1].setWaiting_time((fixedTimer+watingCounter)-PCB[PCBcounter-1].arrival_time);
        PCB[PCBcounter-1].setTurn_around_time(PCB[PCBcounter-1].Waiting_time+PCB[PCBcounter-1].CPU_burst_time);
        }
 
    ////
           public static void calculateAVG(){
    int m=process.counter-1;
    int n;
    double sum1=0;
    double sum2=0;
    double sum3=0;
    int i=1;
    while(i!=process.counter){
        n=0;
      while(PCB[n]!=null){
        if(PCB[n].pid==i){
        sum1+=PCB[n].Response_time;
        sum2+=PCB[n].turn_around_time;
        sum3+=PCB[n].Waiting_time;
        i++;
        break;
        }
     n++;
    }
    } 

    AVGwating=sum3/m;
    AVGturnAround=sum2/m;
    AVGresponce=sum1/m;
    }
      
}
   


   


