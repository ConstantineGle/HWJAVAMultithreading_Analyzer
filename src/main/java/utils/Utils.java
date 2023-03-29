package utils;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Utils {

    public static ArrayBlockingQueue<String> blockingQueueA = new ArrayBlockingQueue<>(100);
    public static ArrayBlockingQueue<String> blockingQueueB = new ArrayBlockingQueue<>(100);
    public static ArrayBlockingQueue<String> blockingQueueC = new ArrayBlockingQueue<>(100);

    public static AtomicInteger atomicIntegerA = new AtomicInteger(0);
    public static AtomicInteger atomicIntegerB = new AtomicInteger(0);
    public static AtomicInteger atomicIntegerC = new AtomicInteger(0);

    public static AtomicReference<String> atomicReferenceA = new AtomicReference<>();
    public static AtomicReference<String> atomicReferenceB = new AtomicReference<>();
    public static AtomicReference<String> atomicReferenceC = new AtomicReference<>();

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
