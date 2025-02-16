package org.scottmconway.incomingsmsgateway;

public class WebhookMessage {
    public String senderPhoneNumber;
    public String senderName;
    public int simSlotId;
    public String simSlotName;
    public String messageContent;
    public long timestamp;


    // Parameterized constructor
    public WebhookMessage(String senderPhoneNumber, String senderName, int simSlotId, String simSlotName, String messageContent, long timestamp) {
        this.senderName = senderName;
        this.simSlotId = simSlotId;
        this.simSlotName = simSlotName;
        this.messageContent = messageContent;
        this.timestamp = timestamp;

        if (senderPhoneNumber == null) {
            this.senderPhoneNumber = "";
        }
        else this.senderPhoneNumber = senderPhoneNumber;
    }
}
