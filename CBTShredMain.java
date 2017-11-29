import java.util.Scanner;
import java.io.*;
public class CBTShredMain {
  public static void main(String[] args) throws FileNotFoundException, IOException{
    Scanner scanner =  new Scanner(System.in);

    System.out.println("Class Name: ");
    String className = scanner.nextLine();

    System.out.println("Full Name: ");
    String fullName = scanner.nextLine();

    System.out.println("Period: ");
    int period = scanner.nextInt();

    CBTShred shredder = new CBTShred("shred.txt", className, fullName, period);
    System.out.println("\n***************************\n" + shredder.format() + "\n***************************\n");

    //shredder.writeToFile(className + ".java");

  }
}
