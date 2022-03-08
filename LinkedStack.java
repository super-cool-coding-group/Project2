import java.util.EmptyStackException;

/**
 * This is the LinkedStack class which implements the StackInterface interface.
 *
 * With this class, we can create stacks (last-in-first-out (LIFO) stack of objects).
 *
 * The stack is implemented using a linked chain of Nodes.
 * 
 * Each method is documented separately.
 *
 * @author Pierlorenzo Peruzzo
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    //#region Private Fields

    private Node<T> head;

    //#endregion

    //#region Constructors

    public LinkedStack(){
        head = null;
    }

    //#endregion

    //#region Implement StackInterface

    @Override
    public void push(T newEntry) {  head = new Node<T>(newEntry, head); }

    @Override
    public T pop() {
        T top = peek();
        // Assertion: head != null
        head = head.next;
        return top;
    }

    @Override
    public T peek() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        else{
            return head.data;
        }
    }

    @Override
    public boolean isEmpty() { return head == null; }

    @Override
    public void clear() { head = null; }

    //#endregion
}

/**
 * This is the Node class.
 * 
 * We use this class to hold data relative to a chained item in the LinkedStack.
 */
class Node<T> {
    T data;
    Node<T> next;

    Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}