import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class TripTravelGUI extends JFrame {

    private double saldo = 0;

    private static final String[][] VIAJES = {
            {"ESP-01","Barcelona","Valencia","Autobus","Hotel Mediterraneo","150","28"},
            {"ESP-02","Granada","Sevilla","Autobus","Hotel Giralda","140","22"},
            {"ESP-03","Malaga","Bilbao","Vuelo","Hotel Guggenheim","170","75"},
            {"ESP-04","Valencia","Ibiza","Barco","Ushuaia Beach","220","65"},
            {"ESP-05","Hospitalet","Barcelona","Metro","Sin hotel","0","12"},
            {"INT-01","Barcelona","Paris","Vuelo","Le Meurice Paris","300","95"},
            {"INT-02","Valencia","Roma","Vuelo","Roma Imperial","250","105"},
            {"INT-03","Bilbao","Londres","Vuelo","The Ritz London","350","120"},
            {"INT-04","Malaga","Berlin","Vuelo","Berlin Central","220","115"},
            {"INT-05","Barcelona","Tokio","Vuelo","Park Hyatt Tokyo","450","780"},
            {"INT-06","Sevilla","New York","Vuelo","Times Square Hotel","520","690"},
            {"CON-01","Madrid","Lisboa","Vuelo","Lisboa Palace","210","85"},
            {"CON-02","Barcelona","Amsterdam","Vuelo","Canal View Hotel","280","140"},
            {"CON-03","Valencia","Viena","Vuelo","Vienna Royal","260","155"},
            {"CON-04","Sevilla","Praga","Vuelo","Prague Center","230","160"},
            {"CON-05","Bilbao","Atenas","Vuelo","Athens Classic","240","180"},
            {"CON-06","Malaga","Bruselas","Vuelo","Brussels Grand","250","145"},
            {"CON-07","Madrid","Zurich","Vuelo","Zurich Elite","320","170"},
            {"CON-08","Barcelona","Dublin","Vuelo","Dublin Green","245","165"},
            {"CON-09","Valencia","Estocolmo","Vuelo","Nordic Sky","310","210"}
    };

    private JLabel saldoLabel;
    private DefaultTableModel tableModel;
    private JTable tabla;

    public TripTravelGUI() {
        setTitle("TripTravel");
        setSize(850, 560);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(panelTop(), BorderLayout.NORTH);
        add(tablaPanel(), BorderLayout.CENTER);
        add(panelBottom(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel panelTop() {
        JPanel p = new JPanel();
        p.setBackground(new Color(30, 80, 160));

        JTextField saldoField = new JTextField(8);
        JButton btn = new JButton("Establecer");

        saldoLabel = new JLabel("Saldo: 0.00 EUR");
        saldoLabel.setForeground(Color.YELLOW);

        btn.addActionListener(e -> {
            try {
                saldo = Double.parseDouble(saldoField.getText());
                actualizarSaldo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Numero invalido");
            }
        });

        p.add(new JLabel("Presupuesto:"));
        p.add(saldoField);
        p.add(btn);
        p.add(saldoLabel);

        return p;
    }

    private JScrollPane tablaPanel() {
        String[] cols = {"ID", "Origen", "Destino", "Tipo", "Hotel", "Precio"};
        tableModel = new DefaultTableModel(cols, 0);
        cargar();

        tabla = new JTable(tableModel);
        return new JScrollPane(tabla);
    }

    private void cargar() {
        tableModel.setRowCount(0);
        for (String[] v : VIAJES) {
            double total = Double.parseDouble(v[5]) + Double.parseDouble(v[6]);
            tableModel.addRow(new Object[]{
                    v[0], v[1], v[2], v[3], v[4], total
            });
        }
    }

    private JPanel panelBottom() {
        JPanel p = new JPanel();

        JTextField buscar = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar");
        JButton btnTodos = new JButton("Todos");

        JTextField fecha = new JTextField(10);
        JButton reservar = new JButton("Reservar");

        // BUSCAR por origen, destino, tipo y hotel
        btnBuscar.addActionListener(e -> {
            String t = buscar.getText().toLowerCase();
            tableModel.setRowCount(0);

            for (String[] v : VIAJES) {
                if (v[1].toLowerCase().contains(t) ||
                        v[2].toLowerCase().contains(t) ||
                        v[3].toLowerCase().contains(t) ||
                        v[4].toLowerCase().contains(t)) {

                    double total = Double.parseDouble(v[5]) + Double.parseDouble(v[6]);
                    tableModel.addRow(new Object[]{
                            v[0], v[1], v[2], v[3], v[4], total
                    });
                }
            }
        });

        btnTodos.addActionListener(e -> cargar());

        // RESERVA + VALIDACIÓN + TICKET
        reservar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un viaje");
                return;
            }

            String f = fecha.getText().trim();

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

            LocalDate fechaViaje;

            try {
                fechaViaje = LocalDate.parse(f, fmt);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Fecha invalida");
                return;
            }

            if (!fechaViaje.isAfter(LocalDate.now())) {
                JOptionPane.showMessageDialog(this, "La fecha debe ser futura");
                return;
            }

            int modelRow = tabla.convertRowIndexToModel(fila);

            String id      = tableModel.getValueAt(modelRow, 0).toString();
            String origen  = tableModel.getValueAt(modelRow, 1).toString();
            String destino = tableModel.getValueAt(modelRow, 2).toString();
            String tipo    = tableModel.getValueAt(modelRow, 3).toString();
            String hotel   = tableModel.getValueAt(modelRow, 4).toString();
            double precio  = Double.parseDouble(tableModel.getValueAt(modelRow, 5).toString());

            if (saldo < precio) {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente");
                return;
            }

            saldo -= precio;
            actualizarSaldo();

            // Número de reserva único basado en timestamp
            String numReserva = "TT-" + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

            String ticket =
                    "=========================================\n" +
                            "           TICKET DE VIAJE               \n" +
                            "=========================================\n" +
                            "N. RESERVA:     " + numReserva + "\n" +
                            "-----------------------------------------\n" +
                            "ID VIAJE:       " + id + "\n" +
                            "ORIGEN:         " + origen + "\n" +
                            "DESTINO:        " + destino + "\n" +
                            "TIPO:           " + tipo + "\n" +
                            "FECHA:          " + f + "\n" +
                            "-----------------------------------------\n" +
                            "HOTEL:          " + hotel + "\n" +
                            "-----------------------------------------\n" +
                            "TOTAL:          " + String.format("%.2f", precio) + " EUR\n" +
                            "SALDO RESTANTE: " + String.format("%.2f", saldo) + " EUR\n" +
                            "=========================================\n" +
                            "       Gracias por viajar con TripTravel  \n" +
                            "=========================================";

            // Panel con vista previa + botón descargar
            mostrarTicketConDescarga(ticket, numReserva);
        });

        p.add(new JLabel("Buscar origen/destino:"));
        p.add(buscar);
        p.add(btnBuscar);
        p.add(btnTodos);

        p.add(new JLabel("Fecha (dd/MM/yyyy):"));
        p.add(fecha);
        p.add(reservar);

        return p;
    }

    /**
     * Muestra el ticket en un diálogo con botones "Descargar .txt" y "Cerrar".
     */
    private void mostrarTicketConDescarga(String ticket, String numReserva) {
        JDialog dialog = new JDialog(this, "Confirmación de Reserva", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(480, 420);
        dialog.setLocationRelativeTo(this);

        // Área de texto con el ticket
        JTextArea area = new JTextArea(ticket);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        area.setEditable(false);
        area.setMargin(new Insets(10, 14, 10, 14));
        dialog.add(new JScrollPane(area), BorderLayout.CENTER);

        // Panel de botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));

        JButton btnDescargarTxt = new JButton("⬇  Descargar .txt");
        btnDescargarTxt.setBackground(new Color(30, 130, 60));
        btnDescargarTxt.setForeground(Color.WHITE);
        btnDescargarTxt.setFocusPainted(false);

        JButton btnCerrar = new JButton("Cerrar");

        btnDescargarTxt.addActionListener(ev -> descargarTxt(ticket, numReserva, dialog));
        btnCerrar.addActionListener(ev -> dialog.dispose());

        botones.add(btnDescargarTxt);
        botones.add(btnCerrar);
        dialog.add(botones, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * Abre un JFileChooser y guarda el ticket como archivo .txt.
     */
    private void descargarTxt(String ticket, String numReserva, JDialog parent) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Guardar ticket");
        fc.setSelectedFile(new File("ticket_" + numReserva + ".txt"));

        // Filtro para archivos .txt
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Archivos de texto (*.txt)", "txt"));

        int resultado = fc.showSaveDialog(parent);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fc.getSelectedFile();

            // Añadir extensión .txt si el usuario no la escribió
            if (!archivo.getName().toLowerCase().endsWith(".txt")) {
                archivo = new File(archivo.getAbsolutePath() + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(archivo), "UTF-8"))) {
                writer.write(ticket);
                JOptionPane.showMessageDialog(parent,
                        "Ticket guardado en:\n" + archivo.getAbsolutePath(),
                        "Descarga completada",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent,
                        "Error al guardar el archivo:\n" + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarSaldo() {
        saldoLabel.setText(String.format("Saldo: %.2f EUR", saldo));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TripTravelGUI::new);
    }
}