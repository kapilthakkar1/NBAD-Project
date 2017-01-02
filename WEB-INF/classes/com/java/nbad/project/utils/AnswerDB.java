/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.nbad.project.utils;

import com.java.nbad.project.entity.Answer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MadhaviBhat
 */
public class AnswerDB {

    public Answer addAnswer(Answer ans) throws ClassNotFoundException, SQLException {

        if (getAnswerByCodeEmail(ans.getsCode(), ans.getParticipantEmail())) {
            updateAnswer(ans);
        } else {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement ps = null;

            String query
                    = " INSERT INTO ANSWER (SCode, Email, Choice, DateSubmitted)"
                    + " VALUES (?, ?, ?, ?)";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, ans.getsCode());
                ps.setString(2, ans.getParticipantEmail());
                ps.setString(3, ans.getChoiceCd());
                ps.setTimestamp(4, new java.sql.Timestamp(ans.getDateSubmitted().getTime()));
                ps.executeUpdate();

            } finally {
                DBUtil.closePreparedStatement(ps);
            }
        }
        


        return ans;
    }
    
    
     public Answer updateAnswer(Answer ans) throws ClassNotFoundException, SQLException {

            Connection connection = DBConnection.getDBConnection();
            PreparedStatement ps = null;

            String query
                    = " update ANSWER set Choice = ?, DateSubmitted = ?"
                    + " where Scode = ? and Email = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, ans.getChoiceCd());
                ps.setTimestamp(2, new java.sql.Timestamp(ans.getDateSubmitted().getTime()));
                ps.setString(3, ans.getsCode());
                ps.setString(4, ans.getParticipantEmail());
                ps.executeUpdate();

            } finally {
                DBUtil.closePreparedStatement(ps);
            }
        return ans;
    }
    

    public Answer getAnswerByStudyCode(String stCode) throws ClassNotFoundException, SQLException {

        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
        Answer ansObj = null;

        String query
                = " Select Choice, Scode, Email"
                + " from ANSWER where "
                + " Scode = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, stCode);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet != null && resultSet.next()) {

                ansObj = new Answer();

                ansObj.setChoiceCd(resultSet.getString("Choice"));
                ansObj.setsCode(resultSet.getString("SCode"));
                ansObj.setParticipantEmail(resultSet.getString("Email"));

            }
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
        return ansObj;
    }

    public List<Answer> getAnswerByEmail(String email) throws ClassNotFoundException, SQLException {

        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
        List<Answer> ansList = null;

        String query
                = " Select Choice, Scode, Email"
                + " from ANSWER where "
                + " Email = ? ";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet != null && resultSet.next()) {
                ansList = new ArrayList<>();
                do { 
                Answer ansObj = new Answer();

                ansObj.setChoiceCd(resultSet.getString("Choice"));
                ansObj.setsCode(resultSet.getString("SCode"));
                ansObj.setParticipantEmail(resultSet.getString("Email"));
                ansList.add(ansObj);
                } while (resultSet.next());

            }
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
        return ansList;
    }

    
        private boolean getAnswerByCodeEmail(String stCode, String email) throws ClassNotFoundException, SQLException {

        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
        
        String query
                = " Select Choice, Scode, Email"
                + " from ANSWER where "
                + " Scode = ? and Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, stCode);
            ps.setString(2, email);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet != null && resultSet.next()) {

            return true;
            }
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
        return false;
    }
}
