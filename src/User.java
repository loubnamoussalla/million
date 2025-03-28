public class User {
    String name;
    boolean userModeSelection;

    public User(String name, boolean userModeSelection) {
        this.name = name;
        this.userModeSelection = userModeSelection;
    }

    public boolean gertUserModeSelection() {
        return userModeSelection;
    }

    public void setUserModeSelection(boolean userModeSelection) {
        this.userModeSelection = userModeSelection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
