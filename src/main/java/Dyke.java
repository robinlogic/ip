import java.util.Scanner;
import java.util.List;
public class Dyke {
    static String[] library = new String[100];
    static int queue = 0;
    static boolean library_Empty = true;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bar = "\t____________________________________________________________\n";
        System.out.println( bar + "\t Hello! I'm DYKE\n\t What can I do for you?\n\n"
                + bar);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("Bye!")) {
                System.out.println(bar + "\t Bye. Hope to see you again soon!\n" + bar);
                break;
            }
            if (input.equals("list")) {
                System.out.print(bar);
                PrintList();
                System.out.println(bar);
            }
            else {
                library[queue] = input;
                queue++;
                library_Empty = false;
                System.out.println(bar + "\tadded: " + input +  "\n" + bar);
            }
        }
        sc.close();
    }

    public static void PrintList() {
        if (library_Empty) {
            System.out.println("\tlist is empty :(");
        }
        else {
            System.out.println("\tHere it is!");
            for (int i =0; i < queue; i++) {
                System.out.println("\t" + (i + 1) + ". " + library[i]);
            }
        }
    }
}
