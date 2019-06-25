package warsztaty_2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import warsztaty_2.models.Solution;
import util.DbUtil;
import java.time.LocalDateTime;

/**
 *
 * @author Tomek
 */
public class SolutionDAO {

    public void create(Solution solution) {

        try (Connection con = DbUtil.getCon()) {

            if (solution.getId() == 0) {

                String sql = "INSERT INTO solution(created, updated, description, exercise_id, users_id) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                LocalDateTime created = solution.getCreated();
                if (created != null) {
                    stmt.setString(1, created.toString());
                } else {
                    stmt.setString(1, null);
                }
                LocalDateTime updated = solution.getCreated();
                if (updated != null) {
                    stmt.setString(2, updated.toString());
                } else {
                    stmt.setString(2, null);
                }
                stmt.setString(3, solution.getDescription());
                stmt.setInt(4, solution.getExerciseId());
                stmt.setLong(5, solution.getUsersId());
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

    public void update(Solution solution) {

        try (Connection con = DbUtil.getCon()) {

            if (solution.getId() == 0) {

                String sql = "INSERT INTO solution(created, updated, description, exercise_id, users_id) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                LocalDateTime created = solution.getCreated();
                if (created != null) {
                    stmt.setString(1, created.toString());
                } else {
                    stmt.setString(1, null);
                }
                LocalDateTime updated = solution.getCreated();
                if (updated != null) {
                    stmt.setString(2, updated.toString());
                } else {
                    stmt.setString(2, null);
                }
                stmt.setString(3, solution.getDescription());
                stmt.setInt(4, solution.getExerciseId());
                stmt.setLong(5, solution.getUsersId());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    solution.setId(rs.getInt(1));
                }

            } else {

                String sql = "UPDATE solution SET created=?, updated=?, description=?, exercise_id=?, users_id=? WHERE id=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                LocalDateTime created = solution.getCreated();
                if (created != null) {
                    stmt.setString(1, created.toString());
                } else {
                    stmt.setString(1, null);
                }
                LocalDateTime updated = solution.getCreated();
                if (updated != null) {
                    stmt.setString(2, updated.toString());
                } else {
                    stmt.setString(2, null);
                }
                stmt.setString(3, solution.getDescription());
                stmt.setInt(4, solution.getExerciseId());
                stmt.setLong(5, solution.getUsersId());
                stmt.setInt(6, solution.getId());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void delete(Solution solution) {

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

            String sql = "SELECT * FROM solution order by created desc";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                Timestamp created = rs.getTimestamp("created");
                if (created != null) {
                    solution.setCreated(created.toLocalDateTime());
                } else {
                    solution.setCreated(null);
                }
                Timestamp updated = rs.getTimestamp("updated");
                if (updated != null) {
                    solution.setUpdated(updated.toLocalDateTime());
                } else {
                    solution.setUpdated(null);
                }
                solution.setDescription(rs.getString("description"));
                solution.setExerciseId(rs.getInt("exercise_id"));
                solution.setUsersId(rs.getLong("users_id"));
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
                Timestamp created = rs.getTimestamp("created");
                if (created != null) {
                    solution.setCreated(created.toLocalDateTime());
                } else {
                    solution.setCreated(null);
                }
                Timestamp updated = rs.getTimestamp("updated");
                if (updated != null) {
                    solution.setUpdated(updated.toLocalDateTime());
                } else {
                    solution.setUpdated(null);
                }
                solution.setDescription(rs.getString("description"));
                solution.setExerciseId(rs.getInt("exercise_id"));
                solution.setUsersId(rs.getLong("users_id"));
                return solution;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public List<Solution> findAllByUsersId(long usersId) {

        List<Solution> solutions = new ArrayList<>();

        try (Connection con = DbUtil.getCon()) {

            String sql = "SELECT * FROM solution WHERE users_id=? order by created desc";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, usersId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                Timestamp created = rs.getTimestamp("created");
                if (created != null) {
                    solution.setCreated(created.toLocalDateTime());
                } else {
                    solution.setCreated(null);
                }
                Timestamp updated = rs.getTimestamp("updated");
                if (updated != null) {
                    solution.setUpdated(updated.toLocalDateTime());
                } else {
                    solution.setUpdated(null);
                }
                solution.setDescription(rs.getString("description"));
                solution.setExerciseId(rs.getInt("exercise_id"));
                solution.setUsersId(rs.getLong("users_id"));
                solutions.add(solution);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return solutions;
    }

    public List<Solution> findAllByExerciseId(int exerciseId) {

        List<Solution> solutions = new ArrayList<>();

        try (Connection con = DbUtil.getCon()) {

            String sql = "SELECT * FROM solution WHERE exercise_id=? order by created desc";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, exerciseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                Timestamp created = rs.getTimestamp("created");
                if (created != null) {
                    solution.setCreated(created.toLocalDateTime());
                } else {
                    solution.setCreated(null);
                }
                Timestamp updated = rs.getTimestamp("updated");
                if (updated != null) {
                    solution.setUpdated(updated.toLocalDateTime());
                } else {
                    solution.setUpdated(null);
                }
                solution.setDescription(rs.getString("description"));
                solution.setExerciseId(rs.getInt("exercise_id"));
                solution.setUsersId(rs.getLong("users_id"));
                solutions.add(solution);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return solutions;
    }
}
