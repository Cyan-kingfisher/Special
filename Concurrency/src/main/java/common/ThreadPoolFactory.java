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
        return new ThreadPoolExecutor(
                2, 4, 1,
                TimeUnit.MINUTES, new LinkedBlockingQueue<>(6)
                , new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
