package com.jcomm.threads;

/**
 * Created by jovaughnlockridge1 on 11/15/15.
 */
public class JT implements Runnable{


        private static volatile  int c;

        public JT(int count){
            c = count;
        }

        public void run(){
            while(true) {
                if(Integer.parseInt(Thread.currentThread().getName())==1 && c==1) {
                    System.out.print(Thread.currentThread().getName()+",");
                    c=2;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if(Integer.parseInt(Thread.currentThread().getName())==2 && c==2) {
                    System.out.print(Thread.currentThread().getName()+",");
                    c=3;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if(Integer.parseInt(Thread.currentThread().getName())==3 && c==3) {
                    System.out.print(Thread.currentThread().getName()+",");
                    c=1;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }



            }
        }


    public static void main(String arg[]){

    }



}
