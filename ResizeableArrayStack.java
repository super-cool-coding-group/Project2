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
 * @version 1.0
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
     * A method which checks if the capacity is too small or too large based on the MAXIMUM_CAPACITY
     * @param capacity The capacity we are checking
     */
    public void checkCapacity(int capacity){
        if(capacity < 1){
            throw new IllegalArgumentException("The capacity provided was too small (less than 1) to create a Stack");
        }
        if(capacity > MAX_CAPACITY){
            throw new IllegalArgumentException("The capacity provided was too large (over "
            + MAX_CAPACITY +
            ") to create a Stack");
        }
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
     * A method to check the integrity of the ResizeableArrayBag to make sure the constructor was called.
     */
    private void checkIntegrity(){
        if(!integrityOk){
            throw new SecurityException("ResizeableArrayStack object is corrupt or was not initialized properly.");
        }
    }

    @Override
    public void push(T newEntry) {
        checkIntegrity();
    }

    @Override
    public T pop() {
        checkIntegrity();
        return null;
    }

    @Override
    public T peek() {
        checkIntegrity();
        return null;
    }

    @Override
    public boolean isEmpty() {
        checkIntegrity();
        return false;
    }

    @Override
    public void clear() {
        checkIntegrity();
    }



}