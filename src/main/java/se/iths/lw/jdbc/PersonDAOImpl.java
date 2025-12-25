package se.iths.lw.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    @Override
    public List<Person> findAll() {
        String sql = "SELECT * FROM person";
        List<Person> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Person p = new Person(
                            rs.getInt("person_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("dob"),
                            rs.getDouble("income")
                    );

                    list.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insert (Person person) {
        String sql = "INSERT INTO person (first_name, last_name, dob, income) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update (Person person) {
        String sql = "UPDATE person SET first_name = ?, last_name = ?, dob = ?, income = ? WHERE person _id = ? ";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, person.getFirstName());
            ps.setString( 2, person.getLastName());
            ps.setDate(3, person.getDob());
            ps.setDouble(4, person.getIncome());
            ps.setInt(5, person.getPersonId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Person findById (Integer id) {
        String sql = "SELECT * FROM person WHERE person_id = ?";

        try (Connection conn = DatabaseUtil.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Person (
                            rs.getInt("person_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("dob"),
                            rs.getDouble("income")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void delete (Integer id) {
        String sql = "DELETE FROM person WHERE person_id = ?";

        try (Connection conn = DatabaseUtil.getInstance().getConnection();
        PreparedStatement ps =conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            int rowsDeleted = ps.executeUpdate();
            System.out.println("Antal rader borttagna: " + rowsDeleted);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
