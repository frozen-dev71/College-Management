package dtbase;

import exception.AdminException;

public interface AdminDb {
    public boolean LoginAdmin(String username , String password) throws AdminException;

}
