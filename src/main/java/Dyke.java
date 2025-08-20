import java.util.Scanner;
public class Dyke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String top_Bar = "____________________________________________________________\n";
        String middle_Bar = "____________________________________________________________\n";
        String bottom_Bar = "____________________________________________________________\n";
        System.out.println( top_Bar + " Hello! I'm DYKE\n What can I do for you?\n\n"
                + middle_Bar + " Bye. Hope to see you again soon!\n" + bottom_Bar);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("Bye!")) {
                System.out.println(top_Bar + " Bye. Hope to see you again soon!\n" + bottom_Bar);
            }
            else {
                System.out.println(top_Bar + input + bottom_Bar);
            }
        }
    }
}
