package EjerciciosMultihilo.Ejercicio5;

public class Principal {
    public static final int NUM_SILLAS = 4;
    public static final int NUM_BARBEROS = 2;
    public static final int TOTAL_CLIENTES = 15;

    public static void main(String[] args) {
        Peluqueria peluqueria = new Peluqueria(NUM_SILLAS);

        System.out.println("=== ABRE LA PELUQUERÍA ===");
        System.out.println("Configuración: " + NUM_BARBEROS + " barberos, " + NUM_SILLAS + " sillas de espera.\n");

        for (int i = 1; i <= NUM_BARBEROS; i++) {
            final int idBarbero = i;
            Thread tBarbero = new Thread(() -> {
                try {
                    peluqueria.trabajarBarbero(idBarbero);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Barbero-" + i);

            tBarbero.setDaemon(true);
            tBarbero.start();
        }

        for (int i = 1; i <= TOTAL_CLIENTES; i++) {
            Thread tCliente = new Thread(new Cliente(peluqueria, i), "Cliente-" + i);
            tCliente.start();

            try {
                Thread.sleep((long) (Math.random() * 400 + 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nCierre de la peluqueria");
    }
}
