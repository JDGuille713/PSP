package EjerciciosMultihilo.Ejercicio4;

public class Consumidor implements Runnable {
    private final ColaLimitada cola;
    private final int idConsumidor;
    private final int totalAConsumir;

    public Consumidor(ColaLimitada cola, int idConsumidor, int totalAConsumir) {
        this.cola = cola;
        this.idConsumidor = idConsumidor;
        this.totalAConsumir = totalAConsumir;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < totalAConsumir; i++) {
                int numero = cola.consumir();
                Thread.sleep((long) (Math.random() * 50));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
