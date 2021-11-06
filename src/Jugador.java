import java.util.Random;

 /**
 * Clase que extiende a la clase Persona para Jugador
 * @author Andrea Alvarado Camacho
 * @author Alfonso Mondragon Segoviano
 * @version 1.0
 */

public class Jugador {
    protected String nombre;
    protected List<Carta> mano;
    protected boolean persona;

    /**
     * Constructor que crea un objeto de tipo jugador
     *
     * @param nombre - string con el nombre del jugador
     * @param persona - boolean que dice si es persona o no
     */
    public Jugador(String nombre, boolean persona) {
       this.nombre = nombre;
       mano = new List<>();
       this.persona = persona;
    }

    /**
     * Método para obtener el nombre del jugador
     *
     * @return Cadena con el nombre del jugador
     */
    public String getNombre() {
       return this.nombre;
    }

    /**
     * Método para editar el nombre del jugador
     *
     * @param nomN - String con el nombre nuevo
     */
    public void editarNombre(String nomN) {
       this.nombre = nomN;
    }

    /**
     * Método para asignarle una mano al jugador
     *
     * @param mano - Lista de cartas con las que jugará el jugador
     */
    public void setMano(List<Carta> mano) {
       this.mano = mano;
    }

     /**
      * Metodo que regresa la mano del jugador
      * @return mano del jugador
      */

    public List<Carta> getMano() {
         return mano;
     }



     /**
     * Método para imprimir la mano del jugador
     *
     * @return La mano del jugador
     */
    public String getManoToString() {
       return mano.toString();
    }

    /**
     * Método para agregar una carta a la mano del jugador
     *
     * @param carta - Carta que se agregará a la mano
     */
    public void agregarCarta(Carta carta) {
       mano.add(mano.size(), carta);
    }

    /**
     * Método que dice si el jugador es una persona o la computadora
     *
     * @return Si es una maquina o una persona
     */
    public Boolean isCPU() {
       return !persona;
    }

    /**
     * Método para imprimir en consola el jugador y sus atributos
     *
     * @return Cadena con toda la información de jugador
     */
    public String toString() {
       return "\nNombre: " + this.nombre + "\nMano: " + this.getMano();
    }

    public String printDeck(){
       StringBuilder opc = new StringBuilder();
       for(int i = 0; i < mano.size(); i++){
          opc.append(i).append(": ").append(mano.get(i).toString()).append(" ");
       }
       return opc.toString();
    }
}