package EjerciciosMultihilo.Ejercicio6;

public class Principal {
    public static final int NUM_FILOSOFOS = 5;

    public static void main(String[] args) {
        Mesa mesa = new Mesa(NUM_FILOSOFOS);
        Thread[] hilos = new Thread[NUM_FILOSOFOS];

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            hilos[i] = new Thread(new Filosofo(i, mesa));
            hilos[i].start();
        }
    }
}