import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Agencia miAgencia = new Agencia();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Hoteles
        Hotel hGranada = new Hotel("Hotel Alhambra Palace", 120.50);
        Hotel hParis = new Hotel("Le Meurice Paris", 250.00);
        Hotel hLondres = new Hotel("The Ritz London", 300.00);
        Hotel hIbiza = new Hotel("Ushuaia Beach", 180.00);
        Hotel hTokio = new Hotel("Park Hyatt Tokyo", 400.00);
        Hotel hLowCost = new Hotel("Hostal Económico", 35.00);

        // Catalogo
        Autobus g1 = new Autobus("GRA-01", "Madrid", "Granada", 18.50);
        g1.setHotel(hGranada);
        miAgencia.añadirViaje(g1);

        Metro g2 = new Metro("GRA-02", "Motril", "Granada", 10, 8);
        miAgencia.añadirViaje(g2); // Sin hotel


        Metro.Vuelo p1 = new Metro.Vuelo("PAR-01", "Barcelona", "Paris", 85.00, 25.00);
        p1.setHotel(hParis);
        miAgencia.añadirViaje(p1);

        Autobus p2 = new Autobus("PAR-02", "Madrid", "Paris", 55.00);
        p2.setHotel(hLowCost);
        miAgencia.añadirViaje(p2);

        Metro.Vuelo l1 = new Metro.Vuelo("LON-01", "Madrid", "Londres", 110.00, 35.00);
        l1.setHotel(hLondres);
        miAgencia.añadirViaje(l1);


        Barco i1 = new Barco("IBZ-01", "Denia", "Ibiza", 65.00, true);
        i1.setHotel(hIbiza);
        miAgencia.añadirViaje(i1);

        Metro.Vuelo i2 = new Metro.Vuelo("IBZ-02", "Valencia", "Ibiza", 40.00, 15.00);
        miAgencia.añadirViaje(i2); // Sin hotel


        Metro.Vuelo t1 = new Metro.Vuelo("TOK-01", "Madrid", "Tokio", 850.00, 65.00);
        t1.setHotel(hTokio);
        miAgencia.añadirViaje(t1);


        Autobus s1 = new Autobus("SEV-01", "Madrid", "Sevilla", 22.00);
        miAgencia.añadirViaje(s1);

        Barco m1 = new Barco("MLC-01", "Malaga", "Melilla", 45.00, false);
        miAgencia.añadirViaje(m1);

        //Inicio programa
        double miDinero = 0;
        System.out.println("BIENVENIDO A LA AGENCIA TRIPTRAVEL");


        while (true) {
            try {
                System.out.print("Introduce tu presupuesto inicial (€): ");
                miDinero = Double.parseDouble(sc.nextLine());
                break;
            } catch (Exception e) { System.out.println("Número no válido."); }
        }

        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ (Saldo: " + String.format("%.2f", miDinero) + "€) ---");
            System.out.println("1. Catálogo Completo");
            System.out.println("2. Comparar Opciones por Destino");
            System.out.println("3. Reservar por ID");
            System.out.println("4. Salir");
            System.out.print("Elige: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        miAgencia.mostrarCatalogo();
                        break;
                    case 2:
                        System.out.print("Introduce destino (Granada, Paris, Ibiza...): ");
                        String d = sc.nextLine();
                        miAgencia.compararOpcionesDestino(d);
                        break;
                    case 3:
                        System.out.print("Introduce ID del viaje: ");
                        String id = sc.nextLine();
                        Viaje sel = miAgencia.encontrarViajePorId(id);

                        if (sel != null) {
                            double coste = sel.calcularPrecioTotal();
                            if (miDinero >= coste) {
                                if (sel.getHotel() != null) {
                                    System.out.print("Fecha para " + sel.getHotel().getNombre() + " (dd/mm/aaaa): ");
                                    LocalDate f = LocalDate.parse(sc.nextLine(), formato);
                                    if (sel.getHotel().estaDisponible(f)) {
                                        sel.getHotel().reservar(f);
                                        miDinero -= coste;
                                        System.out.println("¡Reserva con hotel confirmada!");
                                    } else {
                                        System.out.println("Fecha no disponible.");
                                    }
                                } else {
                                    miDinero -= coste;
                                    System.out.println("¡Reserva (solo transporte) confirmada!");
                                }
                            } else {
                                System.out.println("No tienes suficiente saldo.");
                            }
                        } else {
                            System.out.println("ID no encontrado.");
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error en los datos introducidos.");
            }
        } while (opcion != 4);
        sc.close();
    }
}