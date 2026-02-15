import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class RevisionTracker implements Serializable {

    private List<Subject> subjects;
    private LocalDate lastStudyDate;
    private int streak;

    public RevisionTracker() {
        subjects = new ArrayList<>();
        lastStudyDate = null;
        streak = 0;
    }

    // ---------------- SUBJECT MANAGEMENT ----------------

    public void addSubject(String subjectName) {
        subjects.add(new Subject(subjectName));
    }

    public Subject findSubject(String subjectName) {
        for (Subject s : subjects) {
            if (s.getSubjectName().equalsIgnoreCase(subjectName)) {
                return s;
            }
        }
        return null;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    // ---------------- PROGRESS CALCULATION ----------------

    public double calculateOverallProgress() {
        if (subjects.isEmpty()) return 0;

        double total = 0;
        for (Subject s : subjects) {
            total += s.calculateProgress();
        }

        return total / subjects.size();
    }

    public void displayAllSubjects() {
        if (subjects.isEmpty()) {
            System.out.println("No subjects added yet.");
            return;
        }

        for (Subject s : subjects) {
            System.out.println(s);
        }

        System.out.println("Overall Progress: " +
                String.format("%.2f", calculateOverallProgress()) + "%");

        System.out.println("Current Study Streak: " + streak + " day(s)");
    }

    // ---------------- STREAK LOGIC ----------------

    public void updateStreak() {
        LocalDate today = LocalDate.now();

        if (lastStudyDate == null) {
            streak = 1;
        } else if (lastStudyDate.plusDays(1).equals(today)) {
            streak++;
        } else if (!lastStudyDate.equals(today)) {
            streak = 1;
        }

        lastStudyDate = today;
    }

    public int getStreak() {
        return streak;
    }

    // ---------------- FILE PERSISTENCE ----------------

    public void saveToFile() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("tracker.dat"))) {

            oos.writeObject(this);
            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public static RevisionTracker loadFromFile() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream("tracker.dat"))) {

            return (RevisionTracker) ois.readObject();

        } catch (Exception e) {
            return new RevisionTracker();
        }
    }
}
