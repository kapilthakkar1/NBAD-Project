/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.nbad.project.controller;

import com.java.nbad.project.entity.Answer;
import com.java.nbad.project.entity.Study;
import com.java.nbad.project.entity.User;
import com.java.nbad.project.utils.AnswerDB;
import com.java.nbad.project.utils.StudyDB;
import com.java.nbad.project.utils.UserDB;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MadhaviBhat
 */
public class StudyController extends HttpServlet {

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

        HttpSession session = request.getSession();

        if (session.getAttribute("theUser") != null) {

            String action = request.getParameter("action");
            User thisUser = (User)session.getAttribute("theUser");
            List<Study> sList = null;
            StudyDB stDb = new StudyDB();
            UserDB uDb = new UserDB();
            if (request.getAttribute("studyList") == null) {
            
                try {
                    sList = stDb.getStudyListByEmail(thisUser.getEmail());
                    request.setAttribute("studyList", sList);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                }
                

            } else {
                sList = (List<Study>) request.getAttribute("studyList");
            }

            // perform action and set URL to appropriate page
            url = "/home.jsp";   //default page

            if ("participate".equals(action)) {

                String studyCode = request.getParameter("studyCode");

                if (studyCode == null) {
                                     
                    
                    if (request.getAttribute("partList") == null) {

                        try {
                            List<Study> partList = stDb.getOtherStudyList(thisUser.getEmail());
                            request.setAttribute("partList", partList);
                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    url = "/participate.jsp";
                } else {

                    /*
                     Retrieves the study record (question) from the DB.
                     Puts it in the http request object.
                     */
                    Study thisStudy;
                    try {
                        thisStudy = stDb.getStudyByCode(studyCode);
                        
                        if(thisStudy.getAnswerChoices().contains(";")){
                          String [] ansChoice = thisStudy.getAnswerChoices().split(";");
                          thisStudy.setAnswers(ansChoice);
                          request.setAttribute("thisStudy", thisStudy);
                        }
                        
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    url = "/question.jsp";
                }

            } else if ("myStudies".equals(action)) {

                url = "/studies.jsp";

            } else if ("edit".equals(action)) {

                String studyCode = request.getParameter("studyCode");

                if (studyCode != null) {

                    for (Study s : sList) {
                        if (s.getCode().equals(studyCode)) {

                            request.setAttribute("edtStudy", s);
                            break;
                        }
                    }
                    url = "/editstudy.jsp";
                } else {

                    url = "/login.jsp";
                }

            } else if ("update".equals(action)) {

                String studyCode = request.getParameter("studyCode");
                String sname = request.getParameter("newStudyName");
                String qtext = request.getParameter("studyQues");
                String part = request.getParameter("noOfParticip");
                String desc = request.getParameter("desc");
                String answerChoices = request.getParameter("studyAns");

                for (Study s : sList) {
                    if (s.getCode().equals(studyCode)) {
                        s.setName(sname);
                        s.setQuestion(qtext);
                        s.setImageURL("images/tree.jpg");
                        if (part != null) {
                            s.setRequestedParticipants(Integer.parseInt(part));//
                        } else {
                            s.setRequestedParticipants(0);//(Integer.parseInt(part));
                        }
                        s.setStudyDesc(desc);
                        s.setAnswerChoices(answerChoices);
                        
                        try {
                            stDb.updateStudy(s);
                            request.setAttribute("studyList", sList);

                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }

                url = "/studies.jsp";

            } else if ("stop".equals(action)) {

                String studyCode = request.getParameter("studyCode");

                if (studyCode != null) {
                    for (Study s : sList) {
                        if (s.getCode().equals(studyCode)) {

                            s.setStudyStatus("stop");
                            
                            try {
                                stDb.updateStudy(s);
                                request.setAttribute("studyList", sList);

                            } catch (ClassNotFoundException | SQLException ex) {
                                Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            break;
                        }
                    }
                    url = "/studies.jsp";
                }

            } else if ("start".equals(action)) {

                String studyCode = request.getParameter("studyCode");
                if (studyCode != null) {
                    for (Study s : sList) {
                        if (s.getCode().equals(studyCode)) {

                            s.setStudyStatus("start");
                            try {
                                stDb.updateStudy(s);
                                request.setAttribute("studyList", sList);

                            } catch (ClassNotFoundException | SQLException ex) {
                                Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        }
                    }
                }
                url = "/studies.jsp";

            } else if ("answer".equals(action)) {

                String studyCode = request.getParameter("studyCode");
                String choice = request.getParameter("choice");

                Answer a = new Answer();
                User u = (User) session.getAttribute("theUser");
                a.setParticipantEmail(u.getEmail());
                a.setsCode(studyCode);
                Calendar cal = Calendar.getInstance();
                a.setDateSubmitted(cal.getTime());
                a.setChoiceCd(choice);

                try {

                    AnswerDB ansrDB = new AnswerDB();
                    ansrDB.addAnswer(a);
                    
                    Study thisSt = stDb.getStudyByCode(studyCode);
                    thisSt.setNoOfParticipants(thisSt.getNoOfParticipants() + 1);
                    stDb.updateStudy(thisSt);

                    u.setCoins(u.getCoins() + 1);
                    u.setParticipation(u.getParticipation() + 1);

                    uDb.updateParticipation(u);
                    
                    User reseachUser = uDb.getUserByEmail(thisSt.getEmailCreator());
                    reseachUser.setParticipants(reseachUser.getParticipants() + 1);
                    uDb.updateParticipation(reseachUser);
                    
                    List<Study> partList = stDb.getOtherStudyList(thisUser.getEmail());
                    request.setAttribute("partList", partList);
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.setAttribute("theUser", u);
                url = "/participate.jsp";

            } else if ("add".equals(action)) {

                String sname = request.getParameter("newStudyName");
                String qtext = request.getParameter("studyQues");
                String part = request.getParameter("noOfParticip");
                String desc = request.getParameter("desc");
                String answerChoices = request.getParameter("studyAns");
                
                String code = UUID.randomUUID().toString();
                Study newStudy = new Study();
                newStudy.setCode(code);
                newStudy.setName(sname);
                newStudy.setQuestion(qtext);
                newStudy.setAnswerChoices(answerChoices);
                if (part != null) {
                    newStudy.setRequestedParticipants(Integer.parseInt(part));//
                } else {
                    newStudy.setRequestedParticipants(0);//(Integer.parseInt(part));
                }

                newStudy.setStudyDesc(desc);
                newStudy.setStudyStatus("stop");
                newStudy.setEmailCreator(thisUser.getEmail());
                newStudy.setImageURL("images/tree.jpg");
                Calendar cal = Calendar.getInstance();
                newStudy.setDateCreated(cal.getTime());
                
                try {
                    stDb.addStudy(newStudy);

                    User u = (User) session.getAttribute("theUser");
                    u.setStudies(u.getStudies() + 1);
                    //u.setParticipants(u.getParticipants() + 1);
                    
                    uDb.updateParticipation(u);
                    
                    session.setAttribute("theUser", u);
                    sList.add(newStudy);
                    request.setAttribute("studyList", sList);

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                }

                
                url = "/studies.jsp";

            } else {

                if (session.getAttribute("theUser") != null) {
                    url = "/main.jsp";
                } else {
                    url = "/home.jsp";
                }

            }
        } else {
            url = "/login.jsp";
        }

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

   
}
