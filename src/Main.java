import java.util.Scanner;

public class Main{
	public static void main(String []args){

		List<Jugador> jugadorList = new List<>();
		Baraja baraja = new Baraja();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Ingresa el número de jugadores que jugarán");
		int jugadores = scanner.nextInt();

		for(int i = 0; i < jugadores; i++){
			System.out.println("Ingresa el nombre del jugador " + (i+1));
			String nombre = scanner.next();
			jugadorList.add(jugadorList.size(), new Jugador(nombre));
		}

		Solterona solterona = new Solterona(jugadorList, baraja);

		solterona.juego();




		/*System.out.println(baraja+"\n");
		baraja.revolver();
		System.out.println(baraja);*/





	}

}