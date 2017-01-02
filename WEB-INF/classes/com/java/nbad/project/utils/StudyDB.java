/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.nbad.project.utils;

import com.java.nbad.project.entity.Study;
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
public class StudyDB {


    public StudyDB() {

     
    }
    public List<Study> getStudyListByEmail(String email) throws ClassNotFoundException, SQLException {
    
        
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
        List<Study> stList = new ArrayList<>();
         
        
        String query
                = " Select SName, SCode, Description, Question, ImageURL, ReqParticipants, "+
                  " ActParticipants , SStatus, AnsChoices, Email"
                + " from STUDY where "
                + " Email = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet != null && resultSet.next()) {
                do {

                    Study stObj = new Study();
                    //logedinUser.setUserId(resultSet.getInt("user_id"));
                    stObj.setName(resultSet.getString("SName"));
                    stObj.setCode(resultSet.getString("SCode"));
                    stObj.setStudyDesc(resultSet.getString("Description"));
                    stObj.setQuestion(resultSet.getString("Question"));
                    stObj.setImageURL(resultSet.getString("ImageURL"));
                    stObj.setStudyStatus(resultSet.getString("SStatus"));
                    stObj.setNoOfParticipants(resultSet.getInt("ActParticipants"));
                    stObj.setRequestedParticipants(resultSet.getInt("ReqParticipants"));
                    stObj.setAnswerChoices(resultSet.getString("AnsChoices"));
                    stObj.setEmailCreator(resultSet.getString("Email"));
                    
                    stList.add(stObj);
                } while (resultSet.next());
            }
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
        return stList;
    }

    

    public List<Study> getOtherStudyList(String email) throws ClassNotFoundException, SQLException {
        
        
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
        List<Study> stList = new ArrayList<>();
         
        
        String query
                = " Select SName, SCode, Description, Question, ImageURL, ReqParticipants,"+
                  " ActParticipants , SStatus, AnsChoices, Email"
                + " from STUDY where "
                + " Email != ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet != null && resultSet.next()) {
                do {

                    Study stObj = new Study();
                    //logedinUser.setUserId(resultSet.getInt("user_id"));
                    stObj.setName(resultSet.getString("SName"));
                    stObj.setCode(resultSet.getString("SCode"));
                    stObj.setStudyDesc(resultSet.getString("Description"));
                    stObj.setQuestion(resultSet.getString("Question"));
                    stObj.setImageURL(resultSet.getString("ImageURL"));
                    stObj.setStudyStatus(resultSet.getString("SStatus"));
                    stObj.setNoOfParticipants(resultSet.getInt("ActParticipants"));
                    stObj.setRequestedParticipants(resultSet.getInt("ReqParticipants"));
                    stObj.setAnswerChoices(resultSet.getString("AnsChoices"));
                    stObj.setEmailCreator(resultSet.getString("Email"));
                    stList.add(stObj);
                } while (resultSet.next());
            }
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
        return stList;

        
    }

    public Study getStudyByCode(String stCode) throws ClassNotFoundException, SQLException {

        
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
        Study stObj = null;
         
        
        String query
                = " Select SName, SCode, Description, Question, ImageURL, ReqParticipants,"+
                  " ActParticipants , SStatus, AnsChoices, Email"
                + " from STUDY where "
                + " SCode = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, stCode);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet != null && resultSet.next()) {
                
                    stObj = new Study();
                    
                    //logedinUser.setUserId(resultSet.getInt("user_id"));
                    stObj.setName(resultSet.getString("SName"));
                    stObj.setCode(resultSet.getString("SCode"));
                    stObj.setStudyDesc(resultSet.getString("Description"));
                    stObj.setQuestion(resultSet.getString("Question"));
                    stObj.setImageURL(resultSet.getString("ImageURL"));
                    stObj.setStudyStatus(resultSet.getString("SStatus"));
                    stObj.setNoOfParticipants(resultSet.getInt("ActParticipants"));
                    stObj.setRequestedParticipants(resultSet.getInt("ReqParticipants"));
                    stObj.setAnswerChoices(resultSet.getString("AnsChoices"));
                    stObj.setEmailCreator(resultSet.getString("Email"));
                    
            }
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
        return stObj;
    }
    
    
    public Study addStudy(Study study) throws ClassNotFoundException, SQLException {

        
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
      
        String query
                = " INSERT INTO STUDY (SName, SCode, Description, Question, ImageURL, ReqParticipants, "+
                  " SStatus, Email, DateCreated, AnsChoices)"
                
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getName());
            ps.setString(2, study.getCode());
            ps.setString(3, study.getStudyDesc());
            ps.setString(4, study.getQuestion());
            ps.setString(5, study.getImageURL());
            ps.setInt(6, study.getRequestedParticipants());
            ps.setString(7, study.getStudyStatus());
            ps.setString(8, study.getEmailCreator());
            ps.setTimestamp(9, new java.sql.Timestamp(study.getDateCreated().getTime()));
            ps.setString(10, study.getAnswerChoices());
            ps.executeUpdate();
            
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
        
        return study;
    }
    
    public Study updateStudy(Study study) throws ClassNotFoundException, SQLException {

        
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
      
        String query
                = " Update STUDY set SName = ?,  Description = ?, Question = ?, ImageURL = ?, "
                + " ReqParticipants = ?, SStatus = ?, AnsChoices = ?, ActParticipants = ? "+
                  "  where SCode = ?";
                
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getName());
            ps.setString(2, study.getStudyDesc());
            ps.setString(3, study.getQuestion());
            ps.setString(4, study.getImageURL());
            ps.setInt(5, study.getRequestedParticipants());
            ps.setString(6, study.getStudyStatus());
            ps.setString(7, study.getAnswerChoices());
            ps.setInt(8, study.getNoOfParticipants());
            ps.setString(9, study.getCode());
            ps.executeUpdate();
            
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
        
        return study;
    }
}
