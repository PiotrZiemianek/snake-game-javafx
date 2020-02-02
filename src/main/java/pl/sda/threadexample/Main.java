package pl.sda.threadexample;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start głównego wątku");
//        for (int i = 0; i < 10; i++) {
//            System.out.println("Odliczanie(głównego wątku): " + i);
//        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            System.out.println("Start pobocznego wątku");
            for (int i = 0; i < 10; i++) {
                System.out.println("Odliczanie(pobocznego wątku): " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Koniec pobocznego wątku");
        });
        Thread.sleep(3000);

        System.out.println("Koniec głównego wątku");
    }

}
