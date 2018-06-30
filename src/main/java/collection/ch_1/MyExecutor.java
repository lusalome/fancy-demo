package collection.ch_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 当针对高质量Java多线程并发程序设计时,为防止死蹦等现象的出现，比如使用java之前的wait()、notify()和synchronized等，每每需要考虑性能、死锁、公平性、资源管理以及如何避免线程安全性方面带来的危害等诸多因素，往往会采用一些较为复杂的安全策略.
 * 工具包java.util.concurrent以简化并发完成。开发者们借助于此，将有效的减少竞争条件（race conditions）和死锁线程。
 * Executor                  ：具体Runnable任务的执行者。
 * ExecutorService           ：一个线程池管理者，其实现类有多种。能把Runnable,Callable提交到池中让其调度。
 * Semaphore                 ：一个计数信号量
 * ReentrantLock             ：一个可重入的互斥锁定 Lock，功能类似synchronized，但要强大的多。
 * Future                    ：是与Runnable,Callable进行交互的接口，比如一个线程执行结束后取返回的结果等等，还提供了cancel终止线程。
 * BlockingQueue             ：阻塞队列。
 * CompletionService         ：ExecutorService的扩展，可以获得线程执行结果的
 * CountDownLatch            ：一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * CyclicBarrier             ：一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点
 * Future                    ：Future 表示异步计算的结果。
 * ScheduledExecutorService  ：一个 ExecutorService，可安排在给定的延迟后运行或定期执行的命令。
 */
public class MyExecutor extends Thread {

    private int index;

    public MyExecutor(int index) {
        this.index = index;
    }

    public void run() {
        try {
            System.out.println("{" + index + "} running...");
            Thread.sleep((int) (Math.random() * 10000));
            System.out.println(" {" + index + "} end.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Executors主要方法说明
     * newFixedThreadPool（固定大小线程池）
     * 创建一个可重用固定线程集合的线程池，以共享的无界队列方式来运行这些线程（只有要请求的过来，就会在一个队列里等待执行）。
     * 如果在关闭前的执行期间由于失败而导致任何线程终止，那么一个新线程将代替它执行后续的任务（如果需要）。
     *
     * newCachedThreadPool（无界线程池，可以进行自动线程回收）
     * 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。对于执行很多短期异步任务的程序而言，这些线程池通常可提高程序性能。
     * 调用 execute 将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
     * 因此，长时间保持空闲的线程池不会使用任何资源。注意，可以使用 ThreadPoolExecutor 构造方法创建具有类似属性但细节不同（例如超时参数）的线程池。
     *
     * newSingleThreadExecutor（单个后台线程）
     * 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。（注意，如果因为在关闭前的执行期间出现失败而终止了此单个线程，那么如果需要，一个新线程将代替它执行后续的任务）。
     * 可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。与其他等效的 newFixedThreadPool(1) 不同，可保证无需重新配置此方法所返回的执行程序即可使用其他的线程。
     *
     * 这些方法返回的都是ExecutorService对象，这个对象可以理解为就是一个线程池。
     * 这个线程池的功能还是比较完善的。可以提交任务submit()，可以结束线程池shutdown()。
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new MyExecutor(i));
            // executorService.submit(new MyExecutor(i));
        }
        executorService.shutdown();
    }
}
/*
 * 这个线程池是如何工作的，将休眠的时间调长10倍 Thread.sleep((int)(Math.random()*10000));
 * 会清楚看到只能执行4个线程，当执行完一个线程后，才会又执行一个新的线程。也就是说，我们将所有的线程提交后，线程池会等待执行完最后shutdown。
 * 我们也会发现，提交的线程被放到一个“无界队列里”。这是一个有序队列（BlockingQueue，这个下面会说到）。
 *
 * 另外它使用了Executors的静态函数生成一个固定的线程池，顾名思义，线程池的线程是不会释放的，即使它是Idle。
 * 这就会产生性能问题，比如如果线程池的大小为200，当全部使用完毕后，所有的线程会继续留在池中，相应的内存和线程切换（while(true)+sleep循环）都会增加。
 * 如果要避免这个问题，就必须直接使用ThreadPoolExecutor()来构造。可以像通用的线程池一样设置'最大线程数'、'最小线程数' 和 '空闲线程keepAlive的时间'。
 */
