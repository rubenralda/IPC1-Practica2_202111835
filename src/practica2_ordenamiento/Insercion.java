/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica2_ordenamiento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ruben
 */
public class Insercion extends Thread {

    private JLabel labelpasos;
    private String[] ordenarx;
    private int[] ordenary;
    private JPanel jpanel;
    private String[] encabezado;
    private String titulo;
    private JLabel crono;
    private int tipo;

    public Insercion(JLabel labelpasos, String[] ordenadox, int[] ordenadoy, JPanel jpanel, String[] encabezado, String titulo, JLabel crono, int tipo) {
        this.labelpasos = labelpasos;
        this.ordenarx = ordenadox;
        this.ordenary = ordenadoy;
        this.jpanel = jpanel;
        this.encabezado = encabezado;
        this.titulo = titulo;
        this.crono = crono;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        int insercion, pasos = 0;
        String insercionx;
        Cronometro reloj = new Cronometro(crono);
        reloj.start();
        switch (tipo) {
            case 0: //ascendente
                for (int siguiente = 1; siguiente < ordenary.length; siguiente++) {
                    insercion = ordenary[siguiente];
                    insercionx = ordenarx[siguiente];
                    int moverElemento = siguiente;
                    while (moverElemento > 0 && ordenary[moverElemento - 1] > insercion) {
                        ordenary[moverElemento] = ordenary[moverElemento - 1];
                        ordenarx[moverElemento] = ordenarx[moverElemento - 1];
                        moverElemento--;
                        pasos++;
                        labelpasos.setText(String.valueOf(pasos));
                        try {
                            sleep(100);
                        } catch (Exception e) {
                            System.out.println("Error: " + e);
                        }
                        mostrar();
                    }
                    ordenary[moverElemento] = insercion;
                    ordenarx[moverElemento] = insercionx;
                    pasos++;
                    labelpasos.setText(String.valueOf(pasos));
                    try {
                        sleep(100);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                    mostrar();
                }
                mostrar();
                break;
            case 1: //descendente
                for (int siguiente = 1; siguiente < ordenary.length; siguiente++) {
                    insercion = ordenary[siguiente];
                    insercionx = ordenarx[siguiente];
                    int moverElemento = siguiente;
                    while (moverElemento > 0 && ordenary[moverElemento - 1] < insercion) {
                        ordenary[moverElemento] = ordenary[moverElemento - 1];
                        ordenarx[moverElemento] = ordenarx[moverElemento - 1];
                        moverElemento--;
                        pasos++;
                        labelpasos.setText(String.valueOf(pasos));
                        try {
                            sleep(100);
                        } catch (Exception e) {
                            System.out.println("Error: " + e);
                        }
                        mostrar();
                    }
                    ordenary[moverElemento] = insercion;
                    ordenarx[moverElemento] = insercionx;
                    pasos++;
                    labelpasos.setText(String.valueOf(pasos));
                    try {
                        sleep(100);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                    mostrar();
                }
                mostrar();
                break;
            default:
                throw new AssertionError();
        }
        reloj.setTerminar(false);
    }

    private void mostrar() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //añadir los valores a la grafica
        for (int j = 0; j < ordenarx.length; j++) {
            dataset.setValue(ordenary[j], "", ordenarx[j]);
        }
        //graficar
        JFreeChart barChart = ChartFactory.createBarChart(
                titulo,
                encabezado[0],
                encabezado[1],
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
        ChartPanel panel = new ChartPanel(barChart);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setPreferredSize(new Dimension(400, 400));
        panel.setBackground(Color.white);
        jpanel.removeAll();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(panel, BorderLayout.NORTH);
    }
}
