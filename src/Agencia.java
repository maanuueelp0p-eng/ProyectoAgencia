import java.util.ArrayList;

public class Agencia {
    private ArrayList<Viaje> catalogo;

    public Agencia() {
        this.catalogo = new ArrayList<>();
    }

    public void añadirViaje(Viaje v) {
        catalogo.add(v);
    }

    public void mostrarCatalogo() {
        for (Viaje v : catalogo) System.out.println(v);
    }

    public Viaje encontrarViajePorId(String id) {
        for (Viaje v : catalogo) {
            if (v.getId().equalsIgnoreCase(id)) return v;
        }
        return null;
    }

    //Metodo comparacion
    public void compararOpcionesDestino(String destinoBusqueda) {
        ArrayList<Viaje> opciones = new ArrayList<>();

        for (Viaje v : catalogo) {
            if (v.getDestino().equalsIgnoreCase(destinoBusqueda)) {
                opciones.add(v);
            }
        }

        if (opciones.isEmpty()) {
            System.out.println("No hay viajes disponibles para " + destinoBusqueda);
            return;
        }

        System.out.println("\n--- OPCIONES PARA " + destinoBusqueda.toUpperCase() + " ---");
        Viaje masRentable = opciones.get(0);

        for (Viaje v : opciones) {
            System.out.println(v);
            if (v.calcularPrecioTotal() < masRentable.calcularPrecioTotal()) {
                masRentable = v;
            }
        }

        System.out.println("\n>> LA OPCIÓN MÁS RENTABLE ES: " + masRentable.getId() + " (" + masRentable.calcularPrecioTotal() + "€)");
    }
}
