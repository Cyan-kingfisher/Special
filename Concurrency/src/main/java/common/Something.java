package common;

import java.util.concurrent.CompletableFuture;

/**
 * @author cyan
 * @since 2022/7/13
 */
public class Something {

    public void doSomethingA() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA ---");
    }

    public void doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingB ---");
    }

    public String doSomethingC() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingC ---");
        return "Task C Finish";
    }

    public String doSomethingD() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingD ---");
        return "Task D Finish";
    }

    public CompletableFuture<String> doSomethingOne(String studentId) {

        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("method: doSomethingOne, args: " + studentId);
                    return "subjectId:" + studentId;
                }
        );

    }

    public CompletableFuture<String> doSomethingtwo(String subjectId) {

        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("method: doSomethingTwo, args: " + subjectId);
                    return "score:" + subjectId;
                }
        );

    }

}
