package com.ming;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketTest {

    private int count = 100;

    private Lock lock = new ReentrantLock();

    @Test
    public void testTicket() throws InterruptedException {
        TicketRunnable tr = new TicketRunnable();
        Thread t1 = new Thread(tr, "窗口A");
        Thread t2 = new Thread(tr, "窗口B");
        Thread t3 = new Thread(tr, "窗口C");
        Thread t4 = new Thread(tr, "窗口D");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // 等待线程终止
        Thread.currentThread().join();

    }

    public class TicketRunnable implements Runnable {

        @Override
        public void run() {
            while (count > 0) {
                lock.lock();

                try {
                    if (count > 0) {
                        System.out.println(Thread.currentThread().getName() + "售出第" + count-- + "张票");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
