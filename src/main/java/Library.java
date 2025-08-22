import java.util.ArrayList;

public class Library {
    private ArrayList<Task> library;

    public Library() {
        this.library = new ArrayList<Task>();
    }

    private Library(ArrayList<Task> library) {
        this.library = library;
    }

    public Library addTask(Task task) {

        System.out.println("\t Great! I'm keeping track :D:");
        System.out.println("\t\t" + task.toString());
        library.add(task);
        int size = library.size();
        System.out.printf("\t There %s %d %s in the Library.%n",
                (size > 1 ? "are" : "is"),
                size,
                "task" + (size > 1 ? "s" : ""));

        return new Library(this.library);
    }

    public ArrayList<Task> getTasks() {
        return this.library;
    }

    private Task getTask(int index){
        return this.library.get(index);
    }

    public void markTask(int index){
        if (index < 0 || index >= this.library.size()) {
            System.out.println("\tHey... That doesn't exist!");
        }
        else {
            Task task = this.getTask(index).isDone();
            this.library.set(index, task);
            System.out.println("\tNice Work! I have marked this task as done:");
            System.out.println("\t" + (index + 1) + ". " + task);
        }
    }

    public void deleteTask(int index){
        if (index < 0 || index >= this.library.size()) {
            System.out.println("\tHey... That doesn't exist!");
        }

        Task task = this.getTask(index);
        library.remove(index);
        System.out.println("\tAs you wish... Begone Task!:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + this.size() + " tasks.");

    }

    public boolean isEmpty() {
        return this.library.isEmpty();
    }

    public int size() {
        return this.library.size();
    }

    public void PrintList() {
        if (this.isEmpty()) {
            System.out.println("\n\tlist is empty :(");
        } else {
            System.out.println("\n\tHere it is!");
            for (int i = 0; i < this.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + this.getTask(i));
            }
        }
    }
}
