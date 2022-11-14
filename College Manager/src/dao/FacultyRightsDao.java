package dao;

import exception.FacultyRightsException;
import model.Faculty;

public interface FacultyRightsDao {
    public Faculty loginFaculty(String username, String password) throws FacultyRightsException;

    public String forgetPassword(String mobile, String email, String pass) throws FacultyRightsException;

    public String changePassword(int faculty, String pass) throws FacultyRightsException;
}
