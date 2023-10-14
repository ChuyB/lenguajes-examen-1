package utils;

/**
 * La clase Matrices contiene algunos métodos para trabajar con matrices.
 */
public class Matrices {

  /**
   * Este método calcula la traspuesta de una matriz.
   *
   * @param matriz La matriz a trasponer.
   * @return La matriz traspuesta.
   */
  public static Integer[][] traspuesta(Integer[][] matriz) {
    int n = matriz.length;
    int m = matriz[0].length;
    Integer[][] matrizT = new Integer[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++)
        matrizT[j][i] = matriz[i][j];
    }

    return matrizT;
  }

  /**
   * Este método calcula el producto de una matriz cuadrada con su traspuesta.
   *
   * @param matriz La matriz cuadrada a multiplicar.
   * @return El producto de la matriz y su traspuesta.
   * @throws IllegalArgumentException Si la matriz no es cuadrada o no tiene
   *                                  elementos.
   */
  public static Integer[][] mult(Integer[][] matriz) {
    int n = matriz.length;
    if (n == 0)
      throw new IllegalArgumentException("La matriz debe tener al menos un elemento");
    if (n != matriz[0].length)
      throw new IllegalArgumentException("La matriz debe ser cuadrada");

    Integer[][] res = new Integer[n][n];
    Integer[][] matrizT = traspuesta(matriz);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        res[i][j] = 0;
        for (int k = 0; k < n; k++)
          res[i][j] += matriz[i][k] * matrizT[k][j];
      }
    }

    return res;
  }
}
