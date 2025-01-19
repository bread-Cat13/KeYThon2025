package keython.mandalart.goal;

public enum GoalRetrieveType {
    ALL, FinalMain, MainSub;

    public static GoalRetrieveType fromString(String value) {
        for (GoalRetrieveType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid GoalRetrieveType: " + value);
    }
}
