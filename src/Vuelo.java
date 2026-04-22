public class Vuelo extends Viaje {
    private double tasaAeropuerto;

    public Vuelo(String id, String origen, String destino, double precioBase, double tasa) {
        super(id, origen, destino, precioBase);
        this.tasaAeropuerto = tasa;
    }

    @Override
    public double calcularPrecioTotal() {
        double total = precioBase + tasaAeropuerto;
        if (hotelAsociado != null) total += hotelAsociado.getPrecioNoche();
        return total;
    }

    @Override
    public String toString() {
        return "[AVIÓN] " + super.toString() + " | Precio Total: " + calcularPrecioTotal() + "€";
    }
}