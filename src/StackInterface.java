package src;

/**
 * This is the main interface for Stacks (with which we create the ResizeableArrayStack and the LinkedStack)
 *
 * A stack uses a last-in-first-out approach to storing data, meaning the only data we have access to is whatever is
 * on the very top of the stack.
 *
 * As such, we have a push method to add an item to a stack, a pop method which removes and returns the top item of the stack,
 * and a peek method to return the top item of the stack.
 *
 * We also have an isEmpty method to check if the stack is empty and a clear method to clear a stack of all its entries
 *
 * All the methods are documented separately.
 *
 * @author Pierlorenzo Peruzzo
 * @author George Matta
 * @version 1.1
 */
public interface StackInterface<T>{

    /**
     * Adds a new entry to the top of this stack
     * @param newEntry The object to be added to the stack
     */
    public void push(T newEntry);

    /** Removes and returns this stack's top entry.
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty before the operation.
     */
    public T pop();

    /** Retrieves this stack's top entry.
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public T peek();

    /**
     * Detects whether this stack is empty.
     * @return True if the stack is empty.
     */
    public boolean isEmpty();

    /**
     * Removes all entries from this stack.
     */
    public void clear();
}