package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Q3408 {
  static class TaskManager {

    static class Task implements Comparable<Task> {
      int userId, taskId, priority;

      public Task(List<Integer> task){
        this.userId = task.get(0);
        this.taskId = task.get(1);
        this.priority = task.get(2);
      }

      public Task(int userId, int taskId, int priority){
        this.userId = userId;
        this.taskId = taskId;
        this.priority = priority;
      }

      @Override
      public int compareTo(Task t){
        if (t.priority == this.priority) return t.taskId - this.taskId;
        return t.priority - this.priority;
      }
    }

    TreeSet<Task> taskSet = new TreeSet<>();
    Map<Integer, Task> taskMap = new HashMap<>();

    public TaskManager(List<List<Integer>> tasks) {
      for (var task: tasks){
        Task t = new Task(task);
        taskSet.add(t);
        taskMap.put(t.taskId, t);
      }
    }

    public void add(int userId, int taskId, int priority) {
      Task t = new Task(userId, taskId, priority);
      taskSet.add(t);
      taskMap.put(taskId, t);
    }

    public void edit(int taskId, int newPriority) {
      Task old = taskMap.get(taskId);
      taskSet.remove(old);
      Task updated = new Task(taskId, old.userId, newPriority);
      taskSet.add(updated);
      taskMap.put(taskId, updated);
    }

    public void rmv(int taskId) {
      Task t = taskMap.get(taskId);
      taskSet.remove(t);
      taskMap.remove(taskId);

    }

    public int execTop() {
      if (taskSet.isEmpty()) return -1;
      Task top = taskSet.first();
      taskSet.remove(top);
      taskMap.remove(top.taskId);
      return top.userId;
    }
  }
}
