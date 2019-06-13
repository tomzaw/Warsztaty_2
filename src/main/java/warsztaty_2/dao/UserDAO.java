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
    
    public void Create(User user) {
        
        try (Connection con = DbUtil.getCon()) {
            
            if (user.getId() == 0) {
                
                String sql = "INSERT INTO user(username, email, password) VALUES(?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                
                if (rs.next()) {
                    
                    user.setId(rs.getInt(1));
                }
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    
    public void Update(User user) {
        
        try (Connection con = DbUtil.getCon()) {
            
            if (user.getId() == 0) {
                
                String sql = "INSERT INTO user(username, email, password) VALUES(?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                
                if (rs.next()) {
                    
                    user.setId(rs.getInt(1));
                }
                
            } else {
                
                String sql = "UPDATE user SET username=?, email=?, password=? WHERE id=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.setInt(4, user.getId());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    
    public void Delete(User user) {
        
        try (Connection con = DbUtil.getCon()) {
            
            if (user.getId() != 0) {
                
                String sql = "DELETE FROM user WHERE id=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, user.getId());
                stmt.executeUpdate();
                user.setId(0);
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    
    public List<User> findAll(int id) {
        
        List<User> users = new ArrayList<>();
        
        try (Connection con = DbUtil.getCon()) {
            
            String sql = "SELECT * FROM user";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
        return users;
    }
    
    public User findById(int id) {
        
        try (Connection con = DbUtil.getCon()) {
            
            String sql = "SELECT * FROM user WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
        return null;
    }
}
