public class Barco extends Viaje {
    private boolean tieneCamarote;

    public Barco(String id, String origen, String destino, double precioBase, boolean tieneCamarote) {
        super(id, origen, destino, precioBase);
        this.tieneCamarote = tieneCamarote;
    }

    @Override
    public double calcularPrecioTotal() {
        double total = precioBase + (tieneCamarote ? 50.0 : 0.0);
        if (hotelAsociado != null) total += hotelAsociado.getPrecioNoche();
        return total;
    }

    @Override
    public String toString() {
        return "[BARCO] " + super.toString() + " | Precio Total: " + calcularPrecioTotal() + "€";
    }
}


