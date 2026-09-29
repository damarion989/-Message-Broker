import java.util.Scanner;

public class ReliableMessageBroker {
    public static void main(String[] args) {
        Broker broker = new Broker();
        Scanner input = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("\n--- Reliable Message Broker ---");
            System.out.println("1. Enqueue a new message");
            System.out.println("2. Process current batch");
            System.out.println("3. View and clear Dead-Letter Queue");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(input.nextLine());

                if (choice == 1) {
                    System.out.print("Message ID: ");
                    String id = input.nextLine();

                    System.out.print("Payload: ");
                    String payload = input.nextLine();

                    System.out.print("Success chance (0-100): ");
                    int successChance = Integer.parseInt(input.nextLine());

                    Message message = new Message(id, payload, successChance);
                    broker.addMessage(message);

                } else if (choice == 2) {
                    broker.processBatch();

                } else if (choice == 3) {
                    broker.viewAndClearDeadLetterQueue();

                } else if (choice == 4) {
                    System.out.println("Goodbye.");

                } else {
                    System.out.println("Choose 1 through 4.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }

        input.close();
    }
}
