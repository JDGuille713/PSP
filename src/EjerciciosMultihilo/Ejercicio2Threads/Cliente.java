package EjerciciosMultihilo.Ejercicio2Threads;

public class Cliente extends Thread {

    private Almacen almacen;

    public Cliente(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        for (int intento = 1; intento <= 10; intento++) {

            if (almacen.intentarPuerta()) {
                System.out.println(getName() + " entra en el intento " + intento);

                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (almacen.cogerProducto()) {
                    System.out.println(getName() + " coge un producto y se marcha");
                } else {
                    System.out.println(getName() + " no encuentra producto y se marcha");
                }

                almacen.salir();
                return;
            }

            try {
                Thread.sleep((int) (Math.random() * 200) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(getName() + " se cansa despues de 10 intentos y se marcha");
        almacen.clienteDesiste();
    }
}