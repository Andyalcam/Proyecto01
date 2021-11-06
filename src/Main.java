import java.util.Scanner;
import java.util.InputMismatchException;

public class Main{
	public static void main(String []args){

		List<Jugador> jugadorList = new List<>();
		Baraja baraja = new Baraja();
		Scanner scanner = new Scanner(System.in);
		int jugadores = 0;
		int jugadoresCPU = 0;
		boolean repe = true;

		while(repe){
			System.out.println("Ingresa el número de jugadores que jugarán");
			try{
				jugadores = scanner.nextInt();
				if(jugadores>1 && jugadores<11){
					repe = false;
				}else{
					System.out.println("\tDebes ingresar un número del 2 al 10\n");
				}
			}catch(Exception e){
				System.out.println("\tDebes ingresar un número\n");
				scanner.next();
			}
		}
		repe = true;

		while(repe){
			System.out.println("Ingresa el numero de jugadores CPU, si no quieres jugar contra la maquina ingresa 0");
			try{
				jugadoresCPU = scanner.nextInt();
				repe = false;
			}catch(Exception e){
				System.out.println("Ingresa solo numeros");
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




		/*System.out.println(baraja+"\n");
		baraja.revolver();
		System.out.println(baraja);*/





	}

}