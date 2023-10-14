package utils;

/**
 * La clase Rotar contiene un método para rotar un string.
 */
public class Rotar {

  /**
   * Este método rota un string hacia la izquierda.
   *
   * @param str    El string a rotar.
   * @param places El número de lugares a rotar.
   * @return La cadena rotada.
   */
  public static String rotar(String str, int places) {
    if (str.length() > 1 && places > 0) {
      String tString = str.substring(1) + str.substring(0, 1);
      str = rotar(tString, places - 1);
    }
    return str;
  }
}