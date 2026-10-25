package EjerciciosMultihilo.Ejercicio4;

public class ColaLimitada {
    private final int[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;
    private final int capacidad;

    public ColaLimitada(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new int[capacidad];
    }

    public synchronized void producir(int valor) throws InterruptedException {
        while (count == capacidad) {
            wait();
        }

        buffer[tail] = valor;
        tail = (tail + 1) % capacidad;
        count++;

        notifyAll();
    }

    public synchronized int consumir() throws InterruptedException {
        while (count == 0) {
            wait();
        }

        int valor = buffer[head];
        head = (head + 1) % capacidad;
        count--;

        notifyAll();
        return valor;
    }
}
