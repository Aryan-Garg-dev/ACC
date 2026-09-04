import utility.logger.Logger;
import utility.Pair;
import utility.Validation;
import utility.env.Dotenv;
import utility.env.EnvVar;
import utility.events.EventEmitter;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) {

    Dotenv.config(
      Pair.of("HELLO", new EnvVar<String>(String::new, Validation.regex("[A-Za-z]+"))),
      Pair.of("WORLD", new EnvVar<>(
        v-> Arrays
          .stream(v.split(","))
          .mapToInt(Integer::parseInt)
          .toArray()
      )),
      Pair.of("PORT", new EnvVar<>(Integer::parseInt, 3000))
    );

    Dotenv env = Dotenv.load();

    Logger.log()
      .println(env)
      .println("----------------------------------")
      .println(env.get("HELLO", String.class))
      .println(env.get("WORLD", int[].class))
      .println(env.get("PORT", Integer.class));



    EventEmitter emitter = new EventEmitter();
    emitter.on("hello", (recipients)->Logger.log().print("Hello ").println(recipients));
    emitter.emit("hello", "World", "Everyone");

    
  }
}