import java.util.Random;
/**
* Clase que extiende a la clase Persona para Jugador
* @author Andrea Alvarado Camacho
* @version 1.0
*/

public class Jugador{
	protected String nombre;
   protected List<Baraja> mano; 
   Random random = new Random();

   /**
   * Constructor que crea un objeto de tipo jugador 
   * @param nom - string con el nombre del jugador
   * @param mano - lista con las cartas del jugador
   */
   public Jugador(String nombre, List<Baraja> mano) {
      this.nombre = nombre;
      this.mano = mano;
   }

   /**
   * Metodo para obtener la nombre del jugador
   * @return Cadena con el nombre del jugador
   */
   public String obtenerNombre() {
      return this.nombre;
   }

   /**
   * Metodo para editar el nombre del jugador
   * @param nomN - String con el nombre nuevo
   */
   public void editarNombre(String nomN){
      this.nombre = nomN;
   }

   /**
   * Metodo para imprimir en consola el jugador y sus atributos
   * @return Cadena con toda la informacion de jugador
   */
   public String toString() {
      return "\nNombre: " +this.nombre + "\nMano: " /*+ this.obtenerMano()*/;
   }
}