package virtualtask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class SimpleExecutors {
  public static void main(String[] args) {
    ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();

    ExecutorService service1 = Executors.newThreadPerTaskExecutor(new ThreadFactory() {
      @Override
      public Thread newThread(Runnable r) {
        return null;
      }
    });
  }
}
