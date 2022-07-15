package scene.seven;

import common.Something;
import common.ThreadPoolFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 场景7：CompletableFuture显式设置计算结果
 * 核心方法：completableFuture.complete()
 *
 * @author cyan
 * @since 2022/7/15
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.create();
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Something something = new Something();

        long start = System.currentTimeMillis();

        threadPoolExecutor.execute(() -> completableFuture.complete(something.doSomethingC()));
        String resultTaskD = something.doSomethingD();
        System.out.println("thread run result: " + completableFuture.get() + ", main thread result: " + resultTaskD);

        long end = System.currentTimeMillis();
        System.out.println("Run time：" + (end - start));

    }

}
