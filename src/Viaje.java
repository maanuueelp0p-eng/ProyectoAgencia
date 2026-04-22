public abstract class Viaje {
    protected String id;
    protected String origen;
    protected String destino;
    protected double precioBase;
    protected Hotel hotelAsociado;

    public Viaje(String id, String origen, String destino, double precioBase) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.precioBase = precioBase;
    }

    public void setHotel(Hotel h) { this.hotelAsociado = h; }
    public Hotel getHotel() { return hotelAsociado; }
    public String getId() { return id; }
    public String getDestino() { return destino; }

    //Metodo a implementar
    public abstract double calcularPrecioTotal();

    @Override
    public String toString() {
        String infoHotel = (hotelAsociado != null) ? " + Hotel: " + hotelAsociado.getNombre() : " (Sin hotel)";
        return "ID: [" + id + "] " + origen + " -> " + destino + infoHotel;
    }
}
