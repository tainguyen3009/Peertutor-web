package businesslayer;

import dataaccesslayer.PeerTutorDAO;
import dataaccesslayer.PeerTutorDAOImpl;
import java.util.List;
import transferobject.PeerTutor;
/**
 * 
 * @author Tai Nguyen
 * Student number: 041086103
 * Logic class 
 */
public class PeerTutorBusinessLogic {
    private PeerTutorDAO peerTutorDAO = null;
    /**
     * create a new DAO implement object
     */
    public PeerTutorBusinessLogic() {
        peerTutorDAO = new PeerTutorDAOImpl();
    }
    /**
     * check if the peer tutor is registered
     * @param peerTutor is used to check
     * @return true if the peerTutor is registered
     */
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        return peerTutorDAO.isPeerTutorRegistered(peerTutor);
    }
    /**
     * check if the course valid 
     * @param courseCode is used to check
     * @return true if the course is valid, false if other
     */
    public boolean isCourseValid(String courseCode) {
        return peerTutorDAO.isCourseValid(courseCode);
    }
    /**
     * check if the peer tutor taken the course
     * @param peerTutor is used to check 
     * @param courseCode is used to check
     * @return true if the peerTutor is taken the course
     */
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.isPeerTutorRegistered(peerTutor);
    }

    /**
     * Gets the letter grade for a peer tutor in a specific course.
     *
     * @param peerTutor The PeerTutor object.
     * @param courseCode The code of the course.
     * @return The letter grade for the peer tutor in the specified course.
     */
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.getPeerTutorLetterGradeForCourse(peerTutor, courseCode);
    }
    /**
     * check if the course is assigned to peer tutor
     * @param peerTutor is used to check 
     * @param courseCode is used to check
     * @return true if the course is assigned to peer tutor
     */
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.isCourseAlreadyAssignedToPeerTutor(peerTutor, courseCode);
    }
    /**
     * Assigns a course to a peer tutor.
     *
     * @param peerTutor The PeerTutor object.
     * @param courseCode The code of the course to assign.
     */
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
         peerTutorDAO.assignCourseToPeerTutor(peerTutor, courseCode);
    }
    /**
     * list method that will return all peer tutor for each course
     * @param courseCode is used as primary key to check
     * @return all peer tutor for each course
     */
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        return peerTutorDAO.getAllPeerTutorsForCourse(courseCode);
    }
    
}
