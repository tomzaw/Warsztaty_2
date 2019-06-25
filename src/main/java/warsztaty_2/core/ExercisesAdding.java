package warsztaty_2.core;

import java.util.List;
import java.util.Scanner;
import warsztaty_2.models.Exercise;
import warsztaty_2.models.User;
import warsztaty_2.models.Solution;
import warsztaty_2.dao.SolutionDAO;
import warsztaty_2.dao.ExerciseDAO;
import warsztaty_2.dao.UserDAO;
import java.time.LocalDateTime;

/**
 *
 * @author Tomek
 */
public class ExercisesAdding {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        ExerciseDAO exerciseDAO = new ExerciseDAO();
        UserDAO userDAO = new UserDAO();
        SolutionDAO solutionDAO = new SolutionDAO();

        List<Solution> solutions;
        List<Exercise> exercises;
        List<User> users;

        while (true) {

            System.out.println("Choose one option.");
            System.out.println("1.Add exercise to the user.");
            System.out.println("2.Show solutions of the user.");
            System.out.println("3.Quit.");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("");
                    System.out.println("Users.");
                    users = userDAO.findAll();

                    for (User user : users) {

                        System.out.println(user.toString());
                    }
                    System.out.println("Enter user id.");
                    long usersId = sc.nextLong();
                    sc.nextLine();

                    exercises = exerciseDAO.findAll();

                    for (Exercise exercise : exercises) {

                        System.out.println(exercise.toString());
                    }

                    System.out.println("Enter exercise id.");
                    int exerciseId = sc.nextInt();
                    sc.nextLine();
                    solutionDAO.create(new Solution(LocalDateTime.now(), "", exerciseId, usersId));
                    break;

                case 2:
                    System.out.println("");
                    System.out.println("Enter user id.");
                    long usersId2 = sc.nextLong();
                    sc.nextLine();

                    System.out.println("");
                    System.out.println("Solutions.");
                    solutions = solutionDAO.findAllByUsersId(usersId2);
                    for (Solution solution : solutions) {

                        System.out.println(solution.toString());
                    }
                    break;

                case 3:
                    sc.close();
                    return;

                default:
                    break;
            }
        }
    }
}
