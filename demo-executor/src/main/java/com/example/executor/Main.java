package com.example.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {
        CompletableFuture<List<Integer>> completableFuture1 = CompletableFuture.supplyAsync(() -> Arrays.asList(1,2,3));
        CompletableFuture<List<Integer>> completableFuture2 = CompletableFuture.supplyAsync(() -> Arrays.asList(4,5,6));

        CompletableFuture<Integer> sum1 = completableFuture1.thenApply(n -> n.stream().reduce(0, Integer::sum));
        CompletableFuture<Integer> sum2 = completableFuture2.thenApply(n -> n.stream().reduce(0, Integer::sum));

        CompletableFuture<Integer> sum = sum1.thenCombine(sum2, Integer::sum);

        sum.thenAccept(s -> System.out.println("Sum: " + s));
    }
}
