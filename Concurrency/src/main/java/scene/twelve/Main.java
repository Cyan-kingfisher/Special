package scene.twelve;

import common.Something;
import common.ThreadPoolFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 场景12： 基于CompletableFuture.whenComplete和CompletableFuture.whenCompleteAsync实现异步任务A，执行完毕后激活回调任务
 * 回调任务可获得任务A的返回值，并且可处理异常
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
        completableFutureTaskC.whenComplete(
                (prefixTaskResult, throwable) -> {
                    if (throwable == null) {
                        System.out.println("thread name: " + Thread.currentThread().getName() + "value: " + prefixTaskResult);
                    }
                }
        );
        long end = System.currentTimeMillis();
        System.out.println("Run time：" + (end - start));
        Thread.currentThread().join();
    }

}
