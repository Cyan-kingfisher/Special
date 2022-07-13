package scene.one;

import common.Something;

/**
 * 场景一：2种方法显式开启一个线程异步处理，关键类Thread
 *
 * @author cyan
 * @since 2022/7/13
 */
public class Main {

    private static final Something something = new Something();

    public static void synchronously() {
        System.out.println("同步运行：");
        something.doSomethingA();
        something.doSomethingB();
    }

    /**
     * 使用类A实现run方法，将类A作为Thread的创建参数
     * public Thread(Runnable target, String name)
     */
    public static void coreOne() throws Exception{
        System.out.println("异步运行:");
        Thread aThread = new Thread(something::doSomethingA, "thread-A");
        aThread.start();
        something.doSomethingB();
        aThread.join();
    }

    /**
     * 实现Thread类，重写run方法
     * public Thread(Runnable target, String name)
     */
    public static void coreTwo() throws Exception{
        System.out.println("异步运行:");
        Thread aThread = new Thread("thread-A") {
            @Override
            public void run() {
                something.doSomethingA();
            }
        };
        aThread.start();
        something.doSomethingB();
        aThread.join();
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        synchronously();
        long end = System.currentTimeMillis();
        System.out.println("Run time: " + (end-start));
        System.out.println("-----------------");
        start = System.currentTimeMillis();
        coreOne();
        end = System.currentTimeMillis();
        System.out.println("Run time: " + (end-start));
        System.out.println("-----------------");
        start = System.currentTimeMillis();
        coreTwo();
        end = System.currentTimeMillis();
        System.out.println("Run time: " + (end-start));
        System.out.println("-----------------");
    }

}
