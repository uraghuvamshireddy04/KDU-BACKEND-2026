import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Sum {

    public static void main(String[] args) {
        int N = 10;
        Callable<Integer> totalSum = () -> {
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                sum += i;
            }
            return sum;
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<Integer> future = executor.submit(totalSum);
            Integer result = future.get();
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
