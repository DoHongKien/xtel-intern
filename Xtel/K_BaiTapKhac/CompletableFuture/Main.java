package K_BaiTapKhac.CompletableFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> odd = Arrays.asList(1,3,5,7,9);
        List<Integer> even = Arrays.asList(2,4,6,8,10);
        List<Integer> sum = new ArrayList<>();

        CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(() -> {
            sum.addAll(odd);
            sum.addAll(even);
            return sum;
        });

        // Thực hiện trả về kết quả tổng của list
        CompletableFuture<Optional<Integer>> thenApply = future.thenApply(s -> sum.stream().reduce(Integer::sum));

        // Kết hợp 2 CompletableFuture với nhau để tạo ra 1 kết quả khác
        CompletableFuture<Integer> thenCombine = thenApply
                .thenCombine(thenApply, (a, b)
                        -> a.isPresent() && b.isPresent() ? a.get() + b.get() : 0);

        // Thực hiện kết quả in các phần tử và không trả về kết quả
        CompletableFuture<Void> thenAccept = future.thenAccept(s -> s.forEach(System.out::println));

        // Thực hiện hành động không liên quan gì đến kết quả CompletableFuture
        CompletableFuture<Void> thenRun = future.thenRun(() -> System.out.println("OK"));

        // Thực hiện trả về CompletableFuture mới thay vì giá trị của CompletableFuture trước đó
        CompletableFuture<Integer> thenCompose = future.thenCompose(s -> CompletableFuture.supplyAsync(() -> s.size() + 5));

        // Thực hiện xử lý ngoại lệ tạo mới 1 list integer nếu có exception trả ra
        CompletableFuture<List<Integer>> exceptionally = future.exceptionally(ex -> new ArrayList<>());

        // Thực hiện kiểm tra exception, xử lý nó và trả về giá trị
        CompletableFuture<Object> handle = future.handle((result, exception) -> {
            if (exception == null) {
                throw new RuntimeException("Error occurred while processing result: " + result);
            } else {
                return result;
            }
        });

        // Thực thi nhiều CompletableFuture cùng lúc
        CompletableFuture.allOf(thenApply, thenAccept, thenCombine);
    }
}
