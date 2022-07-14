package scene.two;

import common.Something;
import common.ThreadPoolFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 场景2：显示使用线程池实现异步编程
 *
 * @author cyan
 * @since 2022/7/14
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.create();

        Something something = new Something();
        long start = System.currentTimeMillis();
        // 2为并发任务数量
        CountDownLatch latch = new CountDownLatch(2);
        threadPoolExecutor.execute(
                () -> {
                    something.doSomethingA();
                    latch.countDown();
                }
        );
        threadPoolExecutor.execute(
                () -> {
                    something.doSomethingB();
                    latch.countDown();
                }
        );
        // latch为0向下执行，＞0时线程阻塞
        latch.await();
        System.out.println(System.currentTimeMillis() - start);
        Thread.currentThread().join();
    }

}
