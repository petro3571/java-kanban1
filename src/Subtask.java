public class Subtask extends Task {
    private int epicId;

    public Subtask (Status status, String description, String name, int epicId) {
        super(status,description,name);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id = " + getId() + ", name = " + getName() + ", description = " +
                getDescription() + ", status = " + getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}