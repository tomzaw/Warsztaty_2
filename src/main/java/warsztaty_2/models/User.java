package warsztaty_2.models;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private long id;
    private String username;
    private String email;
    private String password;
    private int groupId;

    public User(String username, String email, String password, int groupId) {

        this.username = username;
        this.email = email;
        setPassword(password);
        this.groupId = groupId;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", groupId=" + groupId + '}';
    }
}
