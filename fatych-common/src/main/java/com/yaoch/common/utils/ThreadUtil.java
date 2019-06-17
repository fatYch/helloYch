package com.yaoch.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {

    private static ExecutorService threadPoolExecutor =  Executors.newFixedThreadPool(5);


    public static void run(Runnable runnable){
        threadPoolExecutor.execute(runnable);
    }


    public static void main(String[] args){
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Integer.SIZE);
    }








}
