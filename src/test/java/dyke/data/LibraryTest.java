package dyke.data;

import dyke.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryTest {
    Library library = new Library();

    @Test
    public void testAddDelete() {
        Task[] tasks = {new Task("test 1"), new Task("test 2")};

        String s = library.addTask(tasks[0]);
        assertEquals(1, library.size());

        String res = " Great! I'm keeping track :D:\n" +
                "\t" + tasks[0].toString() + "\n There is 1 task in the Library.";
        assertEquals(res , s);


        s = library.deleteTask(2);
        res = "Hey... That doesn't exist!";
        assertEquals(res , s);
    }
}
