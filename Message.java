public class Message {
    private String messageId;
    private String payload;
    private int retryCount;
    private int successChance;

    public Message(String messageId, String payload, int successChance) {
        this.messageId = messageId;
        this.payload = payload;
        this.retryCount = 0;
        this.successChance = successChance;
    }

    public int getSuccessChance() {
        return successChance;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void increaseRetryCount() {
        retryCount++;
    }

    @Override
    public String toString() {
        return "Message ID: " + messageId
                + ", Payload: " + payload
                + ", Retry Count: " + retryCount
                + ", Success Chance: " + successChance + "%";
    }
}
