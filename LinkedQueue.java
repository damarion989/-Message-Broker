public class LinkedQueue<T> implements QueueInterface<T> {
    private Node<T> front;
    private Node<T> rear;
    private int count;

    public LinkedQueue() {
        front = null;
        rear = null;
        count = 0;
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {
        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }

        rear = newNode;
        count++;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException("Queue is empty.");
        }

        T result = front.data;
        front = front.next;
        count--;

        if (isEmpty()) {
            rear = null;
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
