import utils.Matrices;
import utils.Rotar;

public class Main {
  public static void main(String[] args) {
    System.out.println(Rotar.rotar("hola mundo", 9));

    Integer[][] matrizA = { { 1, 3 }, { 2, 4 } };
    Integer[][] res = Matrices.mult(matrizA);

    for (Integer[] row : res) {
      for (Integer colEl : row)
        System.out.printf("%s ", colEl);
      System.out.println();
    }
  }
}
