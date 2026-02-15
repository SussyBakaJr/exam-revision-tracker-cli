import java.util.Scanner;

public class Main {

    // Color constants
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RevisionTracker tracker = RevisionTracker.loadFromFile();

        while (true) {

            System.out.println(CYAN + "\n==== Exam Revision Tracker ====" + RESET);
            System.out.println("1. Add Subject");
            System.out.println("2. Add Topic to Subject");
            System.out.println("3. Mark Topic as Completed");
            System.out.println("4. View Progress");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter subject name: ");
                    String subjectName = scanner.nextLine();
                    tracker.addSubject(subjectName);
                    System.out.println(GREEN + "Subject added successfully!" + RESET);
                    break;

                case 2:
                    System.out.print("Enter subject name: ");
                    String subName = scanner.nextLine();
                    Subject subject = tracker.findSubject(subName);

                    if (subject == null) {
                        System.out.println(RED + "Subject not found." + RESET);
                    } else {
                        System.out.print("Enter topic name: ");
                        String topicName = scanner.nextLine();
                        subject.addTopic(topicName);
                        System.out.println(GREEN + "Topic added successfully!" + RESET);
                    }
                    break;

                case 3:
                    System.out.print("Enter subject name: ");
                    String subjectForCompletion = scanner.nextLine();
                    Subject sub = tracker.findSubject(subjectForCompletion);

                    if (sub == null) {
                        System.out.println(RED + "Subject not found." + RESET);
                    } else {
                        System.out.print("Enter topic name to mark completed: ");
                        String topicName = scanner.nextLine();

                        boolean found = false;

                        for (Topic t : sub.getTopics()) {
                            if (t.getTopicName().equalsIgnoreCase(topicName)) {
                                t.markCompleted();
                                tracker.updateStreak();
                                found = true;
                                System.out.println(GREEN + "Topic marked as completed!" + RESET);
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println(RED + "Topic not found." + RESET);
                        }
                    }
                    break;

                case 4:
                    tracker.displayAllSubjects();
                    break;

                case 5:
                    tracker.saveToFile();
                    System.out.println(GREEN + "Exiting... Data saved!" + RESET);
                    scanner.close();
                    return;

                default:
                    System.out.println(RED + "Invalid option. Try again." + RESET);
            }
        }
    }
}
