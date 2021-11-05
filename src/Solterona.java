import java.util.Random;
import java.util.Scanner;

public class Solterona {

    Baraja baraja;
    int sizeBaraja;
    List<Jugador> jugadores;
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
        jugadorActual = jugadores.get(numTurno);
        turno();
        int pares = paresMano();
        while (pares != 0) {
            System.out.println("Ingresa los indices de las parejas Ej: 2,3");
            scanner.next();
            
        }
    }

    public boolean perdedor() {
        return sizeBaraja == 1;
    }


    public void turno() {
        if (jugadorActual.isCPU()) {//Si es la máquina

        } else {//Si es una persona
            System.out.println((jugadorActual.printDeck()));
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
        System.out.println(valoresMano);
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


}
