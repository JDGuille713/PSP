package EjerciciosMultihilo.Ejercicio3;

public class Movimiento implements Runnable {
    private final Cuenta cuenta;
    private final int cantidad;
    private final boolean esIngreso;

    public Movimiento(Cuenta cuenta, int cantidad, boolean esIngreso) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.esIngreso = esIngreso;
    }

    @Override
    public void run() {
        if (esIngreso) {
            cuenta.ingresar(cantidad);
        } else {
            cuenta.retirar(cantidad);
        }
    }
}
