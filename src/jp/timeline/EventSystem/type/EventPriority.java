package jp.timeline.EventSystem.type;

public enum EventPriority {
    HIGHEST("Highest", 0),
    HIGH("High", 1),
    MEDIUM("Medium", 2),
    LOW("Low", 3),
    LOWEST("Lowest", 4);

    private final String name;
    private final byte number;

    EventPriority(String name, int number){
        this.name = name;
        this.number = (byte) number;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public byte getByte() {
        return this.number;
    }
}
