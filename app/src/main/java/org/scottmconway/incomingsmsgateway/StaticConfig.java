package org.scottmconway.incomingsmsgateway;

public class StaticConfig {
    public static final String URL = "http://192.168.29.252:8001/push"; // <-- set your real URL here
    public static final String TEMPLATE = "{\n" +
            "  \"msg\":\"%text%\",\n" +
            "  \"time\":%sentStamp%,\n" +
            "  \"in-sim\":\"%in-sim%\"\n" +
            "}";
    public static final String HEADERS = "{\"User-agent\":\"SMS Forwarder App\"}";
    public static final int RETRIES = 1;
    public static final boolean IGNORE_SSL = false;
    public static final boolean CHUNKED_MODE = true;
    public static final String SENDER = "*";
    public static final int SIM_SLOT = 0;
    public static final boolean SMS_ENABLED = true;
} 