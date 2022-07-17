package scene.fifteen;

import common.Something;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 场景15： 基于CompletableFuture.allOf和CompletableFuture.allOfAsync异步运行多个CompletableFuture
 *
 * @author cyan
 * @since 2022/7/17
 */
public class Main {

    public static void main(String[] args) throws Exception {


        Something something = new Something();
        long start = System.currentTimeMillis();

        List<CompletableFuture<String>> futureList = new ArrayList<>(4);
        futureList.add(something.doSomethingOne("100"));
        futureList.add(something.doSomethingOne("101"));
        futureList.add(something.doSomethingOne("102"));
        futureList.add(something.doSomethingOne("103"));

        CompletableFuture<Void> result = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));

        System.out.println("主线程不阻塞日志");
        System.out.println(result.get());

        long end = System.currentTimeMillis();
        System.out.println("Run time：" + (end - start));

    }

}
