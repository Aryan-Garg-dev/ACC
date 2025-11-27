package leetcode;

import java.util.*;

public class Q1600 {
  static class ThroneInheritance {

    private static class King {
      String name;
      boolean isDead;
      List<King> successors;

      King(String name){
        this.name = name;
        this.isDead = false;
        this.successors = new ArrayList<>();
      }

      static King searchMember(King king, String name){
        if (king == null) return null;
        if (king.name.equals(name)) return king;
        if (king.successors.isEmpty()) return null;
        for (var successor: king.successors){
          King member = searchMember(successor, name);
          if (member != null) return member;
        }
        return null;
      }

      static void setAliveOrder(King king, List<String> order){
        if (king == null) return;
        if (!king.isDead) order.add(king.name);
        if (king.successors.isEmpty()) return;
        for (var successor: king.successors){
          setAliveOrder(successor, order);
        }
      }
    }

    private final King king;
    private final Map<String, King> data;

    public ThroneInheritance(String kingName) {
      king = new King(kingName);
      data = new HashMap<>();
      data.put(kingName, king);

    }

    public void birth(String parentName, String childName) {
      King parent = data.get(parentName);
      King child = new King(childName);
      parent.successors.add(child);
      data.put(childName, child);
    }

    public void death(String name) {
      King member = data.get(name);
      if (member != null) member.isDead = true;
    }

    public List<String> getInheritanceOrder() {
      List<String> order = new ArrayList<>();
      King.setAliveOrder(king, order);
      return order;
    }
  }
}
