package scene.four;

import common.Something;
import common.ThreadPoolFactory;

import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 场景4：使用FutureTask、线程池实现异步编程，异步任务有返回信息
 *
 * @author cyan
 * @since 2022/7/14
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Something something = new Something();
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.create();

        long start = System.currentTimeMillis();

        FutureTask<String> taskC = new FutureTask<>(something::doSomethingC);

        threadPoolExecutor.execute(taskC);

        String resultTaskD = something.doSomethingD();
        String resultTaskC = taskC.get();

        long end = System.currentTimeMillis();
        System.out.println("Run time: " + (end-start));
        System.out.println("Run result: " + resultTaskC + ", " + resultTaskD);
    }

}
