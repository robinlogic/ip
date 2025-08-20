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
        ArrayList<Task> copy = this.library;
        copy.add(task);
        return new Library(copy);
    }

    public ArrayList<Task> getTasks() {
        return this.library;
    }

    private Task getTask(int index){
        return this.library.get(index);
    }

    public Library markTask(int index){
        if (index < 0 || index >= this.library.size()) {
            System.out.println("\tHey...yee That doesn't exist!");
            return this;
        }
        Task task = this.getTask(index).isDone();
        this.library.set(index, task);
        ArrayList<Task> copy = this.library;
        System.out.println("\tNice Work! I have marked this task as done:");
        System.out.println("\t" + (index + 1) + ". " + task);
        return new Library(copy);
    }

    public boolean isEmpty() {
        return this.library.isEmpty();
    }

    public int size() {
        return this.library.size();
    }

    public void PrintList() {
        if (this.isEmpty()) {
            System.out.println("\tlist is empty :(");
        } else {
            System.out.println("\tHere it is!");
            for (int i = 0; i < this.library.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + this.getTask(i));
            }
        }
    }
}
