package scene.fourteen;

import common.Something;

import java.util.concurrent.CompletableFuture;

/**
 * 场景14： 基于CompletableFuture.thenCombine和CompletableFuture.thenCombineAsync异步运行两个任务AB, 任务A和任务B的结果作为参数再执行一个异步任务
 *
 * @author cyan
 * @since 2022/7/17
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Something something = new Something();
        long start = System.currentTimeMillis();

        CompletableFuture<String> completableFuture = something.doSomethingOne("104")
                .thenCombine(something.doSomethingtwo("sub02505"),
                        (one, two) -> {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "oneSomethingResult: " + one + ", oneSomethingResult: " + two;
                        });
        System.out.println("主线程不阻塞日志");
        System.out.println(completableFuture.get());

        long end = System.currentTimeMillis();

        /**
         * Run time：3066
         */
        System.out.println("Run time：" + (end - start));

    }

}
