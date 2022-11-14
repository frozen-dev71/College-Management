package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.AdminException;
import style.Style;
import utility.DBUtil;

public class AdminDaoImpl implements AdminDao {

    @Override
    public boolean LoginAdmin(String username, String password) throws AdminException {

        boolean flag  = false ;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn.prepareStatement("select * from Admin where username = ?");

            ps.setString(1, username);


            ResultSet  rs = ps.executeQuery();

            if(rs.next()) {

                PreparedStatement ps2 = conn.prepareStatement("select * from Admin where username = ? and password = ?");

                ps2.setString(1, username);
                ps2.setString(2, password);

                ResultSet  rs2 = ps2.executeQuery();

                if(rs2.next()) {
                    flag = true ;
                }
                else
                    throw new AdminException(Style.RED+"Wrong Password"+Style.RESET);

            }else {
                throw new AdminException(Style.RED+"No Such Admin Present With this Username"+Style.RESET);

            }

        } catch (SQLException e) {
            throw new AdminException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }

        return flag ;

    }

}
