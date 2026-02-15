import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Subject implements Serializable {

    private String subjectName;
    private List<Topic> topics;

    public Subject(String subjectName) {
        this.subjectName = subjectName;
        this.topics = new ArrayList<>();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void addTopic(String topicName) {
        topics.add(new Topic(topicName));
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public double calculateProgress() {
        if (topics.isEmpty()) return 0;

        long completed = topics.stream()
                .filter(Topic::isCompleted)
                .count();

        return (completed * 100.0) / topics.size();
    }

    @Override
    public String toString() {
        return subjectName + " - Progress: " + 
               String.format("%.2f", calculateProgress()) + "%";
    }
}
