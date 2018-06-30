package collection.ch_1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * 用给定的计数初始化 CountDownLatch。由于调用了countDown() 方法，所以在当前计数到达零之前，await 方法会一直受阻塞。
 * 之后，会释放所有等待的线程，await的所有后续调用都将立即返回。这种现象只出现一次
 * 计数无法被重置。如果需要重置计数，请考虑使用 CyclicBarrier。
 *
 * CountDownLatch是一个通用同步工具，它有很多用途。将计数1初始化的CountDownLatch 用作一个简单的开/关锁存器，或入口
 * 在通过调用countDown()的线程打开入口前，所有调用 await 的线程都一直在入口处等待。
 *
 * 用N初始化的 CountDownLatch 可以使一个线程在N个线程完成某项操作之前一直等待，或者使其在某项操作完成 N 次之前一直等待。
 * CountDownLatch 的一个有用特性是，它不要求调用 countDown 方法的线程等到计数到达零时才继续，而在所有线程都能通过之前，它只是阻止任何线程继续通过一个 await。
 *
 * CountDownLatch最重要的方法是countDown()和await()，前者主要是倒数一次，后者是等待倒数到0，如果没有到达0，就只有阻塞等待了。
 */
public class MyCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        // 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);
        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);
        // 十名选手
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            final int NO = i + 1;

            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        begin.await(); // 一直阻塞
                        Thread.sleep((int) (Math.random() * 10000));
                        System.out.println("{" + NO + "} arrived.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            };
            executor.submit(run);
        }

        System.out.println("Game start.");
        begin.countDown();
        end.await();

        System.out.println("Game over.");
        executor.shutdown();
    }
}
