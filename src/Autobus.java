public class Autobus extends Viaje {
    public Autobus(String id, String origen, String destino, double precioBase) {
        super(id, origen, destino, precioBase);
    }

    @Override
    public double calcularPrecioTotal() {
        double total = precioBase;
        if (hotelAsociado != null) total += hotelAsociado.getPrecioNoche();
        return total;
    }

    @Override
    public String toString() {
        return "[BUS]   " + super.toString() + " | Precio Total: " + calcularPrecioTotal() + "€";
    }
}
