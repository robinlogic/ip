import java.util.Scanner;
import java.util.List;
public class Dyke {
    static Library library = new Library();
    static int accountIndex = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bar = "\t____________________________________________________________\n";
        System.out.println( bar + "\t Hello! I'm DYKE\n\t What can I do for you?\n\n"
                + bar);

        while (sc.hasNext()) {
            String[] input = sc.nextLine().split(" ");
            if (input[0].equals("Bye!")) {
                System.out.println(bar + "\t Bye. Hope to see you again soon!\n" + bar);
                break;
            }
            if (input[0].equals("list")) {
                System.out.print(bar);
                library.PrintList();
                System.out.println(bar);
            }

            else if (input[0].equals("mark") && input.length > 1) {
                System.out.println(bar);
                library = library.markTask(Integer.parseInt(input[1]) - accountIndex);
                System.out.println(bar);
            }

            else {
                library = library.addTask(new Task(input[0]));
                System.out.println(bar + "\tadded: " + input[0] +  "\n" + bar);
            }
        }
        sc.close();
    }

}
