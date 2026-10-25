package EjerciciosMultihilo.Ejercicio6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Mesa {
    private final int numFilosofos;
    private final int[] estado;
    private final Condition[] self;
    private final ReentrantLock lock = new ReentrantLock();
    private int countComiendo = 0;

    private static final int PENSANDO = 0;
    private static final int HAMBRIENTO = 1;
    private static final int COMIENDO = 2;

    public Mesa(int numFilosofos) {
        this.numFilosofos = numFilosofos;
        this.estado = new int[numFilosofos];
        this.self = new Condition[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            self[i] = lock.newCondition();
            estado[i] = PENSANDO;
        }
    }

    public void tomarTenedores(int i) throws InterruptedException {
        lock.lock();
        try {
            estado[i] = HAMBRIENTO;
            probar(i);
            while (estado[i] != COMIENDO) {
                self[i].await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void dejarTenedores(int i) {
        lock.lock();
        try {
            estado[i] = PENSANDO;
            countComiendo--;
            for (int j = 0; j < numFilosofos; j++) {
                probar(j);
            }
        } finally {
            lock.unlock();
        }
    }

    private void probar(int i) {
        int izq = (i - 1 + numFilosofos) % numFilosofos;
        int der = (i + 1) % numFilosofos;

        if (estado[i] == HAMBRIENTO && countComiendo < 2 && estado[izq] != COMIENDO && estado[der] != COMIENDO) {
            estado[i] = COMIENDO;
            countComiendo++;
            self[i].signal();
        }
    }
}