import java.util.Iterator;

public class ArrayStack <E extends Cloneable> implements Stack <E>  {
    private final int maxCapacity;
    private int topStack=-1;
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
        if (topStack == maxCapacity - 1)
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
        return topStack ==-1;
    }

    @Override
    public Stack clone() {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
