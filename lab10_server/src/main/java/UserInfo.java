import java.util.ArrayList;

public class UserInfo {
    private Integer ID;
    private String name;
    private Boolean loggedIn;
    private ArrayList<Integer> friendsIDs;
    private ArrayList<String> messages;

    UserInfo(String nickname) {
        setName(nickname);
        setLoggedIn(false);
        setFriendsIDs(new ArrayList<>());
        setID(IDProvider.getNewID());
        setMessages(new ArrayList<>());
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public ArrayList<Integer> getFriendsIDs() {
        return friendsIDs;
    }

    public void setFriendsIDs(ArrayList<Integer> friendsIDs) {
        this.friendsIDs = friendsIDs;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        getMessages().add(message);
    }
}
