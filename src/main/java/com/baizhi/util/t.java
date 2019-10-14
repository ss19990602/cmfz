package com.baizhi.util;


public class t {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Runnable myThread2 = new MyThread2();
        Thread thread = new Thread(myThread2);
        thread.start();
        myThread.start();
    }
}

class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("+++++++++++++++++++++++++++" + i);

        }
    }
}

class MyThread2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("==========================" + i);
        }
    }

}
