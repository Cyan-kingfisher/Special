package common;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author cyan
 * @since 2022/7/14
 */
public class ThreadPoolFactory {

    public static ThreadPoolExecutor create() {

        final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

        return new ThreadPoolExecutor(
                AVALIABLE_PROCESSORS, AVALIABLE_PROCESSORS * 2, 1,
                TimeUnit.MINUTES, new LinkedBlockingQueue<>(AVALIABLE_PROCESSORS * 2)
                , new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
