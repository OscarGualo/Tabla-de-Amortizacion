package tablaamortizacion;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class TablaAmortizacion {

    private JFrame frame;
    private JTable table;
    private JTextField txtMonto;
    private JTextField txtTasaInteres;
    private JTextField txtPlazo;
    private JTextField txtPrimaSeguro;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TablaAmortizacion window = new TablaAmortizacion();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public TablaAmortizacion() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Tabla de amortización con método Francés");
        frame.setBounds(100, 100, 700, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblMonto = new JLabel("Monto del préstamo:");
        lblMonto.setBounds(10, 10, 150, 25);
        frame.getContentPane().add(lblMonto);

        txtMonto = new JTextField("10000");
        txtMonto.setBounds(160, 10, 120, 25);
        frame.getContentPane().add(txtMonto);
        txtMonto.setColumns(10);

        JLabel lblTasaInteres = new JLabel("Tasa de interés anual (%):");
        lblTasaInteres.setBounds(300, 10, 150, 25);
        frame.getContentPane().add(lblTasaInteres);

        txtTasaInteres = new JTextField("18");
        txtTasaInteres.setBounds(468, 10, 120, 25);
        frame.getContentPane().add(txtTasaInteres);
        txtTasaInteres.setColumns(10);

        JLabel lblPlazo = new JLabel("Plazo (meses):");
        lblPlazo.setBounds(10, 46, 150, 25);
        frame.getContentPane().add(lblPlazo);

        txtPlazo = new JTextField("12");
        txtPlazo.setBounds(160, 46, 120, 25);
        frame.getContentPane().add(txtPlazo);
        txtPlazo.setColumns(10);

        JLabel lblPrimaSeguro = new JLabel("Prima anual seguro (%):");
        lblPrimaSeguro.setBounds(300, 46, 150, 25);
        frame.getContentPane().add(lblPrimaSeguro);

        txtPrimaSeguro = new JTextField("0.72");
        txtPrimaSeguro.setBounds(468, 46, 120, 25);
        frame.getContentPane().add(txtPrimaSeguro);
        txtPrimaSeguro.setColumns(10);

        JButton btnGenerar = new JButton("Generar Tabla");
        btnGenerar.setBounds(374, 82, 150, 25);
        frame.getContentPane().add(btnGenerar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 118, 660, 282);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        
        JLabel lblMetodoDeAmortizacion = new JLabel("Metodo de amortizacion:");
        lblMetodoDeAmortizacion.setBounds(10, 82, 150, 25);
        frame.getContentPane().add(lblMetodoDeAmortizacion);
        
        String opciones[] = {"Frances"};
        JComboBox comboBox = new JComboBox(opciones);
        comboBox.setBounds(160, 82, 120, 22);
        frame.getContentPane().add(comboBox);

        btnGenerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double monto = Double.parseDouble(txtMonto.getText());
                    double tasaInteres = Double.parseDouble(txtTasaInteres.getText()) / 100;
                    int plazo = Integer.parseInt(txtPlazo.getText());
                    double primaAnualSeguro = Double.parseDouble(txtPrimaSeguro.getText()) / 100;

                    // Calcular cuota fija mensual (método francés)
                    double tasaMensual = tasaInteres / 12;
                    double cuotaFija = monto * tasaMensual / (1 - Math.pow(1 + tasaMensual, -plazo));

                    // Seguro fijo mensual
                    double seguroFijoMensual = (primaAnualSeguro / 12) * monto;
                    
                    // Modelo de la tabla
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("No. de cuota");
                    model.addColumn("Capital");
                    model.addColumn("Interes");
                    model.addColumn("Sald. Capital");
                    model.addColumn("Desgravamen");
                    model.addColumn("Tot. Cuota");

                    // Generar filas
                    double saldo = monto;
                    for (int i = 1; i <= plazo; i++) {
                        double interes = saldo * tasaMensual;
                        double abonoCapital = cuotaFija - interes;
                        double cuotaTotal = cuotaFija + seguroFijoMensual;
                        double saldoFinal = saldo - abonoCapital;

                        model.addRow(new Object[]{
                              
                        		//i, saldo, interes, abonoCapital,seguroFijoMensual,cuotaTotal
                        		
                        		i,
                                String.format("%.3f", saldo),
                                String.format("%.3f", interes),
                                String.format("%.3f", abonoCapital),
                                String.format("%.3f", seguroFijoMensual),
                                String.format("%.3f", cuotaTotal)
                             
                        });

                        saldo = saldoFinal;
                    }

                    table.setModel(model);
                } catch (NumberFormatException ex) {
                	JOptionPane.showMessageDialog(null, "Valores invalidos", "Error", 0);
                	
                }
            }
        });
    }
}
