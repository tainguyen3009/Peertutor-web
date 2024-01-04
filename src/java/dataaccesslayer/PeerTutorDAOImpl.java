package dataaccesslayer;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import transferobject.PeerTutor;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Tai Nguyen
 * Class PeerTutorDAOimplimplements the PeerTutorDAO interface and provides
 * methods for interacting with the database to perform operations related to
 * peer tutors and courses.
 */
public class PeerTutorDAOImpl implements PeerTutorDAO {

    /**
     * Checks if a peer tutor is registered in the database.
     * @param peerTutor The PeerTutor object to check for registration.
     * @return true if the peer tutor is registered, false otherwise.
     */
    @Override
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        boolean isRegistered = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = (Connection) ds.createConnection();
            String sqlQuery = "SELECT * FROM PeerTutor WHERE FirstName = ? AND LastName = ?";
            pstm = con.prepareStatement(sqlQuery);
            pstm.setString(1, peerTutor.getFirstName());
            pstm.setString(2, peerTutor.getLastName());
            rs = (ResultSet) pstm.executeQuery();
            
            if (rs.next()){
                isRegistered = true;
            }
        } catch(SQLException e){
            e.printStackTrace();   
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (pstm != null){
                try{ 
                    pstm.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (con != null){
                try{
                    con.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return isRegistered;
        
    }
   /**
    * Checks if a course with the given course code exists in the database.
    *
    * @param courseCode The course code to check for validity.
    * @return true if the course is valid, false otherwise.
    */
    @Override
    public boolean isCourseValid(String courseCode) {
        boolean isValid = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = (Connection) ds.createConnection();
            String sqlQuery = "SELECT * FROM Course WHERE CourseCode = ?";
            pstm = con.prepareStatement(sqlQuery);
            pstm.setString(1, courseCode);
            rs = (ResultSet) pstm.executeQuery();
            
            if (rs.next()){
                isValid = true;
            }
        } catch(SQLException e){
            e.printStackTrace();   
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (pstm != null){
                try{ 
                    pstm.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (con != null){
                try{
                    con.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return isValid;
    }

    /**
    * Checks if a peer tutor has taken a specific course based on their name and the course code.
    *
    * @param peerTutor The PeerTutor object representing the peer tutor.
    * @param courseCode The course code to check for the peer tutor.
    * @return true if the peer tutor has taken the course, false otherwise.
    */
    @Override
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        boolean isPeerTutorTaken = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = (Connection) ds.createConnection();
            String sqlQuery = "SELECT * FROM Student"
                    + " JOIN StudentCourse ON Student.StudentID = StudentCourse.Student_StudentID "
                    + " JOIN Course ON Course.CourseCode = StudentCourse.Course_CourseCode "
                    + " WHERE LastName = ? AND FirstName = ? AND CourseCode = ? ";
            pstm = con.prepareStatement(sqlQuery);
            pstm.setString(1,peerTutor.getLastName());
            pstm.setString(2, peerTutor.getFirstName());
            pstm.setString(3, courseCode);
            rs = (ResultSet) pstm.executeQuery();
            
            if (rs.next()){
                isPeerTutorTaken = true;
            }
        } catch(SQLException e){
            e.printStackTrace();   
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (pstm != null){
                try{ 
                    pstm.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (con != null){
                try{
                    con.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return isPeerTutorTaken;
    }

    /**
     * Retrieves the letter grade obtained by a peer tutor for a specific course.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor.
     * @param courseCode The course code for which to retrieve the letter grade.
     * @return The letter grade obtained by the peer tutor for the course.
     */
    @Override
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        
        try{
            DataSource ds = new DataSource();
            Connection con = ds.createConnection();
            
            String sqlQuery = "SELECT GradeCode FROM Student"
                    + " JOIN Grade ON Student.StudentID = Grade.Student_StudentID"
                    + " JOIN Course ON Course.CourseCode = Grade.Course_CourseCode"
                    + " WHERE LastName = ? AND FirstName = ? AND CourseCode = ?";
            PreparedStatement pstm = con.prepareStatement(sqlQuery);
            pstm.setString(1, peerTutor.getLastName());
            pstm.setString(2, peerTutor.getFirstName());
            pstm.setString(3, courseCode);
            try (ResultSet rs = pstm.executeQuery()){
            
            if (rs.next()){
                return rs.getString("GradeCode");
            }
        } 
        } catch(SQLException e){
            e.printStackTrace();   
        }
            return null;
        }
    
    /**
    * Checks if a course is already assigned to a peer tutor based on their name and the course code.
    *
    * @param peerTutor The PeerTutor object representing the peer tutor.
    * @param courseCode The course code to check for assignment.
    * @return true if the course is already assigned to the peer tutor, false otherwise.
    */
    @Override
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        boolean isCourseAssigned = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = (Connection) ds.createConnection();
            String sqlQuery = "SELECT * FROM PeerTutor"
                    + " JOIN PeerTutorCourse ON PeerTutor.PeerTutorID = PeerTutorCourse.PeerTutor_PeerTutorID"
                    + " JOIN Course ON PeerTutorCourse.Course_CourseCode = Course.CourseCode"
                    + " WHERE LastName = ? AND FirstName = ? AND Course_CourseCode = ?";
            pstm = con.prepareStatement(sqlQuery);
            pstm.setString(1, peerTutor.getLastName());
            pstm.setString(2, peerTutor.getFirstName());
            pstm.setString(3, courseCode);
            rs = (ResultSet) pstm.executeQuery();
            
            if (rs.next()){
                isCourseAssigned = true;
            }
        } catch(SQLException e){
            e.printStackTrace();   
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (pstm != null){
                try{ 
                    pstm.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (con != null){
                try{
                    con.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return isCourseAssigned;
    }

    /**
    * Assigns a course to a peer tutor in the database.
    *
    * @param peerTutor The PeerTutor object representing the peer tutor.
    * @param courseCode The course code to assign to the peer tutor.
    */
    @Override
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        Connection con = null;
        PreparedStatement pstm = null;

        try{
            DataSource ds = new DataSource();
            con = (Connection) ds.createConnection();
            String sqlQuery = "INSERT INTO PeerTutorCourse (PeerTutor_LastName, PeerTutor_FirstName, Course_CourseCode) VALUES (?,?,?)";
            pstm = con.prepareStatement(sqlQuery);
            pstm.setString(1, peerTutor.getLastName());
            pstm.setString(2, peerTutor.getFirstName());
            pstm.setString(3, courseCode);
            pstm.executeUpdate();
            
            

        } catch(SQLException e){
            e.printStackTrace();   
        } finally {
             
                if (pstm != null){
                try{ 
                    pstm.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            
            if (con != null){
                try{
                    con.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }


    /**
    * Retrieves a list of all peer tutors assigned to a specific course.
    *
    * @param courseCode The course code for which to retrieve peer tutors.
    * @return A list of PeerTutor objects representing peer tutors for the course.
    */
    @Override
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<PeerTutor> allPeerTutors = null;
        try{
            DataSource ds = new DataSource();
            con =  ds.createConnection();
            pstm = con.prepareStatement(
                    "SELECT PeerTutorID, LastName, FirstName FROM PeerTutor"
                            + " JOIN PeerTutorCourse ON PeerTutor.PeerTutorID = PeerTutorCourse.PeerTutor_PeerTutorID"
                            + "  WHERE PeerTutorCourse.Course_CourseCode = ?");
            pstm.setString(1, courseCode);
            rs = (ResultSet) pstm.executeQuery();
            allPeerTutors = new ArrayList<PeerTutor>();

            while (rs.next()){
                PeerTutor peerTutor = new PeerTutor();
                peerTutor.setPeerTutorID((rs.getInt("PeerTutorID")));
                peerTutor.setLastName(rs.getString("LastName"));
                peerTutor.setFirstName(rs.getString("FirstName"));
                allPeerTutors.add(peerTutor);
            }
        } catch(SQLException e){
            e.printStackTrace();   
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (pstm != null){
                try{ 
                    pstm.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (con != null){
                try{
                    con.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return allPeerTutors;
    }
    
}
