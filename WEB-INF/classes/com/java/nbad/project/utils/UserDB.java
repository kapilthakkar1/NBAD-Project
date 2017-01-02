/*stat
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MadhaviBhat
 */
public class UserDB {

    public UserDB() {

    }

    public User getUserByEmail(String email) throws ClassNotFoundException, SQLException {

        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
        User logedinUser = null;

        String query
                = " Select Email, FirstName, LastName, Studies,"
                + " Participation, Coins , Participant"
                + " from USER where "
                + " Email = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet != null && resultSet.next()) {
                logedinUser = new User();
                //logedinUser.setUserId(resultSet.getInt("user_id"));
                logedinUser.setEmail(resultSet.getString("Email"));
                logedinUser.setFirstName(resultSet.getString("FirstName"));
                logedinUser.setLastName(resultSet.getString("LastName"));
                logedinUser.setParticipation(resultSet.getInt("Participation"));
                logedinUser.setCoins(resultSet.getInt("Coins"));
                logedinUser.setStudies(resultSet.getInt("Studies"));
                logedinUser.setParticipants(resultSet.getInt("Participant"));
            }

        } finally {
            DBUtil.closePreparedStatement(ps);

        }

        return logedinUser;
    }

    public User validateUserLogin(String email, String passwd) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
        User logedinUser = null;

        String query
                = " Select Email, FirstName, LastName, Studies,"
                + " Participation, Coins , Participant"
                + " from USER where "
                + " Email = ? and Password = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, DBUtil.md5Passwd(passwd));

            ResultSet resultSet = ps.executeQuery();

            if (resultSet != null && resultSet.next()) {
                logedinUser = new User();
                //logedinUser.setUserId(resultSet.getInt("user_id"));
                logedinUser.setEmail(resultSet.getString("Email"));
                logedinUser.setFirstName(resultSet.getString("FirstName"));
                logedinUser.setLastName(resultSet.getString("LastName"));
                logedinUser.setParticipation(resultSet.getInt("Participation"));
                logedinUser.setCoins(resultSet.getInt("Coins"));
                logedinUser.setStudies(resultSet.getInt("Studies"));
                logedinUser.setParticipants(resultSet.getInt("Participant"));
            }

        } finally {
            DBUtil.closePreparedStatement(ps);

        }

        return logedinUser;

    }

    public User addNewUser(User newUser) throws SQLException, ClassNotFoundException {

        if (newUser != null) {

            Connection connection = DBConnection.getDBConnection();
            PreparedStatement ps = null;

            String query
                    = "INSERT INTO USER (Email, FirstName, LastName, Password) "
                    + "VALUES (?, ?, ?, ?)";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, newUser.getEmail());
                ps.setString(2, newUser.getFirstName());
                ps.setString(3, newUser.getLastName());
                ps.setString(4, newUser.getPaswd());
                //ps.setInt(5, newUser.getCoins());
                //ps.setInt(6, newUser.getParticipants());
                ps.executeUpdate();

            } finally {
                DBUtil.closePreparedStatement(ps);

            }

        }
        return newUser;
    }

    public User updateParticipation(User newUser) throws SQLException, ClassNotFoundException {

        if (newUser != null) {

            Connection connection = DBConnection.getDBConnection();
            PreparedStatement ps = null;

            String query
                    = " update USER set Participation = ?, Coins = ?, Studies = ?, Participant = ? "
                    + " where Email = ? ";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, newUser.getParticipation());
                ps.setInt(2, newUser.getCoins());
                ps.setInt(3, newUser.getStudies());
                ps.setInt(4, newUser.getParticipants());
                ps.setString(5, newUser.getEmail());
                ps.executeUpdate();

            } finally {
                DBUtil.closePreparedStatement(ps);

            }
        }
        return newUser;
    }

    public void updatePaswd(String email, String tempPasword, String newPassword) 
            throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;

        try {

            if (newPassword == null) {
                String query = " update USER set TPassword = ? "
                        + " where Email = ? ";
                ps = connection.prepareStatement(query);
                ps.setString(1, tempPasword);
                ps.setString(2, email);
                 ps.executeUpdate();
            } else {
                String query = " update USER set Password = ? "
                        + " where Email = ? and TPassword = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, DBUtil.md5Passwd(newPassword));
                ps.setString(2, email);
                ps.setString(3, tempPasword);
            
                ps.executeUpdate();

            
                query = " update USER set TPassword = null "
                        + " where Email = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, email);
                ps.executeUpdate();
            }

           

        } finally {
            DBUtil.closePreparedStatement(ps);

        }
    }

    public boolean isValidUserToken(String email, String tkn) {

        PreparedStatement ps = null;
        try {

            Connection connection = DBConnection.getDBConnection();
            

            String query
                    = " Select Email "
                    + " from USER where "
                    + " Email = ? and TPassword = ?";

            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, tkn);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet != null && resultSet.next()) {
                return true;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
        return false;
    }
    
}
