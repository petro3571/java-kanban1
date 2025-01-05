import java.util.HashMap;

public class TaskManager {
    HashMap <Integer, Task> tasks;
    HashMap <Integer, Epic> epics;
    HashMap <Integer, Subtask> subtasks;
    private int index;

    public TaskManager () {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        index = 1;

    }

    public int plusIndex() {
        return index++;
    }

    public void addTask (Task task) {
        task.setId(plusIndex());
        tasks.put(task.getId(), task);
    }

    public void addEpic (Epic epic) {
        epic.setId(plusIndex());
        epics.put(epic.getId(), epic);
    }

    public void addSubtask (Subtask subtask) {
        subtask.setId(plusIndex());
        subtasks.put(subtask.getId(), subtask);
    }

    public HashMap <Integer, Task> getAllTasks(){
        return tasks;
    }

    public HashMap <Integer, Epic> getAllEpics(){
        return epics;
    }

    public HashMap <Integer, Subtask> getAllSubtasks(){
        return subtasks;
    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteSubtasks () {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtaks().clear();
            epic.setEpicStatus();
        }
    }

    public Task getTaskByIndex( int id) {
        return tasks.get(id);
    }

    public Epic getEpicByIndex( int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskByIndex( int id) {
        return subtasks.get(id);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        epic.setEpicStatus();
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            epic.setEpicStatus();
        }
    }

    public void deleteTaskByIndex(int id) {
        tasks.remove(id);
    }

    public void deleteEpicByIndex(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (Subtask subtask : epic.getSubtaks()) {
                subtasks.remove(subtask.getId());
            }
        }
    }

    public void deleteSubtaskByIndex( int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.deleteSubtasks(subtask);
                epic.setEpicStatus();
            }
        }
    }
}