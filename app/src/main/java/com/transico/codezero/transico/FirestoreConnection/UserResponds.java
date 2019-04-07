package com.transico.codezero.transico.ReportLog;

import com.google.firebase.Timestamp;

public class UserResponds {

    //Variables
    private Timestamp postTime;
    private String respondText;
    private Integer respondCount;
    private String userID;
    private String userName;



    public UserResponds() {
    }

    public UserResponds(Timestamp postTime, String respondText, Integer respondCount, String userID, String userName) {
        this.postTime = postTime;
        this.respondText = respondText;
        this.respondCount = respondCount;
        this.userID = userID;
        this.userName = userName;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public String getRespondText() {
        return respondText;
    }

    public Integer getRespondCount() {
        return respondCount;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }
}
