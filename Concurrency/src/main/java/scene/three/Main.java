package scene.three;

import common.Something;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 场景3：显示使用Future、Thread实现异步编程，异步任务有返回信息
 *
 * @author cyan
 * @since 2022/7/14
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Something something = new Something();

        long start = System.currentTimeMillis();

        FutureTask<String> taskC = new FutureTask<>(something::doSomethingC);

        Thread thread = new Thread(taskC, "threadTaskC");
        thread.start();

        String resultTaskD = something.doSomethingD();
        String resultTaskC = taskC.get();

        long end = System.currentTimeMillis();
        System.out.println("Run time: " + (end-start));
        System.out.println("Run result: " + resultTaskC + ", " + resultTaskD);
    }

}
