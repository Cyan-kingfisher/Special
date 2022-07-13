package common;

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

}
