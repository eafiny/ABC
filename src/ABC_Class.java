public class ABC_Class {
    static Object monitor = new Object();
    static volatile String letter = "A";
    static final int numIteration = 5;

    public static void main(String[] args) {
        new Thread(()->{
            try{
                for(int i = 0; i < numIteration; i++){
                    synchronized (monitor){
                        while (letter != "A"){
                            monitor.wait();
                        }
                        System.out.print("A");
                        letter = "B";
                        monitor.notifyAll();
                    }
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try{
                for(int i = 0; i < numIteration; i++){
                    synchronized (monitor){
                        while (letter != "B"){
                            monitor.wait();
                        }
                        System.out.print("B");
                        letter = "C";
                        monitor.notifyAll();
                    }
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try{
                for(int i = 0; i < numIteration; i++){
                    synchronized (monitor){
                        while (letter != "C"){
                            monitor.wait();
                        }
                        System.out.println("C");
                        letter = "A";
                        monitor.notifyAll();
                    }
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}
