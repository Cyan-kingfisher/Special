package scene.seventeen;

import java.util.concurrent.CompletableFuture;

/**
 * 场景17： 基于CompletableFuture.completeExceptionally处理异常，使其进程退出
 * 使用上述方法处理异常后，可使用exceptionally设置默认结果值，程序正常运行
 *
 * 若不使用上述方法处理异常，异常被catch捕获，然后主线程由于获取不到结果
 * 被阻塞，无法运行后面的程序
 *
 * @author cyan
 * @since 2022/7/17
 */
public class Main {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Thread thread = new Thread(
                () -> {
                    try {
                        if (true) {
                            throw new InterruptedException("xxxx");
                        }
                        completableFuture.complete("ok");
                    } catch (Exception exception) {
                        completableFuture.completeExceptionally(exception);
                    }
                }, "thread name self"
        );
        thread.start();

        System.out.println("主线程不阻塞日志");
        System.out.println(completableFuture.get());
//        System.out.println(completableFuture.exceptionally(t->"error").get());
        long end = System.currentTimeMillis();
        System.out.println("Run time：" + (end - start));
    }


}
