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
public class Answer implements Serializable{
    
    private String participantEmail;
    
    private String sCode;
    
    private String choiceCd;
    
    private Date dateSubmitted;
    
   

    public Answer() {
    }
    
    

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public String getChoiceCd() {
        return choiceCd;
    }

    public void setChoiceCd(String choiceCd) {
        this.choiceCd = choiceCd;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }
}