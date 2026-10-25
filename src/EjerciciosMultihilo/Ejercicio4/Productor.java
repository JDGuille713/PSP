package EjerciciosMultihilo.Ejercicio4;

public class Productor implements Runnable {
    private final ColaLimitada cola;
    private final int idProductor;
    private final int totalAProducir;

    public Productor(ColaLimitada cola, int idProductor, int totalAProducir) {
        this.cola = cola;
        this.idProductor = idProductor;
        this.totalAProducir = totalAProducir;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < totalAProducir; i++) {
                int numero = (int) (Math.random() * 1000) + 1; // Número aleatorio a producir
                cola.producir(numero);

                // Pequeña pausa opcional para simular el tiempo de trabajo del productor
                Thread.sleep((long) (Math.random() * 50));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
