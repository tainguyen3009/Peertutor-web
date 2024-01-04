package viewlayer;

import businesslayer.PeerTutorBusinessLogic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobject.PeerTutor;

/**
 * 
 * @author Tai Nguyen
 * The PeerTutorServlet class is a servlet that handles requests related to peertutors
 * It processes GET and POST requests, validates input, and displays information based on the business logic.
 */
public class PeerTutorServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PeerTutorServlet view</title>");
            out.println("</head>");
            out.println("<body bgcolor=\"lightgrey\">");
            out.println("<h1>Servlet PeerTutorServlet View at" + request.getContextPath() + "</h1>");
             String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String courseCode = request.getParameter("courseCode");
            PeerTutor peerTutor = new PeerTutor();
            peerTutor.setLastName(lastName);
            peerTutor.setFirstName(firstName);
            PeerTutorBusinessLogic logic = new PeerTutorBusinessLogic();
            List<PeerTutor> allPeerTutors = logic.getAllPeerTutorsForCourse(courseCode);
//            PeerTutor peerTutor = null;
            boolean isRegistered = logic.isPeerTutorRegistered(peerTutor);
            boolean isValid = logic.isCourseValid(courseCode);
            boolean isPeerTutorTaken = logic.hasPeerTutorTakenCourse(peerTutor, courseCode);
            String letterGrade = logic.getPeerTutorLetterGradeForCourse(peerTutor, courseCode);
            boolean isCourseAssigned = logic.isCourseAlreadyAssignedToPeerTutor(peerTutor, courseCode);
            // Check if peertutor is registered 
            if (!isRegistered) {
                out.println("<ul>");
                out.println("<li>Last Name: " + peerTutor.getLastName() + "</li>");
                out.println("<li>First Name :" + peerTutor.getFirstName()+ "</li>");
                out.println("</ul>");
                out.println("<strong>Error: The person is not registered as a peer tutoring</strong>");
                // Check if the course code is valid
            } else if (!isValid) {
                out.println("<ul>");
                out.println("<li>CourseCode: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<strong>Error: The course code is not valid</strong>");
                // Check if the peer tutor is taken the course
            } else if (!isPeerTutorTaken) {
                out.println("<ul>");
                out.println("<li>Last Name: " + peerTutor.getLastName() + "</li>");
                out.println("<li>First Name: " + peerTutor.getFirstName() + "</li>");
                out.println("<li>CourseCode: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<strong>Error: The peer tutor has not taken the course</strong>");
                // Check if the input peertutor pass the lettergrade
            } else if (!"A+".equals(letterGrade) && !"A".equals(letterGrade) && !"A-".equals(letterGrade)) {
                out.println("<ul>");
                out.println("<li>Last Name: " + peerTutor.getLastName() + "</li>");
                out.println("<li>First Name: " + peerTutor.getFirstName() + "</li>");
                out.println("<li>CourseCode: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<strong>Error: The letter grade obtained by the peer tutor for the course is not sufficient</strong>");
            // Check if the course is assigned to a peertutor
            } else if (!isCourseAssigned) {
                out.println("<ul>");
                out.println("<li>Last Name: " + peerTutor.getLastName() + "</li>");
                out.println("<li>First Name: " + peerTutor.getFirstName() + "</li>");
                out.println("<li>CourseCode: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<strong>Error: The peer tutor is already assigned to the course</strong>");
            } else {
                // if all of conditions passed, print information table

                out.println("<h2>Table of Peer Tutors for " + courseCode + "</h2>)");
                out.println("<table border=\"1\">");
                out.println("<tr>");
                out.println("<td>TutorID: </td>");
                out.println("<td>Last Name: </td>");
                out.println("<td>First Name: </td>");
                out.println("</tr>");
                for (PeerTutor tutor : allPeerTutors) {
                    out.printf(("<tr><td>%d</td><td>%s</td><td>%s</td></tr>"),
                            tutor.getPeerTutorID(), tutor.getLastName(), tutor.getFirstName());
                }
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }

            // TODO:  Add your code here.  Make sure to use try-catch or
            //        try-with-resources statement here.  Need to instantiate a
            //        PrintWriter object which is a resource.  You can use the
            //        PrintWriter object to compose the HTML response of this
            //        servlet.  Also, need to instantiate a PeerTutorBusinessLogic
            //        object here and use it to access the database by calling its
            //        appropriate methods.  As the servlet composes the HTML response,
            //        it should use the business logic object.  You should also
            //        retrieve the request parameters here and instantiate a PeerTutor
            //        object and set its fields as needed.  Use bgcolor="#FDF5E6" for
            //        the background color of the HTML response of this servlet.
            //        Please refer to the sample projects code in Week 9.
        }
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
