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
public class Ordenamiento extends Thread{

    private JLabel labelpasos;
    private String[] ordenadox;
    private int[] ordenadoy;
    private JPanel jpanel;
    private String[] encabezado;
    private String titulo;
    private JLabel crono;

    public Ordenamiento(JLabel labelpasos, String[] ordenadox, int[] ordenadoy, JPanel jpanel, String[] encabezado, String titulo,JLabel crono) {
        this.labelpasos = labelpasos;
        this.ordenadox = ordenadox;
        this.ordenadoy = ordenadoy;
        this.jpanel = jpanel;
        this.encabezado = encabezado;
        this.titulo = titulo;
        this.crono=crono;
    }
    
    public void run(int[] ordenary, String[] ordenarx, int tipo) {
        int n, i, l = ordenary.length, temp,pasos=0;
        String temp2;
        Cronometro reloj= new Cronometro(crono);
        switch (tipo) {
            case 0://ascendente
                do {
                    n = 0;
                    for (i = 1; i < l; i++) {
                        if (ordenary[i - 1] > ordenary[i]) {
                            temp = ordenary[i - 1];
                            ordenary[i - 1] = ordenary[i];
                            ordenary[i] = temp;
                            temp2 = ordenarx[i - 1];
                            ordenarx[i - 1] = ordenarx[i];
                            ordenarx[i] = temp2;
                            n = i;
                            pasos++;
                            labelpasos.setText(String.valueOf(pasos));
                            mostrar(encabezado, titulo, ordenary, ordenarx);
                        }
                    }
                    l = n;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                } while (n != 0);
                break;
            case 1://descendente
                do {
                    n = 0;
                    for (i = 1; i < l; i++) {
                        if (ordenary[i - 1] < ordenary[i]) {
                            temp = ordenary[i - 1];
                            ordenary[i - 1] = ordenary[i];
                            ordenary[i] = temp;
                            temp2 = ordenarx[i - 1];
                            ordenarx[i - 1] = ordenarx[i];
                            ordenarx[i] = temp2;
                            n = i;
                            pasos++;
                            labelpasos.setText(String.valueOf(pasos));
                            mostrar(encabezado, titulo, ordenary, ordenarx);

                        }
                    }
                    l = n;
                   
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                } while (n != 0);
                break;
            default:
                throw new AssertionError();
        }
        this.ordenadox = ordenarx;
        this.ordenadoy = ordenary;
    }
    private void metodoinsercion(int[] ordenary, String[] ordenarx, int tipo) {
        int insercion,pasos=0;
        String insercionx;
        for (int siguiente = 1; siguiente < ordenary.length; siguiente++) {
            insercion = ordenary[siguiente];
            insercionx = ordenarx[siguiente];
            int moverElemento = siguiente;
            switch (tipo) {
                case 0: //ascendente
                    while (moverElemento > 0 && ordenary[moverElemento - 1] > insercion) {
                        ordenary[moverElemento] = ordenary[moverElemento - 1];
                        ordenarx[moverElemento] = ordenarx[moverElemento - 1];
                        moverElemento--;
                        pasos++;
                        labelpasos.setText(String.valueOf(pasos));

                    }
                    ordenary[moverElemento] = insercion;
                    ordenarx[moverElemento] = insercionx;
                    mostrar(encabezado, titulo, ordenary, ordenarx);
                    break;
                case 1: //descendente
                    while (moverElemento > 0 && ordenary[moverElemento - 1] < insercion) {
                        ordenary[moverElemento] = ordenary[moverElemento - 1];
                        ordenarx[moverElemento] = ordenarx[moverElemento - 1];
                        moverElemento--;
                        pasos++;
                        labelpasos.setText(String.valueOf(pasos));

                    }
                    ordenary[moverElemento] = insercion;
                    ordenarx[moverElemento] = insercionx;
                    mostrar(encabezado, titulo, ordenary, ordenarx);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        this.ordenadox = ordenarx;
        this.ordenadoy = ordenary;
    }

    private void mostrar(String[] encabezado1, String titulo, int[] tempoy, String[] tempox) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //añadir los valores a la grafica
        for (int j = 0; j < tempox.length; j++) {
            dataset.setValue(tempoy[j], "", tempox[j]);
        }
        //graficar
        JFreeChart barChart = ChartFactory.createBarChart(
                titulo,
                encabezado1[0],
                encabezado1[1],
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
