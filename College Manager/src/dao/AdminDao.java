package dao;

import exception.AdminException;

public interface AdminDao {
    public boolean LoginAdmin(String username , String password) throws AdminException;

}
