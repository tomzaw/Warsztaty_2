package warsztaty_2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import warsztaty_2.models.User;
import util.DbUtil;

/**
 *
 * @author Tomek
 */
public class UserDAO {

    public void create(User user) {

        try (Connection con = DbUtil.getCon()) {

            if (user.getId() == 0) {

                String sql = "INSERT INTO users(username, email, password, group_id) VALUES(?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.setInt(4, user.getGroupId());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    user.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(User user) {

        try (Connection con = DbUtil.getCon()) {

            if (user.getId() == 0) {

                String sql = "INSERT INTO users(username, email, password, group_id) VALUES(?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.setInt(4, user.getGroupId());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    user.setId(rs.getLong(1));
                }

            } else {

                String sql = "UPDATE users SET username=?, email=?, password=?, group_id=? WHERE id=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.setInt(4, user.getGroupId());
                stmt.setLong(5, user.getId());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void delete(User user) {

        try (Connection con = DbUtil.getCon()) {

            if (user.getId() != 0) {

                String sql = "DELETE FROM users WHERE id=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setLong(1, user.getId());
                stmt.executeUpdate();
                user.setId(0);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        try (Connection con = DbUtil.getCon()) {

            String sql = "SELECT * FROM users";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGroupId(rs.getInt("group_id"));
                users.add(user);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }

    public User findById(long id) {

        try (Connection con = DbUtil.getCon()) {

            String sql = "SELECT * FROM users WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGroupId(rs.getInt("group_id"));
                return user;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public List<User> findAllByGroupId(int groupId) {

        List<User> users = new ArrayList<>();

        try (Connection con = DbUtil.getCon()) {

            String sql = "SELECT * FROM users WHERE group_id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, groupId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGroupId(rs.getInt("group_id"));
                users.add(user);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }
}
