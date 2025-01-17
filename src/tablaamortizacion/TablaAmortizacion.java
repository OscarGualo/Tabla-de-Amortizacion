/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tablaamortizacion;

/**
 *
 * @author oscar
 */
public class TablaAmortizacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String monto = "10000";
       String interes = "18";
       String mes = "12";
       
       double interes1 = Math.pow((1+0.015), 12);
        System.out.println(interes1);
    }
    
}
