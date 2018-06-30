package collection.ch_1;

import java.util.concurrent.*;

/*
 * 将生产新的异步任务与使用已完成任务的结果分离开来的服务。
 * 生产者 submit 执行的任务。使用者 take 已完成的任务，并按照完成这些任务的顺序处理它们的结果。
 *
 * 例如，CompletionService 可以用来管理异步 IO ，执行读操作的任务作为程序或系统的一部分提交，
 * 然后，当完成读操作时，会在程序的不同部分执行其他操作，执行操作的顺序可能与所请求的顺序不同。
 *
 * 通常，CompletionService 依赖于一个单独的 Executor 来实际执行任务，在这种情况下，
 * CompletionService 只管理一个内部完成队列。ExecutorCompletionService 类提供了此方法的一个实现。
 *
 *
 * Future
 * Future表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并检索计算的结果。
 * 计算完成后只能使用get方法来检索结果，如有必要，计算完成前可以阻塞此方法。取消则由 cancel 方法来执行。
 *
 * 还提供了其他方法，以确定任务是正常完成还是被取消了。一旦计算完成，就不能再取消计算。
 * 如果为了可取消性而使用Future但又不提供可用的结果，则可以声明 Future<?> 形式类型、并返回 null 作为基础任务的结果。
 * Future的功能，而且这个可以在提交线程的时候被指定为一个返回对象的。
 */
public class MyCompletionService implements Callable<String> {

    private int i;

    public MyCompletionService(int i) {
        this.i = i;
    }

    @Override
    public String call() throws Exception {
        Integer time = (int) Math.random() * 10000;
        try {
            System.out.println("{" + i + "} start.");
            Thread.sleep(time);
            System.out.println(" {" + i + "} end.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i + "" + time;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService completionService = new ExecutorCompletionService<String>(executor);

        for (int i = 0; i < 10; i++) {
            Future future = completionService.submit(new MyCompletionService(i));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("  {" + completionService.take().get() + "} taken.");
        }
        executor.shutdown();
    }

}
