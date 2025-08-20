import java.util.ArrayList;

public class Library {
    private final ArrayList<Task> library;

    public Library() {
        this.library = new ArrayList<Task>();
    }

    private Library(ArrayList<Task> library) {
        this.library = library;
    }

    public Library addTask(Task task) {
        ArrayList<Task> copy = this.library;
        copy.add(task);
        return new Library(copy);
    }

    public ArrayList<Task> getTasks() {
        return this.library;
    }

    public Task getTask(int index){
        return this.library.get(index);
    }

    public void PrintList() {
        if (this.library.isEmpty()) {
            System.out.println("\tlist is empty :(");
        } else {
            System.out.println("\tHere it is!");
            for (int i = 0; i < this.library.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + this.getTask(i));
            }
        }
    }
}
