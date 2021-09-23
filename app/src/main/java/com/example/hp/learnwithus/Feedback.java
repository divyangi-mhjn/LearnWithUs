package com.example.hp.learnwithus;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/26/2017.
 */@IgnoreExtraProperties
public class Feedback {
    private String feedbackId;
    private String feedbackfeed;
    private String feedbackemail;
    private String feedbacksmiley;
    public Feedback(){

    }

    public Feedback(String feedbackId, String feedbackfeed,String feedbackemail,String feedbacksmiley ) {
        this.feedbackId = feedbackId;
        this.feedbackfeed = feedbackfeed;
        this.feedbackemail=feedbackemail;
        this.feedbacksmiley=feedbacksmiley;

    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackfeed() {
        return feedbackfeed;
    }

    public void setFeedbackfeed(String feedbackfeed) {
        this.feedbackfeed = feedbackfeed;
    }

    public String getFeedbackemail() {
        return feedbackemail;
    }

    public void setFeedbackemail(String feedbackemail) {
        this.feedbackemail = feedbackemail;
    }

    public String getFeedbacksmiley() {
        return feedbacksmiley;
    }

    public void setFeedbacksmiley(String feedbacksmiley) {
        this.feedbacksmiley = feedbacksmiley;
    }



}