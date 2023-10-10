public class Restaurant {
    // kitchen - PROCESS
    static String lock = "LOCK";
    static class kitchen extends Thread {
        

        // CS = CRITICAL SECTION
        /*
         * public static void prepareFood() {
         * // k1, k2, k3, k4
         * System.out.println("With in the Kitchen " +
         * Thread.currentThread().getName());
         * // block
         * // CS - CRITICAL SECTION
         * synchronized (lock) {
         * // lock=0
         * System.out.println("Food Preparation: STARTED " +
         * Thread.currentThread().getName());
         * System.out.println(Thread.currentThread().getName());
         * try {
         * Thread.sleep(3000);
         * } catch (InterruptedException e) {
         * e.printStackTrace();
         * }
         * System.out.println("Food Preparation: DONE " +
         * Thread.currentThread().getName());
         * }
         * }
         */

        // k1, k2, k3

        /*
         * public void run() {
         * // k1, K2, K3
         * System.out.println("The order has taken");
         * prepareFood();
         * }
         */
    }

    public static void main(String[] args) {
        //static final Object lock = new Object();
        //String lock = "LOCK";
        // New Concept or way to implement multithreading and that is by using Runnable
        // Class Instance
        Runnable runnable = new Runnable() {
            public void prepareFood() {
                System.out.println("With in the Kitchen " + Thread.currentThread().getName());
                // block
                // CS - CRITICAL SECTION
                synchronized (lock) {
                    System.out.println("Food Preparation: STARTED " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Food Preparation: DONE " + Thread.currentThread().getName());
                }
            }

            @Override
            public void run() {
                System.out.println("The order has taken " + Thread.currentThread().getName());
                prepareFood();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();

        /*kitchen k1 = new kitchen();
        kitchen k2 = new kitchen();
        kitchen k3 = new kitchen();

        k1.start(); // 1st customer
        k2.start(); // 2nd customer
        k3.start(); // 3rd customer*/

    }
}