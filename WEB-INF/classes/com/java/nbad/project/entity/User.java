/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.nbad.project.entity;

import java.io.Serializable;

/**
 *
 * @author MadhaviBhat
 */
public class User implements Serializable{
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private int coins;
    
    private int participants;
    
    private int participation;
    
    private int studies;
    
    private String paswd;

    public User() {
    }    

    public User(String firstName, String lastName, String email, int coins, int participants, int participation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.coins = coins;
        this.participants = participants;
        this.participation = participation;
    }
    
    public User(String firstName, String lastName, String email, int coins, int participants, int participation, String passwd) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.coins = coins;
        this.participants = participants;
        this.participation = participation;
        this.paswd = passwd;
    
    }
    
    

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * @param coins the coins to set
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * @return the participants
     */
    public int getParticipants() {
        return participants;
    }

    /**
     * @param participants the participants to set
     */
    public void setParticipants(int participants) {
        this.participants = participants;
    }

    /**
     * @return the participation
     */
    public int getParticipation() {
        return participation;
    }

    /**
     * @param participation the participation to set
     */
    public void setParticipation(int participation) {
        this.participation = participation;
    }

    public String getPaswd() {
        return paswd;
    }

    public void setPaswd(String paswd) {
        this.paswd = paswd;
    }

    public int getStudies() {
        return studies;
    }

    public void setStudies(int studies) {
        this.studies = studies;
    }
   
}
