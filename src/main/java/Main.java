import java.util.concurrent.atomic.AtomicInteger;

import static utils.Utils.*;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                String text = generateText("abc", 100_000);
                try {
                    blockingQueueA.put(text);
                    blockingQueueB.put(text);
                    blockingQueueC.put(text);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        thread.start();

        Thread threadForA = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                try {
                    AtomicInteger intA = new AtomicInteger(0);
                    String symbolA = blockingQueueA.take();
                    for (int j = 0; j < symbolA.length(); j++) {
                        if (symbolA.charAt(j) == 'a') {
                            intA.getAndIncrement();
                        }
                    }
                    if (intA.get() > atomicIntegerA.get()) {
                        atomicIntegerA = intA;
                        atomicReferenceA.set(symbolA);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        threadForA.start();

        Thread threadForB = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                try {
                    AtomicInteger intB = new AtomicInteger(0);
                    String symbolB = blockingQueueB.take();
                    for (int j = 0; j < symbolB.length(); j++) {
                        if (symbolB.charAt(j) == 'b') {
                            intB.getAndIncrement();
                        }
                    }
                    if (intB.get() > atomicIntegerB.get()) {
                        atomicIntegerB = intB;
                        atomicReferenceB.set(symbolB);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        threadForB.start();

        Thread threadForC = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                try {
                    AtomicInteger intC = new AtomicInteger(0);
                    String symbolC = blockingQueueC.take();
                    for (int j = 0; j < symbolC.length(); j++) {
                        if (symbolC.charAt(j) == 'c') {
                            intC.getAndIncrement();
                        }
                    }
                    if (intC.get() > atomicIntegerC.get()) {
                        atomicIntegerC = intC;
                        atomicReferenceC.set(symbolC);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        threadForC.start();

        threadForA.join();
        threadForB.join();
        threadForC.join();

        System.out.println("Максимальное количество символов 'a' - " + atomicIntegerA.get());
        System.out.println("Максимальное количество символов 'b' - " + atomicIntegerB.get());
        System.out.println("Максимальное количество символов 'c' - " + atomicIntegerC.get());


    }


}
