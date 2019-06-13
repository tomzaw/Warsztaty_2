package warsztaty_2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import warsztaty_2.models.Group;
import util.DbUtil;

/**
 *
 * @author Tomek
 */
public class GroupDAO {

    public void Create(Group group) {

        try (Connection con = DbUtil.getCon()) {

            if (group.getId() == 0) {

                String sql = "INSERT INTO group(name) VALUES(?)";
                PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, group.getName());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    group.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void Update(Group group) {

        try (Connection con = DbUtil.getCon()) {

            if (group.getId() == 0) {

                String sql = "INSERT INTO group(name) VALUES(?)";
                PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, group.getName());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    group.setId(rs.getInt(1));
                }

            } else {

                String sql = "UPDATE group SET name=? WHERE id=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, group.getName());
                stmt.setInt(2, group.getId());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void Delete(Group group) {

        try (Connection con = DbUtil.getCon()) {

            if (group.getId() != 0) {

                String sql = "DELETE FROM group WHERE id=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, group.getId());
                stmt.executeUpdate();
                group.setId(0);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public List<Group> findAll() {

        List<Group> groups = new ArrayList<>();

        try (Connection con = DbUtil.getCon()) {

            String sql = "SELECT * FROM Group";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                groups.add(group);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return groups;
    }

    public Group findById(int id) {

        try (Connection con = DbUtil.getCon()) {

            String sql = "SELECT * FROM group WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                return group;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }
}
