import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    // Lock object
    static Object key = new Object(); // sharable resource
    // QUEUE- Momo Plate
    static Queue<Integer> queue = new LinkedList<Integer>(); // Internal Item store = count++ and count--
    // NO of Momo Item
    static int size = 10;

    public static void main(String[] args) {
        // Producer Thread
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                // NO OF MOMO
                int count = 0;
                while (true) {
                    // CS = CRITICAL SECTION
                    synchronized (key) {
                        try {
                            while (queue.size() == size) {
                                // Queue is full, producer should wait
                                key.wait();
                            }
                            // Item insert
                            queue.offer(count++);
                            // Green signal
                            key.notifyAll();
                            // Display message
                            System.out.println("MOMO Producer, plate size: " + queue.size()); // plate increase
                            // Thread sleep
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }); // producer

        // Consumer Thread
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // CS = CRITICAL SECTION
                    synchronized (key) {
                        try {
                            while (queue.size() == 0) {
                                // Queue is empty, consumer should wait
                                key.wait();
                            }
                            // Item Pop from the queue
                            queue.poll();
                            // Green signal
                            key.notifyAll();
                            // Display message
                            System.out.println("MOMO Consumed, plate size: " + queue.size()); // plate size decrease
                            // Thread sleep
                            Thread.sleep(800);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }); // consumer

        // Thread start
        producer.start();
        consumer.start();
    }// main
}
