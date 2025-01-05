import java.util.ArrayList;

public class Epic extends Task{
    private final ArrayList<Subtask> subtasks;

    public Epic(String name, String description) {
        super(Status.NEW, description, name);
        subtasks = new ArrayList<>();
    }

    public ArrayList<Subtask> getSubtaks() {
        return subtasks;
    }

    public void addSubtasks(Subtask subtask) {
        subtasks.add(subtask);
    }

    public void deleteSubtasks (Subtask subtask) {
        subtasks.remove(subtask);
    }

    public void setEpicStatus () {
        boolean allNew = true;
        boolean allDone = true;
        if (subtasks.isEmpty()) {
            setStatus(Status.NEW);
            return;
        }

         for(Subtask subtask: subtasks) {
             if (subtask.getStatus() != Status.NEW) {
                 allNew = false;
             }
             if(subtask.getStatus() != Status.DONE) {
                 allDone = false;
             }
         }

         if (allDone) {
             setStatus(Status.DONE);
         } else if (allNew) {
             setStatus(Status.NEW);
         } else {
             setStatus(Status.IN_PROGRESS);
         }
    }


    @Override
    public String toString() {
        return "Epic{" +
            "id = " + getId() + ", name = " + getName() + ", description = " +
                    getDescription() + ", status = " + getStatus() +
                    ", subtasks=" + subtasks +
                    '}';
    }
}