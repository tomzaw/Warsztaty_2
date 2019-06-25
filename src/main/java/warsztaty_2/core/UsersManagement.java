package warsztaty_2.core;

import java.util.List;
import java.util.Scanner;
import warsztaty_2.models.User;
import warsztaty_2.dao.UserDAO;

/**
 *
 * @author Tomek
 */
public class UsersManagement {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();

        List<User> users;

        while (true) {

            users = userDAO.findAll();

            for (User user : users) {

                System.out.println(user.toString());
            }

            System.out.println("");

            System.out.println("Choose one option.");
            System.out.println("1.Add user.");
            System.out.println("2.Edit user.");
            System.out.println("3.Delete user.");
            System.out.println("4.Quit.");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter username.");
                    String username = sc.nextLine();
                    System.out.println("Enter email.");
                    String email = sc.nextLine();
                    System.out.println("Enter password.");
                    String password = sc.nextLine();
                    System.out.println("Enter group_id.");
                    int groupId = sc.nextInt();
                    sc.nextLine();
                    userDAO.create(new User(username, email, password, groupId));

                    break;

                case 2:
                    System.out.println("Enter id.");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter username.");
                    String username2 = sc.nextLine();
                    System.out.println("Enter email.");
                    String email2 = sc.nextLine();
                    System.out.println("Enter password.");
                    String password2 = sc.nextLine();
                    System.out.println("Enter group_id.");
                    int groupId2 = sc.nextInt();
                    sc.nextLine();
                    User user2 = new User(username2, email2, password2, groupId2);
                    user2.setId(id2);
                    userDAO.update(user2);

                    break;

                case 3:
                    System.out.println("Enter id.");
                    int id3 = sc.nextInt();
                    sc.nextLine();
                    User user3 = new User();
                    user3.setId(id3);
                    userDAO.delete(user3);

                    break;

                case 4:
                    sc.close();
                    return;

                default:
                    break;
            }
        }
    }
}
