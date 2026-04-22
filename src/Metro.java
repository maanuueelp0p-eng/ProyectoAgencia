public class Metro extends Viaje {
    private int numeroParadas;

    public Metro(String id, String origen, String destino, double precioBase, int numeroParadas) {
        super(id, origen, destino, precioBase);
        this.numeroParadas = numeroParadas;
    }

    @Override
    public double calcularPrecioTotal() {
        double total = precioBase + (numeroParadas * 0.10);
        if (hotelAsociado != null) total += hotelAsociado.getPrecioNoche();
        return total;
    }

    @Override
    public String toString() {
        return "[METRO] " + super.toString() + " | Paradas: " + numeroParadas + " | Total: " + calcularPrecioTotal() + "€";
    }

    public static class Vuelo extends Viaje {
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
}
