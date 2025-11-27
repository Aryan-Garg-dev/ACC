package competitive_coding;

import utility.Console;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Schedule {
  public record Activity(int start, int finish) {
    public String toString(){
      return String.format("Activity{start=%d, finish=%d}", start, finish);
    }
  }
  public static void selectActivities(List<Activity> activities){
    activities.sort(Comparator.comparingInt(a -> a.finish));
    Activity lastSelected = activities.getFirst();
    System.out.println(lastSelected);
    int lastFinish = lastSelected.finish;
    for (int i = 1; i < activities.size(); i++) {
      Activity currentActivity = activities.get(i);
      if (currentActivity.start > lastFinish) {
        lastFinish = currentActivity.finish;
        System.out.println(currentActivity);
      }
    }
  }

  public static List<Activity> list(int[] start, int[] finish){
    List<Activity> activities = new ArrayList<>();
    for (int i = 0; i < start.length; i++){
      activities.add(i, new Activity(start[i], finish[i]));
    }
    return activities;
  }
}
