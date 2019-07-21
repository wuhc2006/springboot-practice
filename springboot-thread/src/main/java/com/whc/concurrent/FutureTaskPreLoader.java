package com.whc.concurrent;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 预加载演示
 *
 * @author Administrator
 * @date 2019/7/20 20:48
 */
public class FutureTaskPreLoader {

    private final FutureTask<ProductionInfo> future = new FutureTask<>(this::loadProductionInfo);

    private final Thread thread = new Thread(future);

    public void start(){
        thread.start();
    }

    private ProductionInfo loadProductionInfo() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);// 睡眠一段时间
        return new ProductionInfo("Apple", new BigDecimal("10000.00"));
    }

    public ProductionInfo get() throws ExecutionException, InterruptedException {
        return future.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTaskPreLoader taskPreLoader = new FutureTaskPreLoader();
        taskPreLoader.start();
        System.out.println(taskPreLoader.get().toString());
    }

}
