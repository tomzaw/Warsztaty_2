package warsztaty_2.core;

import java.util.List;
import java.util.Scanner;
import warsztaty_2.models.Group;
import warsztaty_2.dao.GroupDAO;

/**
 *
 * @author Tomek
 */
public class GroupsManagement {
    
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        GroupDAO groupDAO = new GroupDAO();

        List<Group> groups;

        while (true) {

            groups = groupDAO.findAll();

            for (Group group : groups) {

                System.out.println(group.toString());
            }

            System.out.println("");

            System.out.println("Choose one option.");
            System.out.println("1.Add group.");
            System.out.println("2.Edit group.");
            System.out.println("3.Delete group.");
            System.out.println("4.Quit.");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter name.");
                    String name1 = sc.nextLine();
                    groupDAO.create(new Group(name1));

                    break;

                case 2:
                    System.out.println("Enter id.");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter name.");
                    String name2 = sc.nextLine();
                    Group group2 = new Group(name2);
                    group2.setId(id2);
                    groupDAO.update(group2);

                    break;

                case 3:
                    System.out.println("Enter id.");
                    int id3 = sc.nextInt();
                    sc.nextLine();
                    Group group3 = new Group();
                    group3.setId(id3);
                    groupDAO.delete(group3);

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
