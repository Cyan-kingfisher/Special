package scene.seven;

import common.Something;
import common.ThreadPoolFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 场景7： 基于CompletableFuture.runAsync实现无返回值的异步计算
 *
 * @author cyan
 * @since 2022/7/15
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Something something = new Something();
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.create();

        long start = System.currentTimeMillis();

        /**
         * 默认线程池为ForkJoinPool
         * CompletableFuture completableFuture = CompletableFuture.runAsync(something::doSomethingC);
         */
        CompletableFuture completableFuture = CompletableFuture.runAsync(something::doSomethingC, threadPoolExecutor);
        String resultTaskD = something.doSomethingD();
        System.out.println("thread run result: " + completableFuture.get() + ", main thread result: " + resultTaskD);

        long end = System.currentTimeMillis();
        System.out.println("Run time：" + (end - start));

    }

}
