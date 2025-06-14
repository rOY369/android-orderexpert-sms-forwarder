package org.scottmconway.incomingsmsgateway;

public class WebhookMessage {
    public String messageType;
    public String senderPhoneNumber;
    public String senderName;
    public int simSlotId;
    public String simSlotName;
    public String messageContent;
    public long timestamp;
    public String simNumber;

    // Parameterized constructor
    public WebhookMessage(String messageType, String senderPhoneNumber, String senderName, int simSlotId, String simSlotName, String messageContent, long timestamp, String simNumber) {
        this.messageType = messageType;
        this.senderName = senderName;
        this.simSlotId = simSlotId;
        this.simSlotName = simSlotName;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
        this.simNumber = simNumber;

        if (senderPhoneNumber == null) {
            this.senderPhoneNumber = "";
        }
        else this.senderPhoneNumber = senderPhoneNumber;
    }
}
