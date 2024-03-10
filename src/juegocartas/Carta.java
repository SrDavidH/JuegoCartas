package juegocartas;



import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Carta {
    
    private int indice;
    
    //metodo constructo
    public Carta(Random r){
        //generr el indice de la carta de manera aleatoria
        
        indice = r.nextInt(52)+1;
        
    }
    
    public void mostrar(JPanel pnl, int x, int y){
        
            //Obtener el nombre del archivo de la carta
        String nombreArchivo = "/Imagenes/CARTA" + String.valueOf(indice)+".jpg";
        
        //Cargar la imagen
        ImageIcon imagen=new ImageIcon(getClass().getResource(nombreArchivo));
        
        
        //Instanciar el objeto JLABEL que mostrara la carta
        JLabel lbl=new JLabel(imagen);
        
        
        //definir las coordenadas y dimension de la imagen
        lbl.setBounds(x, y, imagen.getIconWidth(), imagen.getIconHeight());
  
      
        //Mostrar la carta en  el objeto PANEL de despliegue
        pnl.add(lbl);
        
    }
    public Pinta obtenerPinta(){
        if(indice<=13){
            return Pinta.TREBOL;
        }
        else if(indice<=26){
            return Pinta.PICA;
            
        }
        else if(indice<=39){
            return Pinta.CORAZON;
        }
        else{
            return Pinta.DIAMANTE;
        }
    } 
    public NombreCarta ObtenerNombre(){
       int residuo =indice%13;
    if (residuo ==0){
    residuo=13;
        
    }
return NombreCarta.values()[residuo - 1];
}
public int ObtenerValor(){
return 0;
}
}