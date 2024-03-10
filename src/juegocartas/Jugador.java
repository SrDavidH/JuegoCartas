package juegocartas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author dhzea
 */
public class Jugador {
    
    private final int TOTAL_CARTAS=10;
    private final int MARGEN_SUPERIOR=10;
    private final int MARGEN_IZQUIERDA =10;
    private final int DISTANCIA =50;
    
    private final Carta[] cartas=new Carta[TOTAL_CARTAS];
    private final Random r;
    
    public Jugador (){
        r = new Random();
    }
    public void repartir(){
        for(int i=0;i<TOTAL_CARTAS;i++){
            cartas[i]=new Carta(r);
        }
        
    }
    public void mostrar(JPanel pnl){
        pnl.removeAll();
        //for (int = 0; 1< cartas.length; i++)
        
        int x=MARGEN_IZQUIERDA;
        
        for (Carta c:cartas) {
            //cartas[i].mostrar(pnl,10,5);
            c.mostrar(pnl,x,MARGEN_SUPERIOR);
            x += DISTANCIA;
        }
        pnl.repaint();
    }
    public String getGrupos() {
        String mensaje ="No se encontraron grupos";
        
        //verificar que haya cartas
        if(cartas[0] !=null){
        //iniciar los contadores 
        int[] contadores=new int[NombreCarta.values().length];
        
        //recorrer las cartas para contarlasa de acuerdo de su nombre
        for (Carta c :cartas){
            contadores[c.ObtenerNombre().ordinal()]++;
        }
        
        //contar cuantos grupos se hallaron
        int totalGrupos=0;
        for (int i=0;i<contadores.length;i++){
            if(contadores[i]>1){
                totalGrupos++;
            }
        }
        
        if (totalGrupos > 0) {
                mensaje = "Los grupos encontrador fueron:\n";
                for (int i = 0; i < contadores.length; i++) {
                    if (contadores[i] > 1) {
                        mensaje += Grupo.values()[ contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
                
            }
         }
       }
         } else {
            mensaje = "No se han repartido cartas";
        }       
        return mensaje;
    
    }
    
    public String obtenerEscalera() {
    StringBuilder mensaje = new StringBuilder();

    // Recorremos las pintas
    for (Pinta pinta : Pinta.values()) {
        // Creamos un arreglo para almacenar las cartas de la misma pinta
        List<Carta> cartasDeLaMismaPinta = new ArrayList<>();
        
        // Agregamos las cartas de la misma pinta al arreglo
        for (Carta carta : cartas) {
            if (carta.obtenerPinta() == pinta) {
                cartasDeLaMismaPinta.add(carta);
            }
        }

        // Ordenamos las cartas por su valor
        Collections.sort(cartasDeLaMismaPinta, Comparator.comparingInt(carta -> carta.ObtenerNombre().ordinal()));

        // Verificamos si hay secuencias de la misma pinta
        if (cartasDeLaMismaPinta.size() >= 3) { // Verificamos secuencias de al menos 3 cartas
            for (int i = 0; i < cartasDeLaMismaPinta.size() - 2; i++) {
                if (cartasDeLaMismaPinta.get(i).ObtenerNombre().ordinal() == cartasDeLaMismaPinta.get(i + 1).ObtenerNombre().ordinal() - 1 &&
                    cartasDeLaMismaPinta.get(i + 1).ObtenerNombre().ordinal() == cartasDeLaMismaPinta.get(i + 2).ObtenerNombre().ordinal() - 1) {
                    mensaje.append("Terna de ").append(cartasDeLaMismaPinta.get(i).ObtenerNombre());
                    mensaje.append(", ").append(cartasDeLaMismaPinta.get(i + 1).ObtenerNombre());
                    mensaje.append(", ").append(cartasDeLaMismaPinta.get(i + 2).ObtenerNombre());
                    mensaje.append(" de ").append(pinta).append("\n");
                }
            }
        }
    }

    if (mensaje.length() == 0) {
        mensaje.append("No se encontraron figuras en escalera de la misma pinta.");
    }

    return mensaje.toString();
}

    
     public int calcularPuntaje() {
        int puntaje = 0;
        
        for (Carta c : cartas) {
            if (c.ObtenerNombre() == NombreCarta.AS || 
                c.ObtenerNombre() == NombreCarta.JACK ||
                c.ObtenerNombre() == NombreCarta.QUEEN ||
                c.ObtenerNombre() == NombreCarta.KING) {
                puntaje += 10;
            } else {
                puntaje += c.ObtenerValor();
            }
        }
        
        return puntaje;
}
}
