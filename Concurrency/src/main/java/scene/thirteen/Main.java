package scene.thirteen;

import common.Something;

import java.util.concurrent.CompletableFuture;

/**
 * 场景13： 基于CompletableFuture.thenCompose和CompletableFuture.thenComposeAsync实现异步任务
 * ，执行完毕后执行另一个CompletableFuture
 *
 * @author cyan
 * @since 2022/7/17
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Something something = new Something();
        long start = System.currentTimeMillis();

        CompletableFuture<String> completableFuture =
                something.doSomethingOne("104").thenCompose(something::doSomethingtwo);
        System.out.println("主线程不阻塞日志");
        System.out.println("task run result: " + completableFuture.get());

        long end = System.currentTimeMillis();
        System.out.println("Run time：" + (end - start));

    }

}
