package practice.systems.user_management;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
  public static void main(String[] args) {

    List<User> users = new ArrayList<>(List.of(
      new User("John", true, Set.of("ADMIN", "USER")),
      new User("Alice", true, Set.of("USER")),
      new User("Bob", false, Set.of("USER")),
      new User("Charlie", true, Set.of("MANAGER"))
    ));

    // functional interface
    Function<String, User> findUser = (name) ->
      users.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    System.out.println(findUser.apply("John"));

    Supplier<List<User>> findActiveUsers = () -> users.stream().filter(User::isActive).toList();
    findActiveUsers.get().forEach(System.out::println);

    Predicate<User> isAdmin = user -> user.getRoles().contains("ADMIN");
    isAdmin.test(findUser.apply("Charlie"));

    Consumer<User> setInactive = user -> user.setActive(false);
    setInactive.accept(findUser.apply("Bob"));

    // Print Active User
    users.removeIf(user -> !user.isActive());
    for (User user : users) System.out.println(user);

    // Count User Per Role
    Map<String, Integer> roleCount = new HashMap<>();
    for (User user: users){
      for (String role: user.getRoles())
        roleCount.merge(role, 1, Integer::sum);
    }
    System.out.println(roleCount);

    users.forEach(setInactive);
    users.stream().filter(isAdmin).forEach(System.out::println);
  }
}
