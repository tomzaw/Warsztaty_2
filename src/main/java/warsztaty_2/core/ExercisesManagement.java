package warsztaty_2.core;

import java.util.List;
import java.util.Scanner;
import warsztaty_2.models.Exercise;
import warsztaty_2.dao.ExerciseDAO;

/**
 *
 * @author Tomek
 */
public class ExercisesManagement {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        ExerciseDAO exerciseDAO = new ExerciseDAO();

        List<Exercise> exercises;

        while (true) {

            exercises = exerciseDAO.findAll();

            for (Exercise exercise : exercises) {

                System.out.println(exercise.toString());
            }

            System.out.println("");

            System.out.println("Choose one option.");
            System.out.println("1.Add exercise.");
            System.out.println("2.Edit exercise.");
            System.out.println("3.Delete exercise.");
            System.out.println("4.Quit.");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter title.");
                    String title1 = sc.nextLine();
                    System.out.println("Enter description.");
                    String description1 = sc.nextLine();
                    exerciseDAO.create(new Exercise(title1, description1));

                    break;

                case 2:
                    System.out.println("Enter id.");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter title.");
                    String title2 = sc.nextLine();
                    System.out.println("Enter description.");
                    String description2 = sc.nextLine();
                    Exercise exercise2 = new Exercise(title2, description2);
                    exercise2.setId(id2);
                    exerciseDAO.update(exercise2);

                    break;

                case 3:
                    System.out.println("Enter id.");
                    int id3 = sc.nextInt();
                    sc.nextLine();
                    Exercise exercise3 = new Exercise();
                    exercise3.setId(id3);
                    exerciseDAO.delete(exercise3);

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
