/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.nbad.project.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author MadhaviBhat
 */
public class Study implements Serializable {

    private String name;
    private String code;
    private Date dateCreated;
    private String emailCreator;
    private String question;
    private String imageURL;
    private int requestedParticipants;
    private int noOfParticipants;
    private String studyDesc;
    private String studyStatus;
    private String[] answers;
    private String answerChoices;
    private double average;

    private double minimum;

    private double maximum;

    private double stdDeviation;

    public Study() {
    }

    public Study(String name, String code, Date dateCreated, String emailCreator, String question, String imageURL, int requestedParticipants, int noOfParticipants, String studyDesc, String studyStatus, String[] answers) {
        this.name = name;
        this.code = code;
        this.dateCreated = dateCreated;
        this.emailCreator = emailCreator;
        this.question = question;
        this.imageURL = imageURL;
        this.requestedParticipants = requestedParticipants;
        this.noOfParticipants = noOfParticipants;
        this.studyDesc = studyDesc;
        this.studyStatus = studyStatus;
        this.answers = answers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmailCreator() {
        return emailCreator;
    }

    public void setEmailCreator(String emailCreator) {
        this.emailCreator = emailCreator;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getRequestedParticipants() {
        return requestedParticipants;
    }

    public void setRequestedParticipants(int requestedParticipants) {
        this.requestedParticipants = requestedParticipants;
    }

    public int getNoOfParticipants() {
        return noOfParticipants;
    }

    public void setNoOfParticipants(int noOfParticipants) {
        this.noOfParticipants = noOfParticipants;
    }

    public String getStudyDesc() {
        return studyDesc;
    }

    public void setStudyDesc(String studyDesc) {
        this.studyDesc = studyDesc;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
    
    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public double getStdDeviation() {
        return stdDeviation;
    }

    public void setStdDeviation(double stdDeviation) {
        this.stdDeviation = stdDeviation;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(String answerChoices) {
        this.answerChoices = answerChoices;
    }
}
