package com.demo.bbs;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test implements Runnable {

    private int ticket = 100;

    Object object = new Object();

    Lock lock = new ReentrantLock();

    @Override
    public void run() {

        while (true) {
            lock.lock();

                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "--->正在卖" + ticket + "张票");
                    ticket--;
                }
                lock.unlock();

        }

    }
}
