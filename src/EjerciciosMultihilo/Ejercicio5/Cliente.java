package EjerciciosMultihilo.Ejercicio5;

public class Cliente implements Runnable {
    private final Peluqueria peluqueria;
    private final int id;

    public Cliente(Peluqueria peluqueria, int id) {
        this.peluqueria = peluqueria;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            boolean atendido = peluqueria.intentarEntrar(id);
            if (!atendido) {
                System.out.println("X Cliente " + id + " llega y ve la peluquería llena. Se marcha.");
            } else {
                System.out.println("v Cliente " + id + " ha sido afeitado y se marcha contento.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
