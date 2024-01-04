package transferobject;
/**
 * 
 * @author Tai Nguyen
 * student number: 041086103
 * PeerTutor class contain getter setter method 
 */
public class PeerTutor {

    // Here are the fields for a peer tutor.
    private int peerTutorID;
    private String lastName;
    private String firstName;
    /**
     * get method for peerTutorID
     * @return peerTutorID as int
     */
    public int getPeerTutorID(){
        return peerTutorID;
    }
    /**
     * set the peerTutorID as int
     * @param peerTutorID The peer tutor's ID to set.
     */
    public void setPeerTutorID(int peerTutorID){
        this.peerTutorID = peerTutorID;
    }
    /**
     * get method for lastName
     * @return lastName as String
     */
    public String getLastName(){
        return lastName;
    }
    /**
     * set method for lastName
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    /**
     * get method for firstName
     * @return firstName as String
     */
    public String getFirstName(){
        return firstName;
    }
    /**
     * set method for firstName
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

}
