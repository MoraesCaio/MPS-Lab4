package infra;

import business.model.User;
import business.model.tree.GenealogicalTree;
import business.model.tree.Member;

import java.sql.*;
import java.util.ArrayList;


public class UserDAODB implements UserDAO
{

    private final String URL = "jdbc:sqlite:test.db";
    private final String USER_TABLE = "CREATE TABLE IF NOT EXISTS User (\n"
            + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
            + "	login text NOT NULL,\n"
            + "	password text NOT NULL\n"
            + ");";

    private final String MEMBER_TABLE = "CREATE TABLE IF NOT EXISTS Member (\n"
            + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
            + "	name text NOT NULL,\n"
            + "	gender text NOT NULL,\n"
            + "	id_user integer, FOREIGN KEY (id_user) REFERENCES User (id) ON DELETE CASCADE\n"
            + ");";

    public UserDAODB()
    {
        createTable();
    }

    private void createTable()
    {
        try (Connection c = DriverManager.getConnection(URL);
             Statement stmt = c.createStatement())
        {
            stmt.execute(USER_TABLE);
            stmt.execute(MEMBER_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(User user) throws Exception
    {

        String sql = "INSERT INTO User(login,password) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sAVE User: " + e.getMessage());
        }
    }

    @Override
    public User getUser(String login) throws Exception
    {
        String sql = "SELECT * FROM User WHERE login = ?";
        User user = null;

        try (Connection connection = this.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, login);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("login");
                String password = resultSet.getString("password");
                Long idUser = resultSet.getLong("id");
                ArrayList<Member> members = getMembers(idUser);
                user = new User(name, password, new GenealogicalTree(members));
            }
        } catch (SQLException e) {
            System.out.println("Não achou " + login);
        }
        return user;
    }

    @Override
    public void addMember(User user, Member member) throws Exception
    {
        long idUser = getIdUser(user.getName());
        System.out.println("IDUSER: " + idUser);
        String sql = "INSERT INTO Member(name,gender,id_user) VALUES(?,?,?)";

        String gender = member.gender ? "M" : "F";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.name);
            pstmt.setString(2, gender);
            pstmt.setLong(3, idUser);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println( "aDD MEMBER: " + e.getMessage());
        }
    }

    @Override
    public void updateMember(User user, String gender, String name, String newGender, String newName) throws Exception
    {
        String sql = "UPDATE Member SET name = ?, gender = ? WHERE id_user = ?"
                + " AND name = ?  AND gender = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newGender);
            pstmt.setLong(3, getIdUser(user.getName()));
            pstmt.setString(4, name);
            pstmt.setString(5, gender);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println( "uPDATE MEMBER: " + e.getMessage());
        }
    }

    private long getIdUser(String name)
    {

        String sql = "SELECT * FROM User WHERE login = ?";

        try (Connection connection = this.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, name);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("id");
            }

        } catch (SQLException e) {
        }

        return 0;
    }

    private void clear()
    {

        String sql = "DELETE FROM User";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Member> getMembers(Long idUser)
    {
        ArrayList<Member> members = new ArrayList<>();

        String sql = "SELECT * FROM Member WHERE id_user = ?";

        try (Connection connection = this.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
                pstmt.setLong(1, idUser);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet .next()) {

                String name = resultSet.getString("name");
                boolean gender = resultSet.getString("gender").equals("M");

                members.add(new Member(gender, name));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return members;
    }

    private Connection connect()
    {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
