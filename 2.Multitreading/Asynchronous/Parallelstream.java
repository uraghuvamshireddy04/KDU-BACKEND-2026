import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Parallelstream {
    static void main() {
        List<Integer> numbers = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .toList();
        long start = System.currentTimeMillis();
        long sum = numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long end = System.currentTimeMillis();
        System.out.println("execution time: " +(end - start) + " ms");

        long startParallel = System.currentTimeMillis();
        long sumParallel = numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long endParallel = System.currentTimeMillis();
        System.out.println("execution time: " +(endParallel - startParallel) + " ms");
    }
}
