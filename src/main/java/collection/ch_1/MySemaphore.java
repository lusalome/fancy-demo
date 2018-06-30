package collection.ch_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
 * 一个计数信号量。从概念上讲，信号量维护了一个许可集合。
 * 如有必要，在许可可用前会阻塞每一个acquire()，然后再获取该许可。
 * 每一个release()添加一个许可，从而可能释放一个正在阻塞的获取者。但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。
 * Semaphore 通常用于限制可以访问某些资源（物理或逻辑的）的线程数目。例如，下面的类使用信号量控制对内容池的访问：
 *
 * 这里是一个实际的情况，大家排队上厕所，厕所只有两个位置，来了10个人需要排队。
 */
public class MySemaphore extends Thread {

    private int customer;
    private Semaphore position;

    public MySemaphore(int customer, Semaphore position) {
        this.customer = customer;
        this.position = position;
    }

    public void run() {
        try {
            if (position.availablePermits() > 0) {
                System.out.println("{" + customer + "} 进入厕所，有空位...");
            }
            else {
                System.out.println("{" + customer + "} 进入厕所，没空位，排队...");
            }
            position.acquire();
            System.out.println(" {" + customer + "} 顾客获得坑位");
            Thread.sleep((int) (Math.random() * 10000));

            position.release();
            System.out.println(" {" + customer + "} 顾客释放坑位");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(2);

        for (int c = 0; c < 10; c++) {
            executorService.execute(new MySemaphore(c, semaphore));
        }

        executorService.shutdown();
        semaphore.acquireUninterruptibly(2);
        System.out.println("使用完毕，清扫了...");
        semaphore.release(2);
    }
}
