package mineverse.Aust1n46.chat.channel;

public class ChatFormat {
    private final int priority;
    private final String format;

    public ChatFormat(int priority, String format) {
        this.priority = priority;
        this.format = format;
    }

    public int getPriority() {
        return priority;
    }

    public String getFormat() {
        return format;
    }
}
