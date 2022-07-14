package scene.five;

import common.Something;
import common.ThreadPoolFactory;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 场景5：使用Future、线程池实现异步编程，异步任务有返回信息
 *
 * @author cyan
 * @since 2022/7/14
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Something something = new Something();
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.create();

        long start = System.currentTimeMillis();

        Future<String> taskC = threadPoolExecutor.submit(something::doSomethingC);

        String resultTaskD = something.doSomethingD();
        String resultTaskC = taskC.get();

        long end = System.currentTimeMillis();
        System.out.println("Run time: " + (end-start));
        System.out.println("Run result: " + resultTaskC + ", " + resultTaskD);
    }

}
