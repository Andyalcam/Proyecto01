/**
 * Clase que crea objetos de tipo carta
 * @author Andrea Alvarado Camacho
 * @version 1.2
 */
public class Carta{
   private  int valor;
   private final int figura;
   public final String[] figuras = new String[]{null, "diamante", "trebol", "corazon", "picas"};
   public final String[] valores = new String[]{null, "as", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez", "J", "Q", "K"};

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
    * Constructor para crear una carta a partir de dos String
    * @param var1 - cadena para el valor de la carta
    * @param var2 - cadena para la figura de la carta
    */
   public Carta(String var1, String var2) {
      this.valor = this.esValorValido(var1);
      if (this.valor == 0) {
         System.out.println("El valor " + var1 + " es incorrecto.");
      }

      this.figura = this.esFiguraValida(var2);
      if (this.figura == 0) {
         System.out.println("La figura " + var2 + " es incorrecta.");
      }

   }

   /**
    * Metodo para obtener la figura de la carta como int
    * @return figura de la carta
    */
   public int obtenerFigura() {
      return this.figura;
   }

   /**
    * Metodo para obtener la figura de la carta como string
    * @return figura de la carta
    */
   public String obtenerNombreFigura() {
      return this.figura > 0 && this.figura < 5 ? this.figuras[this.figura] : null;
   }

   /**
    * Metodo para obtener el valor de la carta como string
    * @return valor de la carta
    */
   public String obtenerNombreValor() {
      return this.valor > 0 && this.valor < 14 ? this.valores[this.valor] : null;
   }

   /**
    * Metodo para obtener el valor de la carta como int
    * @return valor de la carta
    */
   public int obtenerValor() {
      return this.valor;
   }

   /**
    * Metodo para asignar un nuevo valor a la carta
    * @param valorN - valor para el nuevo valor
    */
   public void asignarValor(int valorN){
      this.valor = valorN;
   }

   /**
    * Metodo para saber si un valor es valido
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
    * Metodo para saber si una figura es valida
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
    * Metodo para saber si una carta es igual a otra
    * @param var1 - objeto de tipo carta a comparar
    * @return boolean - true si son iguales, false si no lo son
    */
   public boolean equals(Object var1) {
      Carta var2 = (Carta)var1;
      return this.valor == var2.valor && this.figura == var2.figura;
   }

   /**
    * Metodo para imprimir el objeto y sus atributos
    * @return String - imprime en consola con formato
    */
   public String toString() {
      return this.valores[this.valor] + " de " + this.figuras[this.figura];
   }

   public static void main(String[] var0) {
      Carta[] var1 = new Carta[52];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = new Carta(var2 % 13 + 1, var2 / 13 + 1);
         System.out.println(var1[var2]);
      }

   }
}