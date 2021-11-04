/**
 * Clase que crea una baraja de cartas
 * @author Andrea Alvarado Camacho
 * @version 1.2
 */
public class Baraja{

    protected List<Carta> lista;
    protected int contador = 0;

    /**
     * Constructor por omision que crea una lista con 52 cartas
     */
    public Baraja(){
        lista = new List();
        for(int i =1; i<=13;i++){
            lista.add(0,(new Carta(i,"diamante")));
            lista.add(0,(new Carta(i,"trebol")));
            lista.add(0,(new Carta(i,"picas")));
            lista.add(0,(new Carta(i,"corazon")));
        }
    }

    /**
     * Método para tomar carta
     * @return Carta -  nueva carta al azar
     */
    public Carta tomarCarta(){
        this.revolver();
        Carta nuevaCarta = lista.get(0);
        return nuevaCarta;
    }

    /* * Metodo que genera numeros aleatorios entre 0 y max. */
    private int random(int max) {
        return (int) Math.round(Math.random() * max + 0.5);
    }

    /**
     * Método para revolver las cartas de la baraja
     */
    public void revolver(){
        int size = lista.size();
        for(int k = 0; k < lista.size(); k++){
            int numRan = random(lista.size()-1);
            Carta temporal = lista.remove(numRan);
            System.out.println(temporal);
            //lista.add(0,temporal);
        }
        System.out.println(lista);
    }

    /**
     * Método para comparar dos cartas
     * @param a -- carta con la que se va a comparar
     * @param b -- carta con la que se va a comparar
     * @return boolean -- true si son iguales y false en otro caso.
     */
    public boolean equals (Carta a, Carta b){
    return (a.obtenerValor()==b.obtenerValor() || a.obtenerFigura()==b.obtenerFigura());
    }

    /**
     * Método para imprimir la baraja
     * @return String -- cadena que contiene la baraja.
     */
    public String toString(){
        /*String cadena = "";
        for (int m = 0; m<lista.size(); m++){
            cadena += "\n"+ lista.get(m);
        }*/
        return lista.toString();
    }
}

