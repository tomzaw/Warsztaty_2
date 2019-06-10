package warsztaty_2.models;

//import service.DbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//Not used.
public class UserDAO{}
/*
//download BCript.
public class UserDAO implements BaseDAO<User> {

    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.password = password;
    }

    @Override
    public List<User> findAll(){

        List<User> users = new ArrayList<>();

        try{

            List<String[]> _users = DbService.getInstance()
                    .execute("Select id, name, email, password from users").getRows();
                    _users.forEach(item ->{
                        User user = new User();
                        user.setId(Integer.parseInt(item[0]));
                        user.setName((item[1]);
                        user.setEmail(item[2]);
                        user.setUnhashedPassword(item[3]);
                        user.add(user);
                    });

        }catch (SQLException e){

            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User  findById(int id){

        List<User> users = new ArrayList<>();

      try{

            List<String[]> _users = DbService.getInstance()
                    .execute("Select id, name, email, password from users").getRows();
                    _users.forEach(item ->{
                        User user = new User();
                        user.setId(Integer.parseInt(item[0]));
                        user.setName((item[1]);
                        user.setEmail(item[2]);
                        user.setUnhashedPassword(item[3]);
                        user.add(user);
                    });

        }catch (SQLException e){

            e.printStackTrace();
        }

        return new User(); //To remove.
}

    @Override
    public User create(T item){

        try{

        }catch(SQLException e){

            e.printStackTrace();
        }

        return new User(); //To remove.
    }

    @Override
    public User update(T item){


        return new User(); //To remove.
    }

    @Override
    public User delete(int id){


        return new User(); //To remove.
    }
}
*/