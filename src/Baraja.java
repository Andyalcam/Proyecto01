/**
 * Clase que crea una baraja de cartas
 * @author Andrea Alvarado Camacho
 * @author Alfonso Mondragón Segoviano
 * @version 1.2
 */
public class Baraja{

    protected List<Carta> lista;

    /**
     * Constructor por omisión que crea una lista con 52 cartas
     */
    public Baraja(){
        lista = new List();
        for(int i =1; i<=13;i++){
            lista.add(0,(new Carta(i,"diamante")));
            lista.add(0,(new Carta(i,"trébol")));
            lista.add(0,(new Carta(i,"picas")));
            lista.add(0,(new Carta(i,"corazon")));
        }
    }

    /**
     * Método para tomar carta
     * @return Carta - nueva carta al azar
     */
    public Carta tomarCarta(){
        Carta nuevaCarta = lista.get(0);
        lista.remove(0);
        return nuevaCarta;
    }

    /**
     * Método que genera números aleatorios entre 0 y max.
     */
    private int random(int max) {
        return (int) Math.round(Math.random() * max + 0.5);
    }

    /**
     * Método para revolver las cartas de la baraja
     */
    public void revolver(){
        for(int i = 0; i < lista.size(); i++){
            int numRan = random(lista.size()-1);
            Carta temporal = lista.remove(numRan);
            lista.add(0,temporal);
        }
    }

    /**
     * Método para obtener el tamaño de la baraja
     * @return el tamaño de la baraja
     */
    public int size(){
        return lista.size();
    }

    /**
     * Método para quitar una carta de la baraja
     * @param i - Indice de la carta que quitaremos
     */
    public void remove(int i){
        lista.remove(i);
    }

    /**
     * Método para imprimir la baraja
     * @return String -- cadena que contiene la baraja.
     */
    public String toString() {
        return lista.toString();
    }
}

