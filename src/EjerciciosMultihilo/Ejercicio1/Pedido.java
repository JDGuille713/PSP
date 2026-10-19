package EjerciciosMultihilo.Ejercicio1;

public class Pedido implements Runnable {
    private int numero;
    public Pedido(int numero    ) {
        this.numero = numero;
    }

    @Override
    public void run() {
        System.out.println("Preparando pedido " + numero + " en " + Thread.currentThread().getName());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Pedido " + numero + " finalizado.");
    }
}
