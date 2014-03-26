/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordkeep;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Shajeer
 */
public class DbConnect {

    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";
    /**
     *
     * @author Shajeer
     */
    int id;
    Connection con;
    Statement st;
    ResultSet rt;
    String usr, pwd;

    /**
     *
     */
    public DbConnect() throws InstantiationException, IllegalAccessException {

        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(protocol + "login;create=true", "", "");
            st = con.createStatement();
           // st.executeUpdate("drop table loginfo");
           // st.executeUpdate("drop table credentials");
            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getTables(null, null, "LOGINFO", null);
            if (!res.next()) {
                st.executeUpdate("create table loginfo(id integer primary key,username varchar(30) not null,password varchar(30) not null)");
                st.executeUpdate("create table credentials(id integer primary key,username varchar(30) not null,url varchar(100) not null,password varchar(40))");
                st.executeUpdate("insert into loginfo values(1,'user','password')");//setting default userid and password
            }

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     *
     * @param usr
     * @param pwd
     * @throws SQLException
     */
    public boolean validlog(String usr, String pwd) throws SQLException {
        int count = 0;
        rt = st.executeQuery("select * from LOGINFO");
        while (rt.next()) {
            if(usr.equals(rt.getString(2))&&pwd.equals(rt.getString(3))){
            count++;
            }
            System.out.print(rt.getString(2));
        }
        if (count == 0) {
            JOptionPane.showMessageDialog(null, "Username or Password error !");
            return false;
        } else if (count == 1) {
            return true;
        }
        return false;

    }

    /**
     *
     * @param usr
     * @param pwd
     * @throws SQLException
     *
     * /**
     *
     * @param id
     * @param user
     * @param url
     * @param i
     * @throws SQLException
     */
    public int getdetails(int[] id, String[] user, String[] url) throws SQLException {
        String sql = "select id,username,url from credentials";
        int i = 0;
        rt = st.executeQuery(sql);

        while (rt.next()) {
            id[i] = (rt.getInt("id"));
            user[i] = (rt.getString("username"));
            url[i] = (rt.getString("url"));
            i++;
        }
        return i;
    }

    /**
     *
     * @param us
     * @param url
     * @return
     * @throws SQLException
     */
    public String getPassWord(String us, String url) throws SQLException {
        String password = null;
        //JOptionPane.showMessageDialog(null, "shajeer");
        if (us == null && url == null) {
            JOptionPane.showMessageDialog(null, "Select valid row", "error", JOptionPane.OK_OPTION);
        } else {
            String sql = "select password from credentials where username='" + us + "'and url='" + url + "'";
            rt = st.executeQuery(sql);
            while (rt.next()) {
                password = rt.getString("password");
            }
        }
        return password;


    }

    public void editvalue(String newusrname, String oldusername, String oldurl, String newurl, String newpass) throws SQLException {
        String sql = "update credentials set username='" + newusrname + "'where username='" + oldusername + "'and url='" + oldurl + "'";
        String sql1 = "update credentials set url='" + newurl + "'where username='" + newusrname + "'and url='" + oldurl + "'";
        String sql2 = "update credentials set password='" + newpass + "'where username='" + newusrname + "'and url='" + newurl + "'";
        st.executeUpdate(sql);
        st.executeUpdate(sql1);
        st.executeUpdate(sql2);
        JOptionPane.showMessageDialog(null, "done !!");

    }

    public void delete(String username, String url) throws SQLException {
        //st.executeUpdate("update counter set datacount='" + totalkeep + 1 + "'where id=1");
        String sql = "delete from credentials where username='" + username + "' and url='" + url + "'";
        st.executeUpdate(sql);
    }

    /**
     *
     * @param usr
     * @param pas
     * @param url
     * @throws SQLException
     */
    public void adddata(String usr, String pas, String url) throws SQLException {
        if (!(usr == null || pas == null)) {
            String sql = "insert into credentials values(0,'" + usr + "','" + url + "','" + pas + "')";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Success !");
        } else {
            JOptionPane.showMessageDialog(null, "username or password not found !");
        }
    }

    public void changeCred(String newuser, String newpass) throws SQLException {


        try {
            st.executeUpdate("update loginfo set username='" + newuser + "' where id=1");
            st.executeUpdate("update loginfo set password= '" + newpass + "' where id=" + id);
            JOptionPane.showMessageDialog(null, "change successfully !");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
