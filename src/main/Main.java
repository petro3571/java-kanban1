package main;

import typetask.Epic;
import typetask.Subtask;
import typetask.Task;
import typetask.Status;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task(Status.NEW, "Купить продукты" , "Лента");
        taskManager.addTask(task1);

        Task task2 = new Task(Status.NEW, "Полный бак", "Крайснефть");
        taskManager.addTask(task2);

        Epic epic1 = new Epic("Новый год", "Подготовка к нг");
        taskManager.addEpic(epic1);

        Subtask subtask1 = new Subtask(Status.NEW, "Выбрать и купить салют", "Салют", epic1.getId());
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask(Status.NEW, "Пригласить близких", "Обзвонить близких", epic1.getId());
        taskManager.addSubtask(subtask2);

        Epic epic2 = new Epic("Переезд", "Подготовка к переезду");
        taskManager.addEpic(epic2);

        Subtask subtask3 = new Subtask(Status.NEW, "Собрать вещи", "Упаковать вещи", epic2.getId());
        taskManager.addSubtask(subtask3);

        System.out.println("Все задачи " + taskManager.getAllTasks());
        System.out.println();
        System.out.println("Все эпики " + taskManager.getAllEpics());
        System.out.println();
        System.out.println("Все подзадачи " + taskManager.getAllSubtasks());
        System.out.println();

        subtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask1);
        subtask2.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask2);

        System.out.println("Обновленный эпик: " + taskManager.getEpicByIndex(epic1.getId()));
        System.out.println();

        taskManager.deleteTaskByIndex(task2.getId());
        taskManager.deleteEpicByIndex(epic2.getId());

        System.out.println("Задачи после удаления " + taskManager.getAllTasks());
        System.out.println();
        System.out.println("Эпики после удаления " + taskManager.getAllEpics());

    }
}
