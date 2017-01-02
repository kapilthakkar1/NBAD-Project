/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.nbad.project.controller;

import com.java.nbad.project.entity.User;
import com.java.nbad.project.utils.TempUserDB;
import com.java.nbad.project.utils.UserDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MadhaviBhat
 */
public class UserController extends HttpServlet {

    private String url;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            url = "/home.jsp";

            HttpSession session = request.getSession();
            session.removeAttribute("currentUser");
            session.removeAttribute("theUser");
            session.invalidate();

        } else if ("activate".equals(action)) {
            url = "/login.jsp";
            HttpSession session = request.getSession();
            session.removeAttribute("currentUser");
            session.removeAttribute("theUser");

            String tok = request.getParameter("tk");
            if (tok != null) {
                TempUserDB tDb = new TempUserDB();
                UserDB uDb = new UserDB();

                try {
                    User tUser = tDb.getUserByToken(tok);
                    uDb.addNewUser(tUser);
                    tDb.removeTempUserByToken(tok);
                    request.setAttribute("message", "Account activated, please login to cotinue.");
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                request.setAttribute("message", "Error occured! Try again.");
            }

        } else if ("how".equals(action)) {

            HttpSession session = request.getSession();
            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else {
                url = "/how.jsp";
            }

        } else if ("about".equals(action)) {
            HttpSession session = request.getSession();
            if (session.getAttribute("theUser") != null) {
                url = "/aboutl.jsp";
            } else {
                url = "/about.jsp";
            }
        } else if ("home".equals(action)) {
            HttpSession session = request.getSession();
            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else {
                url = "/home.jsp";
            }
        } else if ("main".equals(action)) {
            HttpSession session = request.getSession();
            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else {
                url = "/login.jsp";
            }
        }
        else if ("resetnew".equals(action)) {

            String resTok = request.getParameter("tk");
            String resEmail = request.getParameter("email");

            UserDB ud = new UserDB();
            if (ud.isValidUserToken(resEmail, resTok)) {
                request.setAttribute("tmpPasword", resTok);
                request.setAttribute("resetEmail", resEmail);

                url = "/newpassword.jsp";

            } else {
                request.setAttribute("message", "Reset link expired, try again!");
                url = "/forgot.jsp";
            }

        }
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // perform action and set URL to appropriate page
        url = "/home.jsp";   //default page

        if ("login".equals(action)) {
            url = "/login.jsp";

            HttpSession session = request.getSession();
            session.removeAttribute("currentUser");
            session.removeAttribute("theUser");

            String loginEmail = request.getParameter("loginEmail");
            String loginPaswd = request.getParameter("loginPaswd");

            User currUser = isValidUser(loginEmail, loginPaswd);
            if (currUser != null) {
                url = "/main.jsp";    // the "join" page
                session.setAttribute("theUser", currUser);
                session.setAttribute("currentUser", currUser.getFirstName());
            } else {
                request.setAttribute("message", "Invalid email/password! Try again."); // Will be available as ${message}
            }
        } else if ("create".equals(action)) {
            url = "/signup.jsp";

            HttpSession session = request.getSession();
            session.removeAttribute("currentUser");
            session.removeAttribute("theUser");

            String signupEmail = request.getParameter("signupEmail");
            String signupPaswd = request.getParameter("signupPass");
            String signupCnfPaswd = request.getParameter("signupPassCnf");
            String fName = request.getParameter("fName");
            String lName = request.getParameter("lName");

            if (signupCnfPaswd.equals(signupPaswd)) {

                String errMsg = "Error occured try again";

                UserDB userDb = new UserDB();
                try {
                    if (userDb.getUserByEmail(signupEmail) == null) {

                        User tempUser = new User();
                        tempUser.setFirstName(fName);
                        tempUser.setLastName(lName);
                        tempUser.setPaswd(signupPaswd);
                        tempUser.setEmail(signupEmail);

                        String uuid = UUID.randomUUID().toString();

                        TempUserDB usDB = new TempUserDB();

                        usDB.addTempUser(tempUser, uuid, Calendar.getInstance().getTime());

                        sendActivateEmail(uuid, signupEmail, fName);

                        request.setAttribute("message", "Please activate your account, check your email");
                        url = "/login.jsp";

                    } else {

                        request.setAttribute("message", "User already exists.");
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    request.setAttribute("message", errMsg);
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                String errMsg = "Password doesn't match enter again.";
                request.setAttribute("message", errMsg);
            }

        }
        else if ("forgot".equals(action)) {

            String contEmail = request.getParameter("forgotEmail");
            UserDB uDb = new UserDB();
            User nUser = null;
            String uuid = null;
            try {
                nUser = uDb.getUserByEmail(contEmail);

                uuid = UUID.randomUUID().toString();
                uDb.updatePaswd(contEmail, uuid, null);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (nUser != null) {

                StringBuilder msg = new StringBuilder();

            //  String url1 = "http://localhost:8081/assignment1/UserController?action=resetnew&tk=";
             String url1 = "http://alchemist-mynbad5166.rhcloud.com/assignment1/UserController?action=resetnew&tk=";

                msg.append("Hello ").
                        append("! <br>Yor password has been reset. Please  click ").
                        append("<a href=\"").append(url1).
                        append(uuid).append("&email=").append(contEmail)
                        .append("\"> here</a>").
                        append(" to set new password.");

                sendEmail(contEmail, msg.toString(), "Reset Password-do not reply to this email");
                request.setAttribute("message", "Password has been reset, please check your email.");

                url = "/login.jsp";
            } else {
                request.setAttribute("message", "User not found! try again");
            }
        } 
        
        else if ("updateNewPas".equals(action)) {

            url = "/newpassword.jsp";
            String resTok = request.getParameter("tempPaswd");
            String resEmail = request.getParameter("resetEmail");
            
            String newPas = request.getParameter("newPaswd");
            String cnfNewPas = request.getParameter("cnfNewPaswd");
            
            if(newPas.equals(cnfNewPas)){
                
             UserDB uDb = new UserDB();
                try {
                    uDb.updatePaswd(resEmail, resTok, newPas);
                     request.setAttribute("message", "Password updated successfully, login!");
                     url = "/login.jsp";
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("tmpPasword", resTok);
                    request.setAttribute("resetEmail", resEmail);
                    request.setAttribute("message", "Invalid data! try again");
                }
            } else {
                request.setAttribute("tmpPasword", resTok);
                request.setAttribute("resetEmail", resEmail);
                request.setAttribute("message", "Password doesn't match! try again");
            }
            
        }
        
        else if ("contact".equals(action)) {
            if (request.getSession().getAttribute("theUser") != null) {

                String contName = request.getParameter("cName");
                String contEmail = request.getParameter("cEmail");
                String contMsg = request.getParameter("cMsg");

                StringBuilder msg = new StringBuilder();
                msg.append("Hello ").append(contName).append("!"
                        + "<br>Yor message ").append(contMsg).append(" has been recorded.").
                        append("<br>Our Team will contact you soon!");

                sendEmail(contEmail, msg.toString(), "Research Exchange Team-do not reply to this email");

                url = "/confirmc.jsp";
            } else {
                url = "/login.jsp";

            }
        } else if ("recommend".equals(action)) {

            if (request.getSession().getAttribute("theUser") != null) {

                String toName = request.getParameter("cName");
                String toEmail = request.getParameter("cEmail");
                String fromEmail = request.getParameter("fromEmail");
                String msgToFriend = request.getParameter("msg");

                StringBuilder msg = new StringBuilder();
                msg.append("Hello ").append(toName).append("!<br>Your friend ").
                        append(" has recommended you to join Research Exchange program.").
                        append("<br>Please click ").
                        append("<a href=\"http://alchemist-mynbad5166.rhcloud.com/assignment1/signup.jsp\">"
                                + "here</a>").append(" to create new account").
                        append("<br>Message from friend: ").append(msgToFriend);

                sendEmail(toEmail, msg.toString(), "Join Research Exchange Team-do not reply to this email");

                msg = new StringBuilder();
                msg.append("Hello!<br>You have successfully recommended your friend ").append(toName);
                sendEmail(fromEmail, msg.toString(), "Research Exchange Team-do not reply to this email");
                url = "/confirmr.jsp";
                
            }  else {
                url = "/login.jsp";

            }
        } 
        /*
         else if ("logout".equals(action)) {
         url = "/home.jsp";   
            
         HttpSession session = request.getSession();
         session.removeAttribute("currentUser");
            
         }
         */
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private User isValidUser(String loginEmail, String loginPaswd) {

        UserDB userDbObj = new UserDB();

        try {
            return userDbObj.validateUserLogin(loginEmail, loginPaswd);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    private void sendEmail(String toEmail, String msgBody, String msgSubject) {

        String from = "nbadproject@gmail.com";
        String pass = "nbad5166";
        // Recipient's email ID needs to be mentioned.

        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));
            message.setSubject(msgSubject);
            message.setText(msgBody, "UTF-8", "html");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    private void sendActivateEmail(String uuid, String signupEmail, String contName) {

        StringBuilder msg = new StringBuilder();

    //  String url1 = "http://localhost:8081/assignment1/UserController?action=activate&tk=";
        String url1 = "http://alchemist-mynbad5166.rhcloud.com/assignment1/UserController?action=activate&tk=";

        msg.append("Hello ").append(contName).
                append("! <br>Yor account has been created. Please  click ").
                append("<a href=\"").append(url1).
                append(uuid).append("\"> here</a>").
                append(" to activate your account");
        sendEmail(signupEmail, msg.toString(), "New Account activate -Research Exchange Team-do not reply");
    }
}
