package multithreading;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// Each thread will receive their own copy of this
class ThreadSafeFormatter {
  public static ThreadLocal<SimpleDateFormat> dateFormatter = ThreadLocal
    .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
}

class UserService {
  public static LocalDate birthDateFromDB(int userId){
    LocalDate start = LocalDate.of(2020, 1, 1);
    LocalDate end = LocalDate.of(2030, 12, 31);

    long days = end.toEpochDay() - start.toEpochDay();

    Random random = new Random(userId);
    long randomDays = random.nextLong(days + 1);

    return start.plusDays(randomDays);
  }

  public String birthDate(int userId){
    LocalDate birthDate = birthDateFromDB(userId);
    final SimpleDateFormat df = ThreadSafeFormatter.dateFormatter.get();
    return df.format(birthDate);
  }
}

public class ThreadPoolExample {
  private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 1000; i++){
      int id = i;
      threadPool.submit(() -> {
        String birthDate = new UserService().birthDate(id);
        System.out.printf("User: %d -> %s | Thread: %s\n", id, birthDate, Thread.currentThread().getName());
      });
    }
    Thread.sleep(1000);
//    threadPool.shutdown();
  }
}
