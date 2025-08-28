package dyke.tasks;

import dyke.parse.DykeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private Deadline task;

    @BeforeEach
    public void setUp() throws DykeException {
        task = new Deadline(
                "running this test", "2025-08-25");
    }
    @Test
    public void deadlineString(){
        assertEquals(
                "[D] [ ] running this test (by: 2025-08-25)", task.toString());
    }

    @Test
    public void deadlineEncode(){
        assertEquals("D | 0 | running this test | 2025-08-25", task.encode());
    }
}

