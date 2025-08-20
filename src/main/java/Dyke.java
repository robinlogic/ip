import java.util.Scanner;
public class Dyke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bar = "____________________________________________________________\n";
        System.out.println( bar + " Hello! I'm DYKE\n What can I do for you?\n\n"
                + bar);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("Bye!")) {
                System.out.println(bar + " Bye. Hope to see you again soon!\n" + bar);
                break;
            }
            else {
                System.out.println(bar + input +  "\n" + bar);
            }
        }
        sc.close();
    }
}
