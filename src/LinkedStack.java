package src;

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
 * @version 2.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    //#region Private Fields

    /**
     * The top node in the Stack
     */
    private Node<T> head;

    //#endregion

    //#region Constructors

    /**
     * Default constructor, initalize the head node to null.
     */
    public LinkedStack(){
        head = null;
    }

    //#endregion

    //#region Implement StackInterface

    /**
     * Adds a new entry to the top of this LinkedStack.
     * @param newEntry  An object to be added to the LinkedStack.
     */
    @Override
    public void push(T newEntry) {  head = new Node<T>(newEntry, head); } // Create a new node, and add it to the top of the LinkedStack

    /**
     * Removes and returns this LinkedStack's top entry.
     * @return  The object at the top of the LinkedStack.
     * @throws  EmptyStackException if the LinkedStack is empty before the operation.
     */
    @Override
    public T pop() {

        // Get the first object from the stack (if one is available)
        T top = peek();
        // Assertion: head != null
        if (head != null){
            head = head.next;
        }
        // We are removing the head, so the new head will be the next item in the LinkedStack.
        // Return the previous top element that we got through .peek();
        return top;
    }

    /**
     * Retrieves this LinkedStack's top entry.
     * @return  The object at the top of the LinkedStack.
     * @throws  EmptyStackException if the LinkedStack is empty.
     */
    @Override
    public T peek() {
        // If the Stack is empty we throw an exception
        if (isEmpty()){
            throw new EmptyStackException();
        }
        // Otherwise we just return the top item
        else{
            return head.data;
        }
    }

    /**
     * Detects whether this LinkedStack is empty.
     * @return  True if the LinkedStack is empty.
     */
    @Override
    public boolean isEmpty() { return head == null; }

    /**
     * Removes all entries from this LinkedStack by setting the top node to null.
    */
    @Override
    public void clear() { head = null; }

    //#endregion

    //#region Public Fields

    /**
     * Create a String representation of all the items in the LinkedStack.
     * @return The string with all the items in the LinkedStack.
     */
    @Override
    public String toString(){
        // Start from the top item
        Node<T> currentNode = head;

        String res = "";
        // If there are no items in the stack, we just return empty string
        if (currentNode == null){
            return res;
        }

        // We loop throught the stack, and concatenate the value to the res string
        while (currentNode.next != null){
            res += currentNode.data + " ";
            // Process the next item in the stack (if any)
            currentNode = currentNode.next;
        }

        return res;
    }

    //#endregion
}

/**
 * This is the Node class.
 *
 * We use this class to hold data relative to a chained item in the LinkedStack.
 */
class Node<T> {
    /**
     * The data variable holds the data for the node
     */
    T data;
    /**
     * The next variable holds the reference to the next node in the linked structure
     */
    Node<T> next;

    /**
     * A constructor for a node. Creates a Node given its data and the reference to the next node.
     * @param data The data held by the Node
     * @param next The reference to the next node in the linked structure
     */
    Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}