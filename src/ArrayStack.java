import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack <E extends Cloneable> implements Stack <E>  {
    private final int maxCapacity;
    private int topStack=0;
    private Cloneable[] data;

    @SuppressWarnings("negative capacity")
    public ArrayStack(int maxCapacity) throws NegativeCapacityException {
        if (maxCapacity < 0)
            throw new NegativeCapacityException("Negative capacity is not allowed");
        this.maxCapacity = maxCapacity;
    }

    @Override
    @SuppressWarnings("no space")
    public void push(Cloneable element) throws StackOverflowException {
        if (topStack == maxCapacity)
            throw new StackOverflowException("There`s no space - Stack is full");

        data[topStack] = element;
        topStack++;

    }

    @Override
    @SuppressWarnings("empty stack")
    public E pop() throws EmptyStackException {
        if (this.isEmpty())
            throw new EmptyStackException("The stack is empty, there is nothing to remove");

        topStack--;
        return (E) data[topStack];

    }

    @Override
    public E peek() {
        if (this.isEmpty())
            throw new EmptyStackException("The stack is empty, there is nothing to see");
        return (E) data[topStack];
    }

    @Override
    public int size() {
        return topStack;
    }

    @Override
    public boolean isEmpty() {
        return topStack ==0;
    }

    public ArrayStack<E> clone() {
        ArrayStack<E> copyStack = new ArrayStack<E>(this.maxCapacity); // allocating new stack in the same size
        copyStack.data = data.clone(); // the values of copyStack.data are references for the values in data

        for (int i = 0; i < data.length; i++) {
            copyStack.data[i] = data[i]; // copy the values from data to copyStack.data
        }
        return copyStack; // return the cloned stack
    }

    @Override
    public Iterator iterator() {
        return new ArrayStackIterator;
    }

    private class ArrayStackIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return topStack != 0;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException("There is no such element");
            E element = (E) data[topStack];
            topStack--;
            return element;
        }
    }
}
