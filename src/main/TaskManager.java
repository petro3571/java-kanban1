package main;

import typetask.Epic;
import typetask.Subtask;
import typetask.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private final HashMap <Integer, Task> tasks;
    private final HashMap <Integer, Epic> epics;
    private final HashMap <Integer, Subtask> subtasks;
    private int index;

    public TaskManager () {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        index = 1;

    }

    public void addTask (Task task) {
        task.setId(idCounter());
        tasks.put(task.getId(), task);
    }

    public void addEpic (Epic epic) {
        epic.setId(idCounter());
        epics.put(epic.getId(), epic);
    }

    public void addSubtask (Subtask subtask) {
        for (Integer count : epics.keySet()){
            if (count.equals(subtask.getEpicId())) {
                subtask.setId(idCounter());
                subtasks.put(subtask.getId(), subtask);
                getEpicByIndex(count).addSubtasks(subtask);
                getEpicByIndex(count).setEpicStatus();
            }
        }
    }

    public List<Task> getAllTasks(){
        List<Task> taskList = new ArrayList<>();
        for ( Integer count : tasks.keySet()) {
            Task allTask = tasks.get(count);
            taskList.add(allTask);
        }
          return taskList;
    }

    public List<Epic> getAllEpics(){
        List<Epic> epicList = new ArrayList<>();
        for (Integer count: epics.keySet()){
            Epic allEpic = epics.get(count);
            epicList.add(allEpic);
        }
        return epicList;
    }

    public List <Subtask> getAllSubtasks(){
        List<Subtask> subtaskList = new ArrayList<>();
        for (Integer count : subtasks.keySet()) {
            Subtask allSubtask = subtasks.get(count);
            subtaskList.add(allSubtask);
        }
        return subtaskList;
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
            epic.getSubtasks().clear();
            epic.setEpicStatus();
        }
    }

    public Task getTaskByIndex(int id) {
        return tasks.get(id);
    }

    public Epic getEpicByIndex(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskByIndex(int id) {
        return subtasks.get(id);
    }

    public void updateTask(Task task) {
        if (task != null) {
            tasks.put(task.getId(), task);
        }
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        epic.setEpicStatus();
    }

    public void updateSubtask(Subtask subtask) {
        if (subtask != null) {
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.setEpicStatus();
            }
        }
    }

    public void deleteTaskByIndex(int id) {
        tasks.remove(id);
    }

    public void deleteEpicByIndex(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (Subtask subtask : epic.getSubtasks()) {
                subtasks.remove(subtask.getId());
            }
        }
    }

    public void deleteSubtaskByIndex(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.deleteSubtasks(subtask);
                epic.setEpicStatus();
            }
        }
    }

    private int idCounter() {
        return index++;
    }
}