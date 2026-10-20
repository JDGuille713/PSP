package EjerciciosMultihilo.Ejercicio2;

public class Cliente implements Runnable {

    private int numero;
    private Almacen almacen;

    public Cliente(int numero, Almacen almacen) {
        this.numero = numero;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        String hilo = Thread.currentThread().getName();

        for (int intento = 1; intento <= 10; intento++) {

            if (almacen.intentarPuerta()) {
                System.out.println("Cliente " + numero + " entra en el intento " + intento + " (" + hilo + ")");

                try {
                    Thread.sleep(2);   // tiempo que tarda en cruzar la puerta
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (almacen.cogerProducto()) {
                    System.out.println("Cliente " + numero + " coge un producto y se marcha");
                } else {
                    System.out.println("Cliente " + numero + " no encuentra producto y se marcha");
                }

                almacen.salir();
                return;   // el cliente ya ha terminado
            }

            // La puerta estaba ocupada: espera un poco y lo vuelve a intentar
            try {
                Thread.sleep((int) (Math.random() * 80) + 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Cliente " + numero + " se cansa despues de 10 intentos y se marcha");
        almacen.clienteDesiste();
    }
}