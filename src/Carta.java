/**
 * Clase que crea objetos de tipo carta
 * @author Andrea Alvarado Camacho
 * @version 1.2
 */
public class Carta{

   // Colores de fondo
   String whiteBG = "\u001B[47m";
   // Colores de letra
   String red="\033[31m"; 
   String black="\u001B[30m";
   // Reset
   String reset="\u001B[0m";

   private int valor;
   private int figura;
   public final String[] figuras = new String[]{null, "diamante", "trébol", "corazon", "picas"};
   public final String[] dibujos = new String[]{null, red+" ♦ "+reset, black+" ♣ "+reset, red+" ♥ "+reset, black+" ♠ "+reset};
   public final String[] valores = new String[]{null, " A", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", " 10", " J", " Q", " K"};

   /**
    * Constructor para crear una carta a partir de dos enteros
    * @param var1 - int para el valor de la carta
    * @param var2 - int para la figura de la carta
    */
   public Carta(int var1, int var2) {
      this.valor = var1 > 0 && var1 < 14 ? var1 : 0;
      this.figura = var2 > 0 && var2 < 5 ? var2 : 0;
   }

   /**
    * Constructor para crear una carta a partir de un entero y un string
    * @param var1 - int para el valor de la carta
    * @param var2 - String para la figura de la carta
    */
   public Carta(int var1, String var2) {
      this.valor = var1 > 0 && var1 < 14 ? var1 : 0;
      this.figura = this.esFiguraValida(var2);
      if (this.figura == 0) {
         System.out.println("La figura " + var2 + " es incorrecta.");
      }

   }

   /**
    * Método para obtener la figura de la carta como int
    * @return figura de la carta
    */
   public int obtenerFigura() {
      return this.figura;
   }

   /**
    * Método para obtener la figura de la carta como string
    * @return figura de la carta
    */
   public String obtenerNombreFigura() {
      return this.figura > 0 && this.figura < 5 ? this.figuras[this.figura] : null;
   }

   /**
    * Método para obtener el valor de la carta como string
    * @return valor de la carta
    */
   public String obtenerNombreValor() {
      return ((this.valor > 0) && (this.valor < 14)) ? this.valores[this.valor] : null;
   }

   /**
    * Método para obtener el valor de la carta como int
    * @return valor de la carta
    */
   public int getValor() {
      return this.valor;
   }

   /**
    * Método para asignar un nuevo valor a la carta
    * @param valorN - valor para el nuevo valor
    */
   public void asignarValor(int valorN){
      this.valor = valorN;
   }

   /**
    * Método para saber si un valor es válido
    * @param var1 - valor que se desea saber
    * @return int - 0
    */
   public int esValorValido(String var1) {
      var1 = var1.trim().toLowerCase();

      for(int var2 = 1; var2 < this.valores.length; ++var2) {
         if (this.valores[var2].equals(var1)) {
            return var2;
         }
      }

      return 0;
   }

   /**
    * Método para saber si una figura es válida
    * @param var1 - figura que se desea saber
    * @return int - 0
    */
   public int esFiguraValida(String var1) {
      var1 = var1.trim().toLowerCase();

      for(int var2 = 1; var2 < this.figuras.length; ++var2) {
         if (this.figuras[var2].equals(var1)) {
            return var2;
         }
      }

      return 0;
   }

   /**
    * Método para saber si una carta es igual a otra
    * @param var1 - objeto de tipo carta a comparar
    * @return boolean - true si son iguales, false si no lo son
    */
   public boolean equals(Carta var1) {
      return this.valor == var1.valor && this.figura == var1.figura;
   }

   /**
    * Método para imprimir el objeto y sus atributos
    * @return String - imprime en consola con formato
    */
   public String toString() {
      String valor="";
      if(this.obtenerNombreFigura().equals("diamante")){
         valor = red+this.valores[this.valor]+reset;
      }else if(this.obtenerNombreFigura().equals("trébol")){
         valor = black+this.valores[this.valor]+reset;
      }else if(this.obtenerNombreFigura().equals("corazon")){
         valor = red+this.valores[this.valor]+reset;
      }else if(this.obtenerNombreFigura().equals("picas")){
         valor = black+this.valores[this.valor]+reset;
      }
      return reset + whiteBG + valor + whiteBG + this.dibujos[this.figura] + reset;
   }
}