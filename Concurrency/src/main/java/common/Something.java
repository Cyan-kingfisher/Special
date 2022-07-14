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

}
