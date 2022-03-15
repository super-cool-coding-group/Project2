public class ArrayStackTest {

    // CONSTRUCTOR
    // Check when capacity is 0
    // Check when capacity isn't provided
    // Check when capacity is between 1 and maximum
    // Check when capacity is greater than the maximum

    // PUSH
    // Push an entry
        // Check new num of entries (make sure it increased)
        // Check stack
    // Push more entries
        // Check new num of entries (make sure it increased)
        // Check stack
    // Make sure we can't push more than maximum num of entries
    // make sure we double capacity correctly

    // POP
        // Pop when stack is empty
        // Pop when stack has items
        // Pop when stack is full
        // Check that we returned the correct item
        // Check num of entries (make sure it decreased)

    // PEEK
        // peek when stack is empty
        // Check num of entries (make sure it didn't change)
        // check that we returned the correct item

    // ISEMPTY
        // check when stack is empty
        // check when stack is not empty

    // CLEAR
        // clear when stack is empty
        // clear when stack is full
        // check number of entries (make sure it's 0)
        // check stack contents (make sure it's empty)


    // THIS IS JUST A TEMPORARY MAIN METHOD TO MAKE SURE I'M NOT BEING DUMB
    public static void main(String[] args){
        ResizeableArrayStack<String> ras = new ResizeableArrayStack<String>();

        System.out.println(ras);
        ras.push("hello");
        System.out.println(ras);
        ras.push("world");
        System.out.println(ras);
        ras.push("!");
        System.out.println(ras);
        System.out.println(ras.peek());
        System.out.println(ras.pop());
        System.out.println(ras);
        System.out.println(ras.pop());
        System.out.println(ras);
        ras.clear();
        System.out.println(ras);
    }

}
