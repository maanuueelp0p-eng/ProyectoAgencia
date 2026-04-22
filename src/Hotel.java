import java.time.LocalDate;
import java.util.ArrayList;

public class Hotel {
    private String nombre;
    private double precioNoche;
    private ArrayList<LocalDate> fechasReservadas;

    public Hotel(String nombre, double precioNoche) {
        this.nombre = nombre;
        this.precioNoche = precioNoche;
        this.fechasReservadas = new ArrayList<>();
    }

    public boolean estaDisponible(LocalDate fecha) {
        return !fechasReservadas.contains(fecha);
    }

    public void reservar(LocalDate fecha) {
        fechasReservadas.add(fecha);
    }

    public String getNombre() { return nombre; }
    public double getPrecioNoche() { return precioNoche; }

    @Override
    public String toString() {
        return nombre + " (" + precioNoche + "€/noche)";
    }
}
