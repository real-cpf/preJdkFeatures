package virtualtask;

import java.security.*;
import java.time.Duration;
import java.util.concurrent.*;
import java.util.stream.IntStream;


public class SimpleExecutors {
  private final static ThreadFactory factory = Thread.ofVirtual().factory();
  private final ExecutorService service = Executors.newThreadPerTaskExecutor(factory);

  public static void main(String[] args) throws InterruptedException {

    Thread.Builder.OfVirtual virtual = Thread.ofVirtual();
    virtual.start(()->{
      System.out.println(Thread.currentThread());
    }).join();

  }
  public static void simple() {

    SimpleExecutors executors = new SimpleExecutors();
    IntStream.range(0,10_000).forEach(i->{
      executors.runAsync(()->{
        System.out.printf("start [%d] %n" +Thread.currentThread(),i);
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.printf("end [%d] %n" + Thread.currentThread(),i);
      });
    });

    executors.shutdown();
    try {
      TimeUnit.SECONDS.sleep(15);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  public void shutdown(){
    service.shutdown();
  }
  public CompletableFuture<Void>  runAsync(Runnable runnable){
    return CompletableFuture.runAsync(runnable,service);
  }
}
