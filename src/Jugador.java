 /**
 * Clase que extiende a la clase Persona para Jugador
 * @author Andrea Alvarado Camacho
 * @author Alfonso Mondragon Segoviano
 * @version 1.2
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
       historial = new List<>();
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
      * Metodo que regresa la mano del jugador
      * @return mano del jugador
      */
    public List<Carta> getMano() {
         return mano;
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
     * Método que genera números aleatorios entre 0 y max.
     */
    private int random(int max) {
        return (int) Math.round(Math.random() * max + 0.5);
    }

    public void intercambio(){
      if(mano.size() > 1){
         for(int i = 0; i < mano.size(); i++){
            int numRan = random(mano.size()-1);
            Carta temporal = mano.remove(numRan);
            mano.add(0,temporal);
        }
      }
    }

    public void swap(int i, int j){
      if(mano.size()>1){
         if(i<j){
            Carta temp = mano.remove(i);
            mano.add(i,mano.remove(j-1));
            mano.add(j,temp);
         }else{
            Carta temp = mano.remove(j);
            mano.add(j,mano.remove(i-1));
            mano.add(i,temp);
         }
      }
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