import java.util.Random;
import java.util.Scanner;
import java.lang.NumberFormatException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;
import java.util.InputMismatchException;
/**
 * Clase que simula el juego solterona
 * @author Andrea Alvarado Camacho
 * @author Alfonso Mondragón Segoviano
 * @version 1.0
 */
public class Solterona {

    // Colores de letra
    String red="\033[31m"; 
    String green="\033[32m"; 
    String yellow="\033[33m";

    // Reset
    String reset="\u001B[0m";

    Baraja baraja;
    int sizeBaraja;
    List<Jugador> jugadores;
    Jugador jugadorAnterior;
    Jugador jugadorActual;
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
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
        int ind;
        boolean conti;
        while(!perdedor()){
            if(numTurno == jugadores.size()){
                numTurno = 0;
            }
            jugadorAnterior = jugadorActual;
            jugadorActual = jugadores.get(numTurno);
            if(jugadorAnterior.getMano().size() != 0){
                System.out.println(green+"Es el turno de " + jugadorActual.getNombre()+reset);
                if(jugadorActual.isCPU()){
                    int cartaRandom = random.nextInt(jugadorAnterior.getMano().size());
                    robarCarta(cartaRandom);
                }else{
                    if(jugadorAnterior.getMano().size()-1==0){
                        System.out.println("Solo puedes robar la carta 0 :3");
                    }else{
                        System.out.println("¿Qué carta deseas robar?\tIngresa un número entre 0 y " + (jugadorAnterior.getMano().size()-1));
                    }
                    conti = true;
                    while (conti) {
                        try{
                            ind = scanner.nextInt();
                            robarCarta(ind);
                            conti = false;
                        }catch(InputMismatchException e){
                            System.out.println(yellow+"\t Debes ingresar un número :)"+reset);
                            scanner.next();
                        }catch(IndexOutOfBoundsException e){
                            System.out.println(yellow+"\t Ingresa indices dentro de los rangos :)"+reset);
                        }catch(NullPointerException e){
                            System.out.println(yellow+"\t Ingresa indices dentro de los rangos :)"+reset);
                        } 
                    }
                }
                
                if(jugadorAnterior.getMano().size()==0){
                    if(numTurno==0){
                        System.out.println(red+"\t\tEl jugador " + jugadorAnterior.getNombre() + " ya no tiene cartas y sale de juego\n"+reset);
                        jugadores.remove(jugadores.size()-1);
                        numTurno = jugadores.size()-1;
                    }else{
                        System.out.println(red+"\t\tEl jugador " + jugadorAnterior.getNombre() + " ya no tiene cartas y sale de juego\n"+reset);
                        jugadores.remove(numTurno-1);
                        numTurno--;
                    }
                }
                turno();
            }
            clear();
            numTurno++;
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){}
        }
        for(int i = 0 ; i < jugadores.size(); i++){
            if(jugadores.get(i).getMano().size() == 1){
                System.out.println("Perdió " + jugadores.get(i).getNombre() + " , se quedo con la solterona jijijija");
                System.out.println("La solterona fue: " + jugadores.get(i).getMano());
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
            jugadorActual.intercambio();
            System.out.println("Ya no tienes mas pares :c" + "\n");
        } else {//Si es una persona
            int a,b;
            boolean repe = true;
            String tupla = "";
            String inter;
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
            repe = true;
            boolean repe2;
            while(repe){
                if(jugadorActual.getMano().size()<2){
                    break;
                }
                System.out.println("¿Deseas intercambiar tus cartas?\tSi/No");
                inter = scanner.next();
                if(inter.equalsIgnoreCase("si")){
                    System.out.println("Ingresa los indices de las parejas Ej: 2,3");

                    repe2 = true;
                    while(repe2){
                        try{
                            tupla = scanner2.nextLine().trim();
                            a = Integer.parseInt(tupla.split(",")[0]);
                            b = Integer.parseInt(tupla.split(",")[1]);
                            if(a!=b){
                                    jugadorActual.swap(a,b);
                                    System.out.println(jugadorActual.printDeck());
                                    repe2 = false;
                            }else if(a==b){
                                System.out.println(yellow+"\t No puedes ingresar las mismas cartas :c\n"+reset);
                                repe2 = true;
                            }
                        }catch(NumberFormatException e){
                            System.out.println(yellow+"\t Intentalo de nuevo. Sigue el ejemplo :)\n"+reset);
                            repe2 = true;
                        }catch(IndexOutOfBoundsException e){
                            System.out.println(yellow+"\t Ingresa indices dentro de los rangos :)\n"+reset);
                            repe2 = true;
                        }catch(NullPointerException e){
                            System.out.println(yellow+"\t Ingresa indices dentro de los rangos :)\n"+reset);
                            repe2 = true;
                        } 
                    }
                    repe = true;
                }else if (inter.equalsIgnoreCase("no")){
                    repe = false;
                }else{
                    System.out.println(yellow+"\nDebes escribir 'si' o 'no'\n"+reset);
                }
            }
        }
    }

    /**
     * Método para saber cuantos pares tiene un jugador.
     * @return el número de pares en la mano.
     */
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

    /**
     * Método para robar una carta al jugador anterior.
     * @param i - El índice de la carta que vas a robar.
     */
    public void robarCarta(int i){
        int jugador = numTurno-1;
        if(jugador < 0){
            jugador = jugadores.size()-1;
        }
        if(jugadores.get(jugador).getMano().size() != 0){
            Carta carta = jugadores.get(jugador).getMano().get(i);
            jugadores.get(jugador).getMano().remove(i);
            jugadores.get(numTurno).agregarCarta(carta);
        }
    }

    /**
     * Método para limpiar la terminal.
     */
    public void clear(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}