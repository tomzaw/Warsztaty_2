package warsztaty_2.models;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    
    private int id;
    private String username;
    private String email; //Should be unique.
    private String password;

    public User(String username, String email, String password) {
        
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
