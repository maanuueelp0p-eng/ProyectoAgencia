import java.util.Scanner;
import java.time.LocalDate;


public class Main {


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        Agencia miAgencia = new Agencia();


        // Hoteles
        Hotel hSevilla = new Hotel("Hotel Giralda", 140);
        Hotel hValencia = new Hotel("Hotel Mediterráneo", 150);
        Hotel hBilbao = new Hotel("Hotel Guggenheim", 170);
        Hotel hIbiza = new Hotel("Ushuaia Beach", 220);
        Hotel hParis = new Hotel("Le Meurice Paris", 300);
        Hotel hRoma = new Hotel("Roma Imperial", 250);
        Hotel hLondres = new Hotel("The Ritz London", 350);
        Hotel hBerlin = new Hotel("Berlin Central", 220);
        Hotel hTokio = new Hotel("Park Hyatt Tokyo", 450);
        Hotel hNewYork = new Hotel("Times Square Hotel", 520);


        // NUEVOS HOTELES CONTINENTALES
        Hotel hLisboa = new Hotel("Lisboa Palace", 210);
        Hotel hAmsterdam = new Hotel("Canal View Hotel", 280);
        Hotel hViena = new Hotel("Vienna Royal", 260);
        Hotel hPraga = new Hotel("Prague Center", 230);
        Hotel hAtenas = new Hotel("Athens Classic", 240);
        Hotel hBruselas = new Hotel("Brussels Grand", 250);
        Hotel hZurich = new Hotel("Zurich Elite", 320);
        Hotel hDublin = new Hotel("Dublin Green", 245);
        Hotel hEstocolmo = new Hotel("Nordic Sky", 310);


        // Viajes nacionales
        Autobus v1 = new Autobus("ESP-01", "Barcelona", "Valencia", 28);
        v1.setHotel(hValencia);
        miAgencia.añadirViaje(v1);


        Autobus s1 = new Autobus("ESP-02", "Granada", "Sevilla", 22);
        s1.setHotel(hSevilla);
        miAgencia.añadirViaje(s1);


        Vuelo b1 = new Vuelo("ESP-03", "Malaga", "Bilbao", 75, 18);
        b1.setHotel(hBilbao);
        miAgencia.añadirViaje(b1);


        Barco i1 = new Barco("ESP-04", "Valencia", "Ibiza", 65, true);
        i1.setHotel(hIbiza);
        miAgencia.añadirViaje(i1);


        Metro m1 = new Metro("ESP-05", "Hospitalet", "Barcelona", 12, 10);
        miAgencia.añadirViaje(m1);


        // Viajes internacionales
        Vuelo p1 = new Vuelo("INT-01", "Barcelona", "Paris", 95, 20);
        p1.setHotel(hParis);
        miAgencia.añadirViaje(p1);


        Vuelo r1 = new Vuelo("INT-02", "Valencia", "Roma", 105, 22);
        r1.setHotel(hRoma);
        miAgencia.añadirViaje(r1);


        Vuelo l1 = new Vuelo("INT-03", "Bilbao", "Londres", 120, 28);
        l1.setHotel(hLondres);
        miAgencia.añadirViaje(l1);


        Vuelo be1 = new Vuelo("INT-04", "Malaga", "Berlin", 115, 25);
        be1.setHotel(hBerlin);
        miAgencia.añadirViaje(be1);


        Vuelo t1 = new Vuelo("INT-05", "Barcelona", "Tokio", 780, 60);
        t1.setHotel(hTokio);
        miAgencia.añadirViaje(t1);


        Vuelo n1 = new Vuelo("INT-06", "Sevilla", "New York", 690, 55);
        n1.setHotel(hNewYork);
        miAgencia.añadirViaje(n1);


        // VIAJES CONTINENTALES AÑADIDOS (hasta 20 viajes totales)


        Vuelo c1 = new Vuelo("CON-01", "Madrid", "Lisboa", 85, 18);
        c1.setHotel(hLisboa);
        miAgencia.añadirViaje(c1);


        Vuelo c2 = new Vuelo("CON-02", "Barcelona", "Amsterdam", 140, 25);
        c2.setHotel(hAmsterdam);
        miAgencia.añadirViaje(c2);


        Vuelo c3 = new Vuelo("CON-03", "Valencia", "Viena", 155, 26);
        c3.setHotel(hViena);
        miAgencia.añadirViaje(c3);


        Vuelo c4 = new Vuelo("CON-04", "Sevilla", "Praga", 160, 28);
        c4.setHotel(hPraga);
        miAgencia.añadirViaje(c4);


        Vuelo c5 = new Vuelo("CON-05", "Bilbao", "Atenas", 180, 30);
        c5.setHotel(hAtenas);
        miAgencia.añadirViaje(c5);


        Vuelo c6 = new Vuelo("CON-06", "Malaga", "Bruselas", 145, 24);
        c6.setHotel(hBruselas);
        miAgencia.añadirViaje(c6);


        Vuelo c7 = new Vuelo("CON-07", "Madrid", "Zurich", 170, 27);
        c7.setHotel(hZurich);
        miAgencia.añadirViaje(c7);


        Vuelo c8 = new Vuelo("CON-08", "Barcelona", "Dublin", 165, 29);
        c8.setHotel(hDublin);
        miAgencia.añadirViaje(c8);


        Vuelo c9 = new Vuelo("CON-09", "Valencia", "Estocolmo", 210, 35);
        c9.setHotel(hEstocolmo);
        miAgencia.añadirViaje(c9);


        // Inicio
        double dinero = 0;


        System.out.println("=================================");
        System.out.println("      BIENVENIDO A TRIPTRAVEL");
        System.out.println("=================================");


        while (true) {
            try {
                System.out.print("Introduce presupuesto (€): ");
                dinero = Double.parseDouble(sc.nextLine());


                if (dinero >= 0) {
                    break;
                }


            } catch (Exception e) {
                System.out.println("Cantidad incorrecta.");
            }
        }


        int opcion = 0;


        do {
            System.out.println("\n========= MENÚ =========");
            System.out.println("Saldo actual: " + String.format("%.2f", dinero) + " €");
            System.out.println("1. Ver catálogo");
            System.out.println("2. Buscar destino");
            System.out.println("3. Reservar viaje");
            System.out.println("4. Salir");
            System.out.print("Elige opción: ");


            try {
                opcion = Integer.parseInt(sc.nextLine());


                switch (opcion) {


                    case 1:
                        miAgencia.mostrarCatalogo();
                        break;


                    case 2:
                        System.out.print("Introduce destino: ");
                        String destino = sc.nextLine();
                        miAgencia.compararOpcionesDestino(destino);
                        break;


                    case 3:


                        System.out.print("Introduce ID del viaje: ");
                        String id = sc.nextLine();


                        Viaje sel = miAgencia.encontrarViajePorId(id);


                        if (sel == null) {
                            System.out.println("Viaje no encontrado.");
                            break;
                        }


                        double coste = sel.calcularPrecioTotal();


                        if (dinero < coste) {
                            System.out.println("Saldo insuficiente.");
                            break;
                        }


                        LocalDate hoy = LocalDate.now();
                        LocalDate fechaViaje;


                        while (true) {
                            try {
                                System.out.print("Introduce fecha del viaje (dd/MM/yyyy): ");


                                String texto = sc.nextLine();
                                String[] partes = texto.split("/");


                                int dia = Integer.parseInt(partes[0]);
                                int mes = Integer.parseInt(partes[1]);
                                int año = Integer.parseInt(partes[2]);


                                fechaViaje = LocalDate.of(año, mes, dia);


                                if (fechaViaje.isBefore(hoy)) {
                                    System.out.println("Error: no puedes elegir una fecha anterior a hoy.");
                                } else {
                                    break;
                                }


                            } catch (Exception e) {
                                System.out.println("Fecha incorrecta.");
                            }
                        }


                        dinero -= coste;


                        System.out.println("\n======= TICKET VIAJE =======");
                        System.out.println("ID: " + sel.getId());
                        System.out.println("Origen: " + sel.getOrigen());
                        System.out.println("Destino: " + sel.getDestino());
                        System.out.println("Fecha: " + fechaViaje);
                        System.out.println("Precio: " + String.format("%.2f", coste) + " €");


                        System.out.println("\n======= TICKET SALDO =======");
                        System.out.println("Saldo restante: " + String.format("%.2f", dinero) + " €");
                        break;


                    case 4:
                        System.out.println("Gracias por usar TripTravel.");
                        break;


                    default:
                        System.out.println("Opción incorrecta.");
                }


            } catch (Exception e) {
                System.out.println("Error.");
            }


        } while (opcion != 4);
    }
}

