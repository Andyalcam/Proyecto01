import java.util.Scanner;


public class Main{
	public static void main(String []args){

		// Colores de letra
	    String yellow="\033[33m";

	    // Reset
	    String reset="\u001B[0m";

		List<Jugador> jugadorList = new List<>();
		Baraja baraja = new Baraja();
		Scanner scanner = new Scanner(System.in);
		String historial;
		int jugadores = 0;
		int jugadoresCPU = 0;
		boolean repe = true;

		System.out.println("\t*** BIENVENIDO AL JUEGO 'LA SOLTERONA' ***\n");
		System.out.println("\t       Estas son las instrucciones: ");
		System.out.println("1. Despues de barajear el mazo de cartas se retirara una carta.");
		System.out.println("2. Se repartira la baraja entre los jugadores");
		System.out.println("3. En el primer turno sacaras todos aquellos pares que tengas en tu mano");
		System.out.println("4. En los siguientes turnos, robaras una carta al jugador anterior a ti y si tienes pares los sacaras");
		System.out.println("5. Si te quedas sin cartas saldras del juego, pero si te quedas con la solterona habras perdido");
		System.out.println("6. Se consideran como cartas pares aquellas que tengan solo mismos valores, es decir sin importar su figura o color");

		while(repe){
			System.out.println("\nIngresa el número de jugadores que jugarán");
			try{
				jugadores = scanner.nextInt();
				if(jugadores>0 && jugadores<11){
					repe = false;
				}else{
					System.out.println(yellow + "\tDebes ingresar un número del 1 al 10"+reset);
				}
			}catch(Exception e){
				System.out.println(yellow+"\tDebes ingresar un número"+reset);
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
						System.out.println(yellow+"\t Debes ingresar al menos un contrincante CPU\n"+reset);
						repe = true;
					}else if((jugadores+jugadoresCPU)>10){
						System.out.println(yellow+"\t No puede haber más de 10 jugadores en total :c"+reset);
						repe = true;
					}else{
						repe = false;
					}
				}else if(jugadores == 10){
					System.out.println(yellow+"\nNingun CPU jugará :c\n"+reset);
					repe = false;
				}else{
					System.out.println("Ingresa el numero de jugadores CPU, si no quieres jugar contra la maquina ingresa 0");
					jugadoresCPU = scanner.nextInt();
					if((jugadores+jugadoresCPU)>10){
						System.out.println(yellow+"\t No puede haber más de 10 jugadores en total :c\n"+reset);
						repe = true;
					}else{
						repe = false;
					}
				}
			}catch(Exception e){
				if(jugadores==1){
					System.out.println(yellow+"\t Ingresa números del 1 al " + (10-jugadores) + "\n"+reset);
				}else{
					System.out.println(yellow+"\t Ingresa números del 0 al " + (10-jugadores) + "\n"+reset);
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

		repe = true;
		System.out.println("¿Deseas saber el historial de la partida?\tSi/No");
		while(repe){
			historial = scanner.next();
			if(historial.equalsIgnoreCase("si")){
				System.out.println("Aqui se supone que iba el historial jsjs, por lo que queda como ejercicio al jugador.");
				repe = false;
			}else if (historial.equalsIgnoreCase("no")){
				System.out.println("Ayoos :3");
				repe = false;
			}else{
				System.out.println(yellow+"Debes escribir 'si' o 'no'"+reset);
			}
		}
	}
}