/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.nbad.project.utils;

import com.java.nbad.project.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author MadhaviBhat
 */
public class TempUserDB {

    public void addTempUser(User newUser, String token, Date issueDate) throws SQLException, ClassNotFoundException {

        if (newUser != null) {

            Connection connection = DBConnection.getDBConnection();
            PreparedStatement ps = null;

            String query
                    = "INSERT INTO TEMPUSER (Email, FirstName, LastName, Password, Token, IssueDate) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, newUser.getEmail());
                ps.setString(2, newUser.getFirstName());
                ps.setString(3, newUser.getLastName());
                ps.setString(4, DBUtil.md5Passwd(newUser.getPaswd()));
                ps.setString(5, token);
                ps.setTimestamp(6, new java.sql.Timestamp(issueDate.getTime()));
                ps.executeUpdate();

            } finally {
                DBUtil.closePreparedStatement(ps);

            }

        }
    }

    public User getUserByToken(String token) throws ClassNotFoundException, SQLException {

        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
        User logedinUser = null;

        String query
                = " Select Email, FirstName, LastName, Password "
                + " from TEMPUSER where "
                + " Token = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet != null && resultSet.next()) {
                logedinUser = new User();
                logedinUser.setEmail(resultSet.getString("Email"));
                logedinUser.setFirstName(resultSet.getString("FirstName"));
                logedinUser.setLastName(resultSet.getString("LastName"));
                logedinUser.setPaswd(resultSet.getString("Password"));
            }

        } finally {
            DBUtil.closePreparedStatement(ps);
        }
        return logedinUser;
    }

    public void removeTempUserByToken(String token) throws ClassNotFoundException, SQLException {

        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;

        String query
                = " Delete from TEMPUSER where "
                + " Token = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, token);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }
}
