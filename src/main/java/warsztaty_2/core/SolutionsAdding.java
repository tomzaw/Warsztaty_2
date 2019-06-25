package warsztaty_2.core;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;
import warsztaty_2.dao.ExerciseDAO;
import warsztaty_2.models.Solution;
import warsztaty_2.dao.SolutionDAO;
import warsztaty_2.models.Exercise;

/**
 *
 * @author Tomek
 */
public class SolutionsAdding {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user id.");
        long usersId = sc.nextInt();
        SolutionDAO solutionDAO = new SolutionDAO();
        ExerciseDAO exerciseDAO = new ExerciseDAO();

        List<Solution> solutions;
        List<Exercise> exercises;

        while (true) {

            System.out.println("Choose one option.");
            System.out.println("1.Add solution.");
            System.out.println("2.View solutions.");
            System.out.println("3.Quit.");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("");
                    System.out.println("Unsolved exercises.");
                    exercises = exerciseDAO.findAllUnsolvedByUserId(usersId);

                    for (Exercise exercise : exercises) {

                        System.out.println(exercise.toString());
                    }

                    System.out.println("Enter exercise id.");
                    int exerciseId = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter description.");
                    String description = sc.nextLine();
                    solutionDAO.create(new Solution(LocalDateTime.now(), description, exerciseId, usersId));
                    break;

                case 2:
                    System.out.println("");
                    System.out.println("Solutions.");
                    solutions = solutionDAO.findAllByUsersId(usersId);
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
