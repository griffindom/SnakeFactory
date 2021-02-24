import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MyStackTest {
    MyStack myStack;
    @BeforeEach
    public void setup() {
        myStack = new MyStack();
    }
    @Test
    public void testNumber() {
        myStack.push("Griffin");
        myStack.push("Jasper");
        myStack.push("William");
        myStack.push("James");
        myStack.push("Sydney");
        myStack.pop();
        assertEquals(myStack.top(), "James");
    }

    @Test
    public void sydneyTest() {
        myStack.push("Griffin");
        myStack.push("Jasper");
        myStack.push("Sydney");
        myStack.pop();
        assertEquals(myStack.top(), "Jasper");
    }

    @Test
    public void williamTest() {
        myStack.push("Jasper");
        myStack.push("Sydney");
        myStack.push("Griffin");
        myStack.push("William");
        myStack.push("James");
        myStack.pop();
        assertEquals(myStack.top(), "Sydney");
    }
    @Test
    public void testStackPush() {
        stack.push("1a");
        stack.push("0a");
        stack.push("2a");
        stack.push("3a");
        assertEquals("3a", myStack.pop());
    }
    @Test
    public void deleteNTest() {
        myStack.push("Griffin");
        myStack.push("Jasper");
        myStack.push("Prince William");
        myStack.push("James");
        myStack.push("Sydney");
        myStack.push("Jerry Garcia");
        myStack.delete(3);
        assertEquals(myStack.top(), "Prince William");
    }
}