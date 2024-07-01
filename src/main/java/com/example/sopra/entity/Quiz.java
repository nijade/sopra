package com.example.sopra.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Quiz")
public class Quiz {

    @Id
    @GeneratedValue
    private Integer quizID;

    public Quiz(){

    }

    private boolean isChosen;

    @Lob
    private String questionOne;
    private String rightAnswersQuestionOne;
    private List<String> possibleAnswersQuestionOne;


    @Lob
    private String questionTwo;
    private String rightAnswersQuestionTwo;
    private List<String> possibleAnswersQuestionTwo;


    @Lob
    private String questionThree;
    private String rightAnswersQuestionThree;
    private List<String> possibleAnswersQuestionThree;

    public void setIsChosen(boolean chosen){
        this.isChosen = chosen;

    }

    public boolean getIsChosen(){
        return isChosen;

    }

    public void setQuizID(Integer quizID) {
        this.quizID = quizID;
    }

    public Integer getQuizID(){return quizID;}

    // Getter und Setter für questionOne
    public String getQuestionOne() {
        return questionOne;
    }

    public void setQuestionOne(String questionOne) {
        this.questionOne = questionOne;
    }

    // Getter und Setter für rightAnswersQuestionOne
    public String getRightAnswersQuestionOne() {
        return rightAnswersQuestionOne;
    }

    public void setRightAnswersQuestionOne(String rightAnswersQuestionOne) {
        this.rightAnswersQuestionOne = rightAnswersQuestionOne;
    }

    // Getter und Setter für possibleAnswersQuestionOne
    public List<String> getPossibleAnswersQuestionOne() {
        return possibleAnswersQuestionOne;
    }

    public void setPossibleAnswersQuestionOne(List<String> possibleAnswersQuestionOne) {
        this.possibleAnswersQuestionOne = possibleAnswersQuestionOne;
    }

    // Getter und Setter für questionTwo
    public String getQuestionTwo() {
        return questionTwo;
    }

    public void setQuestionTwo(String questionTwo) {
        this.questionTwo = questionTwo;
    }

    // Getter und Setter für rightAnswersQuestionTwo
    public String getRightAnswersQuestionTwo() {
        return rightAnswersQuestionTwo;
    }

    public void setRightAnswersQuestionTwo(String rightAnswersQuestionTwo) {
        this.rightAnswersQuestionTwo = rightAnswersQuestionTwo;
    }

    // Getter und Setter für possibleAnswersQuestionTwo
    public List<String> getPossibleAnswersQuestionTwo() {
        return possibleAnswersQuestionTwo;
    }

    public void setPossibleAnswersQuestionTwo(List<String> possibleAnswersQuestionTwo) {
        this.possibleAnswersQuestionTwo = possibleAnswersQuestionTwo;
    }

    // Getter und Setter für questionThree
    public String getQuestionThree() {
        return questionThree;
    }

    public void setQuestionThree(String questionThree) {
        this.questionThree = questionThree;
    }

    // Getter und Setter für rightAnswersQuestionThree
    public String getRightAnswersQuestionThree() {
        return rightAnswersQuestionThree;
    }

    public void setRightAnswersQuestionThree(String rightAnswersQuestionThree) {
        this.rightAnswersQuestionThree = rightAnswersQuestionThree;
    }

    // Getter und Setter für possibleAnswersQuestionThree
    public List<String> getPossibleAnswersQuestionThree() {
        return possibleAnswersQuestionThree;
    }

    public void setPossibleAnswersQuestionThree(List<String> possibleAnswersQuestionThree) {
        this.possibleAnswersQuestionThree = possibleAnswersQuestionThree;
    }


}
