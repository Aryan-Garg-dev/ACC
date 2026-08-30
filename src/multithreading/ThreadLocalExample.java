package multithreading;

class User {
  Long id;
  String name;
}

class UserContextHolder {
  public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class EntryService {
  public static User getUser() {
    User u = new User();
    u.id = 1L;
    u.name = "John Doe";
    return u;
  }

  public void process(){
    User user = getUser();
    UserContextHolder.holder.set(user); // set user for this thread
  }
}

class NextService1 {
  public void process(){
    User user = UserContextHolder.holder.get(); // get user for this thread
    // process user
  }
}

class LastService {
  public void process(){
    // cleanup (last service, user no longer required)
    UserContextHolder.holder.remove(); // remove user for this thread
  }
}

public class ThreadLocalExample {}
