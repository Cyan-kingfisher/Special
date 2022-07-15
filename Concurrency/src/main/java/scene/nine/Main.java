package scene.nine;

import common.Something;
import common.ThreadPoolFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 场景9： 基于thenRun和thenRunAsync实现异步任务A，执行完毕后激活任务B
 * 任务B获取不到任务A的结果
 * thenRun：使用ForkJoinPool线程池，任务A和任务B使用同一线程
 * thenRunAsync(Runnable action, Executor executor)：使用自定义线程，任务A使用ForkJoinPool线程和任务B使用自定义线程池线程
 *
 * @author cyan
 * @since 2022/7/16
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Something something = new Something();
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.create();

        long start = System.currentTimeMillis();

        CompletableFuture<String> completableFutureTaskC = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("thread name: " + Thread.currentThread().getName());
                    return something.doSomethingC();
                }
        );

        /**
         * 以下代码运行结果为：
         * thread name: ForkJoinPool.commonPool-worker-9
         * --- doSomethingC ---
         * --- doSomethingD ---
         * thread name: ForkJoinPool.commonPool-worker-9
         * taskD run result: null, taskC thread result: Task C Finish
         * Run time：4068
         *
         * CompletableFuture<Void> completableFutureTaskD = completableFutureTaskC.thenRun(
         *                 () -> {
         *                     something.doSomethingD();
         *                     System.out.println("thread name: " + Thread.currentThread().getName());
         *                 });
         */


        /**
         * 运行结果和thenRun没区别
         */
        CompletableFuture<Void> completableFutureTaskD = completableFutureTaskC.thenRunAsync(
                () -> {
                    something.doSomethingD();
                    System.out.println("thread name: " + Thread.currentThread().getName());
                });

        /**
         * 以下代码运行结果为：
         * thread name: ForkJoinPool.commonPool-worker-9
         * --- doSomethingC ---
         * --- doSomethingD ---
         * thread name: pool-1-thread-1
         *
         * CompletableFuture<Void> completableFutureTaskD = completableFutureTaskC.thenRunAsync(
         *                 () -> {
         *                     something.doSomethingD();
         *                     System.out.println("thread name: " + Thread.currentThread().getName());
         *                 }, threadPoolExecutor);
         */


        System.out.println("taskD run result: " + completableFutureTaskD.get() + ", taskC thread result: " + completableFutureTaskC.get());

        long end = System.currentTimeMillis();
        System.out.println("Run time：" + (end - start));

    }

}
