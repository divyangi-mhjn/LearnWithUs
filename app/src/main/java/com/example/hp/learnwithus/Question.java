
package com.example.hp.learnwithus;
        import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties

public class Question {
    private int ID;
    private String question;
    private String choicea;
    private String choiceb;
    private String choicec;
    private String answer;

    public Question(){

        //this constructor is required
    }

    public Question(String question, String choicea, String choiceb,String choicec, String answer) {
        this.answer = answer;
        this.question = question;
        this.choicea = choicea;
        this.choiceb = choiceb;
        this.choicec = choicec;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoicea() {
        return choicea;
    }

    public void setChoicea(String choicea) {
        this.choicea = choicea;
    }

    public String getChoiceb() {
        return choiceb;
    }

    public void setChoiceb(String choiceb) {
        this.choiceb = choiceb;
    }

    public String getChoicec() {
        return choicec;
    }

    public void setChoicec(String choicec) {
        this.choicec = choicec;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }



    public int isCorrectAnswer(String selectedAnswer)
    {
        return (selectedAnswer.compareTo(answer));
    }

}

