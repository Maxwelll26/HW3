import java.util.Iterator;
/**
 * A generic stack implementation using an array.
 *
 * @param <E> the type of elements in the stack
 */
public class ArrayStack<E extends Cloneable> implements Stack<E> {
    private int maxSize;
    private Object[] stackArray;
    private int top;
    /**
     * Constructs an empty stack with the specified capacity.
     *
     * @param capacity the maximum capacity of the stack
     * @throws NegativeCapacityException if the specified capacity is negative
     */
    public ArrayStack(int capacity) throws NegativeCapacityException {
        if (capacity < 0)
            throw new NegativeCapacityException("Capacity can't be negative");
        this.maxSize = capacity;
        this.stackArray = new Object[maxSize];
        this.top = -1;
    }
    /**
     * Adds an element to the top of the stack.
     *
     * @param element the element to be added
     * @throws StackOverflowException if the stack is full
     */
    @Override
    public void push(E element) throws StackOverflowException {
        if (top == maxSize - 1)
            throw new StackOverflowException("Stack is full");
        ++top;
        stackArray[top] = element;
    }
    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty");
        E element = (E) stackArray[top];
        stackArray[top] = null;
        --top;
        return element;
    }
    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty");
        return (E) stackArray[top];
    }
    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return top + 1;
    }
    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }
    /**
     * Creates a deep copy of the stack.
     *
     * @return a new stack that is a deep copy of this stack
     */
    @Override
    public ArrayStack<E> clone() {
        ArrayStack<E> newStack = new ArrayStack<>(maxSize);
        for (int i = 0; i <= top; i++) {
            try {
                E element = (E) stackArray[i].getClass().getMethod("clone").invoke(stackArray[i]);
                newStack.setElement(element, i);
            } catch (Exception e) {
                return null;
            }
        }
        newStack.top = this.top;
        return newStack;
    }
    /**
     * Sets the specified element at the given index in the stack array.
     *
     * @param element the element to be set
     * @param index   the index at which to set the element
     */
    private void setElement(E element, int index) {
        stackArray[index] = element;
    }
    /**
     * Returns an iterator over the elements in the stack.
     *
     * @return an iterator over the elements in the stack
     */
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }
    /**
     * An iterator implementation for iterating over the elements in the stack.
     */
    private class StackIterator implements Iterator<E> {
        private int currIndex = top;

        @Override
        public boolean hasNext() {
            return currIndex >= 0;
        }

        @Override
        public E next() {
            E element = (E) stackArray[currIndex];
            --currIndex;
            return element;
        }
    }
}
