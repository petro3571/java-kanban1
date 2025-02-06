package manager;

import typetask.Task;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private final List<Task> historyStorage = new LinkedList<>();
    private static final Integer MAXSIZELIST = 10;

    @Override
    public void addTask(Task task) {
        if(task != null) {
            historyStorage.add(task.getSnapshot());
            if(historyStorage.size() > MAXSIZELIST) {
                historyStorage.remove(0);
            }
        }
    }

    @Override
    public List<Task> getHistory() {
        return new LinkedList<>(historyStorage);
    }
}
