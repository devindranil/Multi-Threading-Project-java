public class DeadLockDemo {
    public static void main(String[] args) {
       final Object resource1 = "This is resource one";
       final Object resource2 = "This is resource two";
       //thread 1 tries to lock resource1
       Thread t1 = new Thread(){
        public void run(){
            //String n = Thread.currentThread().getName();
            synchronized(resource1){
                System.out.println("Thread 1: locked resource 1 ");
                try{
                Thread.sleep(100);
                }catch(Exception e){}

                synchronized(resource2){
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        }
       };

        //thread 2 tries to lock resource2
       Thread t2 = new Thread(){
        public void run(){
            
           // String n = Thread.currentThread().getName();
            synchronized(resource1){
                System.out.println("Thread 2: locked resource 2 ");
                try{
                Thread.sleep(100);
                }catch(Exception e){}

                synchronized(resource2){
                    System.out.println("Thread 2: locked resource 1 " );
                }
            }
        }
       };

       /*t1.setName("Rohan");
       t2.setName("Debraj");*/

       t1.start();
       t2.start();
    }
}
