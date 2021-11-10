import java.util.Random;
import java.util.Scanner;
import java.lang.NumberFormatException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;

public class Solterona {

    Baraja baraja;
    int sizeBaraja;
    List<Jugador> jugadores;
    Jugador jugadorAnterior;
    Jugador jugadorActual;
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    int numTurno;

    /**
     * Constructor para el juego de Solterona.
     * @param jugadores - Lista de los que van a jugar.
     * @param baraja - Baraja con la que se jugará.
     */

    public Solterona(List<Jugador> jugadores, Baraja baraja) {
        this.baraja = baraja;
        this.jugadores = jugadores;
        modoDeJuego();
        repartirBaraja();
        numTurno = random.nextInt(jugadores.size() - 1);
        jugadorActual = jugadores.get(numTurno);
        if(numTurno == 0){
            jugadorAnterior = jugadores.get((jugadores.size()-1));
        }else{
            jugadorAnterior = jugadores.get(numTurno-1);
        }
    }

    /**
     * Método para repartir la baraja revuelta con todos los jugadores.
     */
    public void repartirBaraja() {
        int jugadores = this.jugadores.size();
        int longitudBaraja = baraja.size();
        int jugador = random.nextInt(jugadores - 1);
        for (int i = 0; i < longitudBaraja; i++) {
            if (jugador == jugadores) {
                jugador = 0;
            }
            this.jugadores.get(jugador).agregarCarta(baraja.tomarCarta());
            jugador++;
        }
    }

    /**
     * Método para revolver y modificar el tamaño de la baraja.
     */
    public void modoDeJuego() {
        if (jugadores.size() > 1 && jugadores.size() < 4) {
            for (int i = 0; i < 22; i++) {
                baraja.remove(28);
            }
        } else if (jugadores.size() == 4) {
            for (int i = 0; i < 12; i++) {
                baraja.remove(28);
            }
        }
        baraja.remove(4);
        sizeBaraja = baraja.size();
        baraja.revolver();
    }

    /**
     * Método para la inicialización del juego.
     */
    public void juego() {
        while(!perdedor()){
            if(numTurno == jugadores.size()){
                numTurno = 0;
            }
            jugadorAnterior = jugadorActual;
            jugadorActual = jugadores.get(numTurno);
            if(jugadorAnterior.getMano().size() != 0){
                System.out.println("Es el turno de " + jugadorActual.getNombre() + " " + numTurno);
                robarCarta();
                if(jugadorAnterior.getMano().size()==0){
                    if(numTurno==0){
                        System.out.println("El jugador " + jugadorAnterior.getNombre() + " ya no tiene cartas y sale de juego");
                        jugadores.remove(jugadores.size()-1);
                        System.out.println(numTurno);
                        numTurno = jugadores.size()-1;
                    }else{
                        System.out.println("El jugador " + jugadorAnterior.getNombre() + " ya no tiene cartas y sale de juego");
                        jugadores.remove(numTurno-1);
                        System.out.println(numTurno);
                        numTurno--;
                    }
                }
                turno();
            }
            numTurno++;
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){}
        }
        for(int i = 0 ; i < jugadores.size(); i++){
            if(jugadores.get(i).getMano().size() == 1){
                System.out.println("Perdió " + jugadores.get(i).getNombre() + " , se quedo con la solterona jijijija");
                System.out.println("La solterona fue: " + jugadores.get(i).getManoToString());
            }
        }
    }

    /**
     * Método para saber si ya hay algún perdedor.
     * @return true - Si solo queda la solterona.
     * false - Si aún hay más pares en juego.
     */
    public boolean perdedor() {
        return sizeBaraja == 1;
    }

    /**
     * Método que indicará de quien es turno y le muestra sus opciones de juego, en caso de ser CPU automatiza el proceso.
     * @throws NumberFormatException - Si el usuario no ingresa números.
     * @throws IndexOutOfBoundsException - Si el usuario ingresa números que no están en lo parámetros.
     * @throws NullPointerException - Si el usuario ingresa números que no están en lo parámetros.
     */
    public void turno() {
        if (jugadorActual.isCPU()) {//Si es la máquina
            System.out.println((jugadorActual.printDeck()));
            int pares = paresMano();
            if(pares == 1){
                System.out.println(jugadorActual.getNombre() + " tienes " + pares + " par");
            }else if(pares !=0){
                System.out.println(jugadorActual.getNombre() + " tienes " + pares + " pares");
            }
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){}
            while (pares != 0) {
                for(int i=0; i<jugadorActual.getMano().size(); i++){
                    for(int j=0; j<jugadorActual.getMano().size(); j++){
                        if(i != j){
                            if (jugadorActual.getMano().get(i).getValor() == jugadorActual.getMano().get(j).getValor()) {
                                if (i < j) {
                                    jugadorActual.getMano().remove(i);
                                    jugadorActual.getMano().remove(j - 1);
                                } else {
                                    jugadorActual.getMano().remove(i);
                                    jugadorActual.getMano().remove(j);
                                }
                                System.out.println(jugadorActual.printDeck());
                                try{
                                    Thread.sleep(1000);
                                }catch (InterruptedException e){}
                                pares--;
                                sizeBaraja -= 2;
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println("Ya no tienes mas pares :c" + "\n");
        } else {//Si es una persona
            int i,j;
            boolean repe = true;
            String tupla = "";
            System.out.println((jugadorActual.printDeck()));
            int pares = paresMano();
            if(pares == 1){
                System.out.println(jugadorActual.getNombre() + " tienes " + pares + " par");
            }else if(pares !=0){
                System.out.println(jugadorActual.getNombre() + " tienes " + pares + " pares");
            }
            while (repe) {
                while (pares != 0) {
                    try{
                        System.out.println("Ingresa los indices de las parejas Ej: 2,3");
                        tupla = scanner.nextLine().trim();
                        i = Integer.parseInt(tupla.split(",")[0]);
                        j = Integer.parseInt(tupla.split(",")[1]);
                        if(i!=j){
                            if(jugadorActual.getMano().get(i).getValor() == jugadorActual.getMano().get(j).getValor()){
                                if(i<j){
                                    jugadorActual.getMano().remove(i);
                                    jugadorActual.getMano().remove(j-1);
                                }else{
                                    jugadorActual.getMano().remove(i);
                                    jugadorActual.getMano().remove(j);
                                }
                                System.out.println(jugadorActual.printDeck());
                                pares--;
                                sizeBaraja-=2;
                                if(pares == 1){
                                    System.out.println(jugadorActual.getNombre() + " tienes " + pares + " par");
                                }else{
                                    System.out.println(jugadorActual.getNombre() + " tienes " + pares + " pares");
                                }
                            }else{
                                System.out.println("\tTus cartas no son pares :c\n");
                            }
                            repe = false;
                        }else{
                            System.out.println("\t No puedes ingresar las mismas cartas :c\n");
                            repe = true;
                        }
                    }catch(NumberFormatException e){
                        System.out.println("\t Intentalo de nuevo. Sigue el ejemplo :)\n");
                        repe = true;
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("\t Ingresa indices dentro de los rangos :)\n");
                        repe = true;
                    }catch(NullPointerException e){
                        System.out.println("\t Ingresa indices dentro de los rangos :)\n");
                        repe = true;
                    } 
                }
                System.out.println("Ya no tienes mas pares :c\n");
                repe = false;
            }
        }
    }


    public int paresMano(){
        List<Carta> manoAux = jugadorActual.getMano();
        List<String> valoresMano = new List<>();

        for(int i = 0; i < manoAux.size(); i++){//Buscamos los valores en nuestra mano
            String valor = manoAux.get(i).getValor()+"";
            if(!valoresMano.contains(valor)){
                valoresMano.add(valoresMano.size(), valor);
            }
        }
        int repeticiones = 0;
        int pares = 0;
        for(int i = 0; i < valoresMano.size(); i++){
            repeticiones = 0;
            for(int j = 0; j < manoAux.size(); j++){
                if(valoresMano.get(i).equals(manoAux.get(j).getValor()+"")){
                    repeticiones++;
                }
            }
            pares += (repeticiones/2);
        }

        return pares;
    }

    public void robarCarta(){
        int jugador = numTurno-1;
        if(jugador < 0){
            jugador = jugadores.size()-1;
        }
        if(jugadores.get(jugador).getMano().size() != 0){
            int cartaRandom = random.nextInt(jugadores.get(jugador).getMano().size());
            Carta carta = jugadores.get(jugador).getMano().get(cartaRandom);
            jugadores.get(jugador).getMano().remove(cartaRandom);
            jugadores.get(numTurno).agregarCarta(carta);
        }
    }


}
