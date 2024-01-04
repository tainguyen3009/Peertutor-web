package dataaccesslayer;

import java.util.List;
import transferobject.PeerTutor;
/**
 * 
 * @author Tai Nguyen
 * Student Number: 041086103
 * DAO class 
 */
public interface PeerTutorDAO {
    /**
     * Checks if a peer tutor is registered.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor.
     * @return true if the peer tutor is registered, false otherwise.
     */
    
    boolean isPeerTutorRegistered(PeerTutor peerTutor);
    /**
     * Checks if a course is valid.
     *
     * @param courseCode The code of the course to be checked.
     * @return true if the course is valid, false otherwise.
     */
    boolean isCourseValid(String courseCode);
    
     /**
     * Checks if a peer tutor has taken a specific course.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor.
     * @param courseCode The code of the course to be checked.
     * @return true if the peer tutor has taken the course, false otherwise.
     */
    boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode);
    
     /**
     * Retrieves the letter grade obtained by a peer tutor for a specific course.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor.
     * @param courseCode The code of the course.
     * @return The letter grade obtained by the peer tutor for the course.
     */
    String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode);
    
     /**
     * Checks if a course is already assigned to a peer tutor.
     *
     * @param peerTutor The PeerTutor object representing the peer tutor.
     * @param courseCode The code of the course.
     * @return true if the course is already assigned to the peer tutor, false otherwise.
     */
    boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode);

   /**
    * Assigns a course to a peer tutor.
    * @param peerTutor The PeerTutor object representing the peer tutor.
    * @param courseCode  The code of the course to be assigned.
    */
    void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode);
    
    /**
     * Retrieves a list of all peer tutors for a specific course.
     *
     * @param courseCode The code of the course.
     * @return List of PeerTutor objects representing all peer tutors for the course.
     */
    List<PeerTutor> getAllPeerTutorsForCourse(String courseCode);
}

