package practice.systems.user_management;

import java.util.Set;

public class User {
  private String name;
  private boolean active;
  private Set<String> roles;

  public User(String name, boolean active, Set<String> roles) {
    this.name = name;
    this.active = active;
    this.roles = roles;
  }

  public String getName() {
    return name;
  }

  public boolean isActive() {
    return active;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "User{" +
      "name='" + name + '\'' +
      ", active=" + active +
      ", roles=" + roles +
      '}';
  }
}
