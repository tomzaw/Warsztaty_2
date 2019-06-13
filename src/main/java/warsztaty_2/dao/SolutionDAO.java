package warsztaty_2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import warsztaty_2.models.Solution;
import util.DbUtil;

/**
 *
 * @author Tomek
 */
public class SolutionDAO {

    public void Create(Solution solution) {

        try (Connection con = DbUtil.getCon()) {

            if (solution.getId() == 0) {

                String sql = "INSERT INTO solution(created, updated, description, exercise_id, user_id) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, solution.getCreated().toString());
                stmt.setString(2, solution.getUpdated().toString());
                stmt.setString(3, solution.getDescription());
                stmt.setInt(4, solution.getExercise_id());
                stmt.setInt(5, solution.getUser_id());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    solution.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void Update(Solution solution) {

        try (Connection con = DbUtil.getCon()) {

            if (solution.getId() == 0) {

                String sql = "INSERT INTO solution(created, updated, description, exercise_id, user_id) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, solution.getCreated().toString());
                stmt.setString(2, solution.getUpdated().toString());
                stmt.setString(3, solution.getDescription());
                stmt.setInt(4, solution.getExercise_id());
                stmt.setInt(5, solution.getUser_id());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    solution.setId(rs.getInt(1));
                }

            } else {

                String sql = "UPDATE solution SET created=?, updated=?, description=?, exercise_id=?, user_id=? WHERE id=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, solution.getCreated().toString());
                stmt.setString(2, solution.getUpdated().toString());
                stmt.setString(3, solution.getDescription());
                stmt.setInt(4, solution.getExercise_id());
                stmt.setInt(5, solution.getUser_id());
                stmt.setInt(6, solution.getId());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void Delete(Solution solution) {

        try (Connection con = DbUtil.getCon()) {

            if (solution.getId() != 0) {

                String sql = "DELETE FROM solution WHERE id=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, solution.getId());
                stmt.executeUpdate();
                solution.setId(0);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public List<Solution> findAll() {

        List<Solution> solutions = new ArrayList<>();

        try (Connection con = DbUtil.getCon()) {

            String sql = "SELECT * FROM solution";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setCreated(LocalDateTime.parse(rs.getString("created")));
                solution.setUpdated(LocalDateTime.parse(rs.getString("updated")));
                solution.setDescription(rs.getString("description"));
                solution.setExercise_id(rs.getInt("exercise_id"));
                solution.setUser_id(rs.getInt("user_id"));
                solutions.add(solution);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return solutions;
    }

    public Solution findById(int id) {

        try (Connection con = DbUtil.getCon()) {

            String sql = "SELECT * FROM solution WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setCreated(LocalDateTime.parse(rs.getString("created")));
                solution.setUpdated(LocalDateTime.parse(rs.getString("updated")));
                solution.setDescription(rs.getString("description"));
                solution.setExercise_id(rs.getInt("exercise_id"));
                solution.setUser_id(rs.getInt("user_id"));
                return solution;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }
}
