package pl.wsb.fitnesstracker.training.internal;

public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    private final String displayName;

    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ActivityType fromString(String text) {
        for (ActivityType type : ActivityType.values()) {
            if (type.getDisplayName().equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown activity type: " + text);
    }
}
