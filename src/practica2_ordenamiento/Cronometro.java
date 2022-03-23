/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica2_ordenamiento;

import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import org.jfree.chart.plot.ThermometerPlot;

/**
 *
 * @author ruben
 */
public class Cronometro extends Thread {

    int minutos = 0;
    int segundos = 0;
    private boolean terminar;
    JLabel inicio;

    Cronometro(JLabel inicio) {
        this.inicio = inicio;
        terminar=true;
    }

    public void setTerminar(boolean terminar) {
        this.terminar = terminar;
    }

    public void reloj() {
       while (terminar==true) {
            if (segundos == 30) {
                segundos = 0;
                minutos++;
            }
            inicio.setText(minutos + ":" + segundos);
            segundos++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    // creacion del hilo
    @Override
    public void run() {
        reloj();
    }
}
