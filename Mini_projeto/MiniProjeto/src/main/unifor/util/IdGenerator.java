package util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private static final AtomicLong matriculaCounter = new AtomicLong(1);
    private static final AtomicLong reservaCounter = new AtomicLong(1);

    public static Long generateMatriculaId() {
        return matriculaCounter.getAndIncrement();
    }

    public static Long generateReservaId() {
        return reservaCounter.getAndIncrement();
    }

    public static void resetCounters() {
        matriculaCounter.set(1);
        reservaCounter.set(1);
    }
}