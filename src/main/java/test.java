/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author artis
 */

import java.util.*;

public class test {
    public static process PCB[]=null; 
    public static void main(String []args){
    Scanner read = new Scanner(System.in);
    
    int n=0;
    int P=0;
    int ch=-1;
    
    process p=null;
    
    do{
        System.out.println("choose one of the following options by entering its number");
    System.out.println("1.to enter the number of processes to add the to the system");
    System.out.println("2.to display detailed information about each process in the system");
    System.out.println("3.to exit");
    ch=read.nextInt();
    if(ch<1||ch>3)
      System.out.println("invaild option");
    
    if (ch==1){
        System.out.println("enter the number of process you want to create");
        P=read.nextInt();
        PCB= new process[P];//create new arr to add process to it
        
        for(int i=0;i<P;i++){
        System.out.println("enter process priority priority range from 1 to 5 ");
            int prior=read.nextInt();
        System.out.println("enter arrival time");
            int arrival=read.nextInt();
        System.out.println("enter CPU burst time");
            int cpu=read.nextInt();
        p = new process(prior,arrival,cpu);
        PCB[n++]=p;
        }
       margeSort(0,P-1);
    }
    if(ch==2){
        for(int i=0;i<P;i++){
        System.out.println(PCB[i].toString());
        }
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
     if(PCB[i].getPriority()<=PCB[j].getPriority()){
     B[k++]=PCB[i];
     i++;
     }
     else{
     B[k++]=PCB[j];
     j++;        
     }
     }
     if(i>m)
        while(j<=r){
     B[k++]=PCB[j];
     j++;
     }
     else   
        while(i<=m){
     B[k++]=PCB[i];
     i++;     
    }
     for(k=0;k<B.length;k++)
     PCB[k+l]=B[k];
    }
    
}
