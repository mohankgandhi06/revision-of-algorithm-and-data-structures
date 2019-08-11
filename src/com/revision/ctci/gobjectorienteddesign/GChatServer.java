package com.revision.ctci.gobjectorienteddesign;

import java.util.HashMap;
import java.util.List;

public class GChatServer {
    public static void main(String[] args) {
        Thread chatterBox = new Thread(new ChatterBox());
        chatterBox.start();
    }
}

class ChatterBox implements Runnable {
    /* Account Login Module */
    /* Chat initialization Module
     *  - creating new chat group
     *  - selecting the friends to start a new chat session
     * Maintaining multiple Chat session
     *  - interfacing the Messaging Service
     *  - receiving and sending acknowledgement
     * Design Decisions
     * #1: We can have a local database and a one in the server level
     *     - Locally we can do the updates real time and we can update the server asynchronously
     * #2: Atomic updates when it comes to database updates (all or nothing) and there should
     *     be error handling in place when it happens
     * #3: Ajax update of the messages in UI */

    @Override
    public void run() {

    }
}

class CBAccount extends CBUser {
    private CBAvatar avatar;
    private String status;
    private HashMap<String, CBFriend> friends;
    private HashMap<String, CBChat> chats;

    public CBAccount(String id, String name, String mobile, String email) {
        super(id, name, mobile, email);
    }

    public void setAvatar(CBAvatar avatar) {
        this.avatar = avatar;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addFriend(CBFriend friend) {

    }

    public void addFriends(List<CBFriend> friends) {

    }

    public void createChat(List<CBFriend> receivers) {

    }

    public void removeChat(String chatId) {

    }

    public CBAvatar getAvatar() {
        return avatar;
    }

    public String getStatus() {
        return status;
    }

    public HashMap<String, CBFriend> getFriends() {
        return friends;
    }

    public HashMap<String, CBChat> getChats() {
        return chats;
    }
}

//AVATAR IMAGE chosen by the CBAccount user
class CBAvatar {
    private String image;

    public CBAvatar(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}

class CBFriend extends CBUser {
    public CBFriend(String id, String name, String mobile, String email) {
        super(id, name, mobile, email);
    }
}

/* MESSAGING SERVICE of the current user */
class CBChatOperator {
    /* This will be called as a AJAX request from the UI so that update happens and does not refresh
     * the page often */
    public void insertMessageInTheChatWindow(String id) {

    }

    public void sendMessage(List<CBUser> users, CBMessage message) {

    }

    /* ACKNOWLEDGEMENT for the received message */
    public void receiveAcknowledgement(String id) {

    }
}

/* CBChat based classes START */
class CBChat {
    private String id;//If numeric the combination will be over quickly as many chats will be present over the years
    private CBSender sender;//Encapsulation of only necessary details of the current CBAccount user
    /* List of all the other CBFriends to whom the message is sent.
     * Here we could have implemented a List alone but then inside the message we would
     * need to send the details of the CBSender every time. Now can the receiver can use
     * only the string to find out the user who the specific message by clicking on their
     * name and then fetch their details from their account if they are direct friends
     * of them or the number/email if not known */
    private HashMap<String, CBReceiver> receivers;
    private HashMap<String, CBMessage> messages;

    public CBChat(String accountId, CBSender sender, HashMap<String, CBReceiver> receivers) {
        this.id = prepareId(accountId, sender, receivers);//We have to careful for concurrent generation of id by other chats
        this.sender = sender;
        this.receivers = receivers;
    }

    private String prepareId(String accountId, CBSender sender, HashMap<String, CBReceiver> receivers) {
        return "";
    }

    public String getId() {
        return id;
    }

    public CBSender getSender() {
        return sender;
    }

    public HashMap<String, CBReceiver> getReceivers() {
        return receivers;
    }

    public HashMap<String, CBMessage> getMessages() {
        return messages;
    }
}

//CBAccount, CBFriend, CBSender and CBReceiver can implement their specific methods based on the need
class CBUser {
    private String id;
    private String name;
    private String mobile;
    private String email;

    public CBUser(String id, String name, String mobile, String email) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }
}

class CBSender extends CBUser {
    public CBSender(String id, String name, String mobile, String email) {
        super(id, name, mobile, email);
    }
}

class CBReceiver extends CBUser {
    public CBReceiver(String id, String name, String mobile, String email) {
        super(id, name, mobile, email);
    }
}

class CBMessage {
    private String id;//If numeric the combination will be over quickly as many chats will be present over the years
    private String senderName;//Only the name is required
    private String message;//Can be audio | video | document | plain text | emoticons

    public CBMessage(String senderName, String message, String chatId) {
        this.id = prepareId(senderName, message, chatId);//We have to careful for concurrent generation of id by other users
        this.senderName = senderName;
        this.message = message;
    }

    private String prepareId(String senderName, String message, String chatId) {
        return "";
    }

    public String getId() {
        return id;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessage() {
        return message;
    }
}
/* CBChat based classes END */