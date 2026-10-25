package EjerciciosMultihilo.Ejercicio6;

public class Filosofo implements Runnable {
    private final int id;
    private final Mesa mesa;

    public Filosofo(int id, Mesa mesa) {
        this.id = id;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("El filósofo " + id + " está pensando");
                Thread.sleep((long) (Math.random() * 4000 + 1000));

                mesa.tomarTenedores(id);

                System.out.println("Filósofo " + id + " comiendo");
                Thread.sleep((long) (Math.random() * 2000 + 1000));

                mesa.dejarTenedores(id);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
