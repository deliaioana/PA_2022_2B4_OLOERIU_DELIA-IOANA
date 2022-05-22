import java.net.Socket;

public class FriendCommand {
    public synchronized void execute(String command, Socket socket, UserInfo currentUser){
        String[] friends = command.split(" ");
        for (String friendName : friends) {
            if(isRegistered(friendName)){
                addFriend(friendName, currentUser);
            }
        }

        MessageSender.send("Your new list of friend IDs is: " + currentUser.getFriendsIDs(), socket);
    }

    private synchronized boolean isRegistered(String nickname) {
        for (UserInfo user : Server.getUsers()) {
            if(user.getName().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    private synchronized void addFriend(String friendName, UserInfo currentUser) {
        Integer friendID = getIDFromName(friendName);

        if(friendID != null) {
            currentUser.getFriendsIDs().add(friendID);
        }
    }

    private Integer getIDFromName(String friendName) {
        for (UserInfo user : Server.getUsers()) {
            if(user.getName().equals(friendName)) {
                return user.getID();
            }
        }
        return null;
    }
}
