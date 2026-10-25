package EjerciciosMultihilo.Ejercicio4;

public class Principal {
    public static void main(String[] args) {
        int capacidadCola = 10;
        ColaLimitada cola = new ColaLimitada(capacidadCola);

        int numProductores = 3;
        int numConsumidores = 3;
        int itemsPorProductor = 20;
        int itemsPorConsumidor = 20;

        Thread[] productores = new Thread[numProductores];
        Thread[] consumidores = new Thread[numConsumidores];

        System.out.println("Iniciando simulación de Productor-Consumidor...");

        for (int i = 0; i < numProductores; i++) {
            productores[i] = new Thread(new Productor(cola, i + 1, itemsPorProductor), "Productor-" + (i + 1));
            productores[i].start();
        }

        for (int i = 0; i < numConsumidores; i++) {
            consumidores[i] = new Thread(new Consumidor(cola, i + 1, itemsPorConsumidor), "Consumidor-" + (i + 1));
            consumidores[i].start();
        }

        try {
            for (int i = 0; i < numProductores; i++) {
                productores[i].join();
            }
            for (int i = 0; i < numConsumidores; i++) {
                consumidores[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulación finalizada correctamente sin errores de concurrencia.");
    }
}
