import java.io.Serializable;

public class Topic implements Serializable {
    private String topicName;
    private boolean completed;

    public Topic(String topicName) {
        this.topicName = topicName;
        this.completed = false;
    }

    public String getTopicName() {
        return topicName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return topicName + " - " + (completed ? "Completed" : "Pending");
    }
}
