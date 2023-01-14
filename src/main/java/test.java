/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author artis
 */
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {
    public static process readyQ[]=new process[30];
    public static process PCB[]=new process[100];
    public static int PCBcounter=0;
    public static int readyCounter=0;
    public static int P=0;

    
    public static void main(String []args){
    Scanner read = new Scanner(System.in);
    
    int ch=-1;
    process p=null;
    
    do{
        
        System.out.println("choose one of the following options by entering its number");
        System.out.println("1.to create new processes ");
        System.out.println("2.to display detailed information about each process in the system");
        System.out.println("3.to exit");
        
        ch=read.nextInt();
    if(ch<1||ch>3)
      System.out.println("invaild option");
    
    if (ch==1){
        System.out.println("enter the number of process you want to create");
        P=read.nextInt();
        
        for(int i=0;i<P;i++){
        System.out.println("enter process priority priority range from 1 to 5 ");
            int prior=read.nextInt();
            while (prior <1 ||prior>5 ){
               System.out.print("please enter priority in range from 1 to 5");
               prior=read.nextInt();
            }
        System.out.println("enter arrival time");
            int arrival=read.nextInt();
        System.out.println("enter CPU burst time");
            int cpu=read.nextInt();
        p = new process(prior,arrival,cpu);
        readyQ[readyCounter++]=p;
        }
       writeInFile();
       margeSort(0,P-1);
       appedToPCB();
    }
    if(ch==2){
     readFromFile();
     printPCB();
    }
    
    }while(ch!=3);
    }
    
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
     if(readyQ[i].getPriority()<readyQ[j].getPriority()){
     B[k++]=readyQ[i];
     i++;
     }
     else if(readyQ[i].getPriority()==readyQ[j].getPriority()){
       if(readyQ[i].getArrival_time()<readyQ[j].getArrival_time()){
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
    
    public static void appedToPCB(){ 
        int totalBurests=totalCpuB();
        int n=0;//counter for readyQ 
        int i=0;
         while(true){
         if(n<P && readyQ[n].getCpu_b()>0){
            int Cpu_b=readyQ[n].getCpu_b(); //get the remaing cpu burest for the process
            readyQ[n].setCpu_b(Cpu_b-1); //update the remaing time by subtracting one (cpu_burest -1)
            PCB[PCBcounter++]=readyQ[n++];
            i++;   
         }
          if (n==P){
         n=0;//the counter will be reset to zero if its value equlas to the counter of readyQ
         }
         
         if(readyQ[n].getCpu_b()==0){
         n++;
         }
         
         if(i==totalBurests){
         break;
         }
            
     }
         readyCounter=0;
    }
    
    private static int totalCpuB(){//to calcualte the sum of cpu burests
        int sum=0;
    for(int i=0;i<P;i++){
        sum+=readyQ[i].getCpu_b();
    }
     return sum;
    }
    
    public static void printPCB(){
        int i=0;
    while(PCB[i]!=null){
    System.out.print("P"+PCB[i].getPid()+"|CS|");
    i++;
    }
    System.out.print("\n");
    }
    
    public static void writeInFile(){
        try {
            File f = new File("report1.dat");
            FileOutputStream f2 = new FileOutputStream(f,true);
            PrintWriter pr= new PrintWriter(f2);
            
            for(int i=0;i<P;i++){
               pr.println(readyQ[i].toString());
                        }
              pr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public static void readFromFile(){
      try{
        
        File f = new File("report1.dat");
        Scanner sc = new Scanner(f);
           
                while(sc.hasNext()){
                System.out.println(sc.nextLine());
                }
             sc.close();
              }
   
      catch(IOException ex){
      
      }
    
   }
}

