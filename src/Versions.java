
public class Versions {
  public static void main(String[] args) {
    System.out.println(Runtime.version());

    System.getProperties().forEach((k, v) -> {
      System.out.printf("%s:%s%n", k, v);
    });
  }
}
