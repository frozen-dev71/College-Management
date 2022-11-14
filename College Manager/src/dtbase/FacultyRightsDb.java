package dtbase;

import exception.FacultyRightsException;
import model.Faculty;

public interface FacultyRightsDb {
    public Faculty loginFaculty(String username, String password) throws FacultyRightsException;

    public String forgetPassword(String mobile, String email, String pass) throws FacultyRightsException;

    public String changePassword(int faculty, String pass) throws FacultyRightsException;
}
