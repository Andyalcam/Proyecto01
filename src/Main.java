import java.util.Scanner;
import java.util.InputMismatchException;

public class Main{
	public static void main(String []args){

		// Colores de fondo
		String blackBG = "\u001B[40m";
		String redBG = "\u001B[41m";
		String greenBG = "\u001B[42m";
		String yellowBG = "\u001B[43m";
		String blueBG = "\u001B[44m";
		String purpleBG = "\u001B[45m";
		String cyanBG = "\u001B[46m";
		String whiteBG = "\u001B[47m";
		// Colores de letra
	    String red="\033[31m"; 
	    String green="\033[32m"; 
	    String yellow="\033[33m";
	    String cyan="\033[36m";
	    String black="\u001B[30m";
	    // Reset
	    String reset="\u001B[0m";

		List<Jugador> jugadorList = new List<>();
		Baraja baraja = new Baraja();
		Scanner scanner = new Scanner(System.in);
		int jugadores = 0;
		int jugadoresCPU = 0;
		boolean repe = true;

		while(repe){
			System.out.println("Ingresa el número de jugadores que jugarán ");
			try{
				jugadores = scanner.nextInt();
				if(jugadores>0 && jugadores<11){
					repe = false;
				}else{
					System.out.println("\tDebes ingresar un número del 1 al 10\n");
				}
			}catch(Exception e){
				System.out.println("\tDebes ingresar un número\n");
				scanner.next();
			}
		}
		repe = true;

		while(repe){
			try{
				if(jugadores == 1){
					System.out.println("Ingresa el numero de jugadores CPU");
					jugadoresCPU = scanner.nextInt();
					if(jugadoresCPU <= 0){
						System.out.println("\t Debes ingresar al menos un contrincante CPU\n");
						repe = true;
					}else if((jugadores+jugadoresCPU)>10){
						System.out.println("\t No puede haber más de 10 jugadores en total :c\n");
						repe = true;
					}else{
						repe = false;
					}
				}else if(jugadores == 10){
					System.out.println("Ningun CPU jugará :c\n");
					repe = false;
				}else{
					System.out.println("Ingresa el numero de jugadores CPU, si no quieres jugar contra la maquina ingresa 0");
					jugadoresCPU = scanner.nextInt();
					if((jugadores+jugadoresCPU)>10){
						System.out.println("\t No puede haber más de 10 jugadores en total :c\n");
						repe = true;
					}else{
						repe = false;
					}
				}
			}catch(Exception e){
				if(jugadores==1){
					System.out.println("\t Ingresa números del 1 al " + (10-jugadores) + "\n");
				}else{
					System.out.println("\t Ingresa números del 0 al " + (10-jugadores) + "\n");
				}
				scanner.next();
			}
		}
		
		for(int i = 0; i < jugadores; i++){
			System.out.println("Ingresa el nombre del jugador " + (i+1));
			String nombre = scanner.next();
			jugadorList.add(jugadorList.size(), new Jugador(nombre, true));
		}

		for (int i = 0; i < jugadoresCPU; i++){
			jugadorList.add(jugadorList.size(), new Jugador("CPU " + (i+1) + "", false));
		}

		Solterona solterona = new Solterona(jugadorList, baraja);

		solterona.juego();
	}

}