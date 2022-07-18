package scene.eighteen;

import common.Something;
import common.ThreadPoolFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * 场景18： Stream与CompletableFuture并发运行某方法
 *
 * @author cyan
 * @since 2022/7/18
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.create();
        Something something = new Something();
        List<String> params = new ArrayList<>();
        String baseIp = "192.168.0.";
        for (int i = 0; i < 20; i++) {
            params.add(baseIp + i);
        }

        /**
         * the first method
         */
        List<CompletableFuture<String>> futureList = params.stream()
                .map(ip -> CompletableFuture.supplyAsync(() -> something.rpcCall(ip))).collect(Collectors.toList());
        List<String> asyncResult = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        asyncResult.forEach(System.out::println);

        /**
         * the second method
         *
         * CompletableFuture.allOf(params.stream().map(ip -> CompletableFuture.supplyAsync(() -> something.rpcCall(ip))).toArray(CompletableFuture[]::new));
         * Thread.currentThread().join();
         */

        /**
         * third method
         *
         * CompletableFuture.allOf(params.stream().map(ip -> CompletableFuture.supplyAsync(() -> something.rpcCall(ip), threadPoolExecutor)).toArray(CompletableFuture[]::new));
         */
    }

}
