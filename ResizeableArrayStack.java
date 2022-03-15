import java.util.EmptyStackException;

/**
 * This is the ResizeableArrayStack class which implements the StackInterface interface.
 *
 * With this class, we can create stacks (last-in-first-out (LIFO) stack of objects).
 *
 * The stack is implemented using a resizeable array.
 *
 * Each method is documented separately.
 *
 * @author George Matta
 * @version 1.2
 */
public class ResizeableArrayStack<T> implements StackInterface<T>{

    /**
     * The main stack array which we will be able to resize and apply stack operations on.
     */
    private T[] stack;

    /**
     * An int denoting the number of entries within our stack.
     */
    private int numOfEntries;

    /**
     * A boolean denoting whether or not the constructor was called properly.
     * This makes sure our ResizeableArrayStack remains uncorrupted.
     */
    private boolean integrityOk = false;

    /**
     * A final static int denoting the maximum capacity of a stack.
     */
    private static final int MAX_CAPACITY = 1000;

    /**
     * A final static int denoting the default capacity of a stack.
     * This is to allow for a no-arg constructor/default constructor.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Our default constructor. This initializes a stack with a DEFAULT_CAPACITY capacity
     */
    public ResizeableArrayStack(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Our main ResizeableArrayStack constructor.
     * Creates a ResizeableArrayBag given a capacity.
     * We check this capacity using checkCapacity().
     * @param capacity The capacity we want our stack to have
     */
    public ResizeableArrayStack(int capacity){
        checkCapacity(capacity);
        this.stack = createStack(capacity);
        this.numOfEntries = 0;
        this.integrityOk = true;
    }

    /**
     * A private method to create a generic-type array
     * @param capacity The capacity of the array object
     * @return The array object of T generics
     */
    @SuppressWarnings("unchecked")
    private T[] createStack(int capacity){
        return (T[]) new Object[capacity];
    }

    /**
     * A method to throw an error if the capacity of the bag is too large or too small
     * @param capacity The capacity we are checking
     */
    private void checkCapacity(int capacity){
        String errorMessage = "Attempted to create a bag with a capacity (" + capacity + ") which is too ";
        if (capacity >= MAX_CAPACITY){
            errorMessage += "large";
            throw new IllegalStateException(errorMessage);
        }
        if (capacity < 1){
            errorMessage += "small";
            throw new IllegalStateException(errorMessage);
        }
    }

    /**
     * A method to check if the number of entries is larger than the maximum capacity
     * If so, we double the capacity
     */
    private void ensureCapacity(){
        if(numOfEntries >= stack.length){
            doubleCapacity();
        }
    }

    /**
     * A method to check the integrity of the ResizeableArrayBag to make sure the constructor was called.
     */
    private void checkIntegrity(){
        if(!integrityOk){
            throw new SecurityException("ResizeableArrayStack object is corrupt or was not initialized properly.");
        }
    }

    /**
     * A method to double the capacity of the stack array
     */
    private void doubleCapacity(){
        int newCapacity = stack.length * 2;
        checkCapacity(newCapacity);
        T[] newStack = createStack(newCapacity);
        stack = copyEntries(newStack);
    }

    /**
     * A method to copy all the entries from this stack into a new stack
     * @param newStack The stack to copy the entries into
     * @return The stack with the copied entries
     */
    private T[] copyEntries(T[] newStack){
        for(int i = 0; i < numOfEntries; i++){
            if(newStack[i] == null){
                continue;
            }
            newStack[i] = stack[i];
        }
        return newStack;
    }

    /**
     * A method to push a new entry into our stack. With this ADT, we add to the end of the array
     * @param newEntry The generic object entry we are adding
     */
    @Override
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();

        stack[numOfEntries] = newEntry;
        numOfEntries++;
    }

    /**
     * A method to remove an entry from our stack. With this ADT, we remove from the end of the array.
     * @return The generic object we popped from the stack
     */
    @Override
    public T pop() {
        checkIntegrity();

        if(isEmpty()){
            throw new EmptyStackException();
        }

        T popped = stack[numOfEntries - 1];
        stack[numOfEntries - 1] = null;
        numOfEntries--;

        return popped;
    }

    /**
     * A method to return the top entry in our stack (without removing it)
     * @return The generic object at the top of the stack
     */
    @Override
    public T peek() {
        checkIntegrity();

        if(isEmpty()){
            throw new EmptyStackException();
        }

        return stack[numOfEntries - 1];
    }

    /**
     * A method to check if our stack is empty. This just checks if the number of entries is less than 1.
     * @return A boolean denoting whether or not the stack is empty
     */
    @Override
    public boolean isEmpty() {
        checkIntegrity();
        return numOfEntries < 1;
    }

    /**
     * A method to clear our entire stack. We just continuously remove the top entry while the stack is not empty.
     */
    @Override
    public void clear() {
        checkIntegrity();

        while(!isEmpty()){
            stack[numOfEntries] = null;
            numOfEntries--;
        }
    }

    /**
     * A private method to return a String representation of the array
     */
    public String toString(){
        String result = "[";

        for(int i = 0; i < numOfEntries; i++){
            result += stack[i];
            if(i != numOfEntries - 1){
                result += ", ";
            }
        }
        result += "]";

        return result;
    }



}