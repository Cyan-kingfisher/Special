package scene.eleven;

import common.Something;
import common.ThreadPoolFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 场景11： 基于CompletableFuture.thenApply和CompletableFuture.thenApplyAsync实现异步任务A，执行完毕后激活任务B
 * 任务B获取能任务A的结果，主线程能获取到任务B的返回值
 * thenApply：使用ForkJoinPool线程池，任务A和任务B使用同一线程
 * thenApplyAsync(Runnable action, Executor executor)：使用自定义线程，任务A使用ForkJoinPool线程和任务B使用自定义线程池线程
 * 
 * @author cyan
 * @since 2022/7/17
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Something something = new Something();
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.create();

        long start = System.currentTimeMillis();

        CompletableFuture<String> completableFutureTaskC = CompletableFuture.supplyAsync(something::doSomethingC);
        CompletableFuture<String> completableFutureTaskD = completableFutureTaskC.thenApply(
                res -> {
                    System.out.println("TaskD 获取到 TaskC 的结果: " + res);
                    return something.doSomethingD();
                }
        );
        System.out.println("taskD run result: " + completableFutureTaskD.get() + ", taskC thread result: " + completableFutureTaskC.get());

        long end = System.currentTimeMillis();
        System.out.println("Run time：" + (end - start));

    }

}
