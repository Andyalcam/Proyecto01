import java.util.Random;
import java.util.Scanner;

public class Solterona {

    Baraja baraja;
    int sizeBaraja;
    List<Jugador> jugadores;
    Jugador jugadorAnterior;
    Jugador jugadorActual;
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    int numTurno;

    public Solterona(List<Jugador> jugadores, Baraja baraja) {
        this.baraja = baraja;
        modoDeJuego(jugadores);
        repartirBaraja(jugadores, this.baraja);
        numTurno = random.nextInt(jugadores.size() - 1);
        this.jugadores = jugadores;
        jugadorActual = jugadores.get(numTurno);
        if(numTurno == 0){
            jugadorAnterior = jugadores.get((jugadores.size()-1));
        }else{
            jugadorAnterior = jugadores.get(numTurno-1);
        }
    }

    public void repartirBaraja(List<Jugador> listJugadores, Baraja baraja) {
        int jugadores = listJugadores.size();
        int longitudBaraja = baraja.size();
        int jugador = random.nextInt(jugadores - 1);
        for (int i = 0; i < longitudBaraja; i++) {
            if (jugador == jugadores) {
                jugador = 0;
            }
            listJugadores.get(jugador).agregarCarta(baraja.tomarCarta());
            jugador++;
        }
    }


    public void modoDeJuego(List<Jugador> jugadores) {
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

    public void juego() {
        while(!perdedor()){
            if(numTurno >= jugadores.size()){
                numTurno = 0;
            }
            jugadorActual = jugadores.get(numTurno);
            if(jugadorAnterior.getMano().size() != 0){
                System.out.println("Es el turno de " + jugadorActual.getNombre());
                robarCarta();
                turno();
            }
            numTurno++;
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){}
        }
        for(int i = 0 ; i < jugadores.size(); i++){
            if(jugadores.get(i).getMano().size() == 1){
                System.out.println("Perdio " + jugadores.get(i).getNombre() + " Se quedo con la solterona jijijija");
                System.out.println("La solterona fue: " + jugadores.get(i).getManoToString());
            }
        }
    }

    public boolean perdedor() {
        return sizeBaraja == 1;
    }


    public void turno() {
        if (jugadorActual.isCPU()) {//Si es la máquina
            System.out.println((jugadorActual.printDeck()));
            int pares = paresMano();
            if(pares == 1){
                System.out.println(jugadorActual.getNombre() + " tienes " + pares + " par");
            }else{
                System.out.println(jugadorActual.getNombre() + " tienes " + pares + " pares");
            }
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
            int i=0,j=0;
            boolean repe = true;
            String tupla = "";
            System.out.println((jugadorActual.printDeck()));
            int pares = paresMano();
            if(pares == 1){
                System.out.println(jugadorActual.getNombre() + " tienes " + pares + " par");
            }else{
                System.out.println(jugadorActual.getNombre() + " tienes " + pares + " pares");
            }
            while (repe) {
                while (pares != 0) {
                    try{
                        System.out.println("Ingresa los indices de las parejas Ej: 2,3");
                        tupla = scanner.nextLine().trim();
                        i = Integer.parseInt(tupla.split(",")[0]);
                        j = Integer.parseInt(tupla.split(",")[1]);
                        repe = false;
                    }catch(Exception e){
                        System.out.println("\t Intentalo de nuevo. Sigue el ejemplo :)\n");
                        repe = true;
                    }
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
                            System.out.println("Tus cartas no son pares");
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
