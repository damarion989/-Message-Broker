import java.util.Random;

public class Broker {
    private static final int MAX_RETRIES = 3;

    private QueueInterface<Message> messageQueue;
    private QueueInterface<Message> deadLetterQueue;
    private Random random;

    public Broker() {
        messageQueue = new LinkedQueue<>();
        deadLetterQueue = new LinkedQueue<>();
        random = new Random();
    }

    public void addMessage(Message message) {
        try {
            messageQueue.enqueue(message);
            System.out.println("Enqueued: " + message);
        } catch (QueueOverflowException e) {
            System.out.println("Could not enqueue message.");
        }
    }

    public void processBatch() {
        if (messageQueue.isEmpty()) {
            System.out.println("No messages to process.");
            return;
        }

        try {
            while (!messageQueue.isEmpty()) {
                Message message = messageQueue.dequeue();

                if (random.nextInt(100) < message.getSuccessChance()) {
                    System.out.println("SUCCESS: " + message);
                } else {
                    message.increaseRetryCount();

                    if (message.getRetryCount() >= MAX_RETRIES) {
                        deadLetterQueue.enqueue(message);
                        System.out.println("MOVED TO DLQ: " + message);
                    } else {
                        messageQueue.enqueue(message);
                        System.out.println("FAILED - Re-enqueued: " + message);
                    }
                }
            }
        } catch (QueueUnderflowException | QueueOverflowException e) {
            System.out.println("Queue error: " + e.getMessage());
        }
    }

    public void viewAndClearDeadLetterQueue() {
        if (deadLetterQueue.isEmpty()) {
            System.out.println("The Dead-Letter Queue is empty.");
            return;
        }

        System.out.println("\nDead-Letter Queue:");

        try {
            while (!deadLetterQueue.isEmpty()) {
                System.out.println(deadLetterQueue.dequeue());
            }
        } catch (QueueUnderflowException e) {
            System.out.println("Queue error.");
        }
    }
}
