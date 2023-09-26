import java.util.Random;
import java.util.Set;

public class CollectionsHelper {

    public static <T> T getRandomFrom(Set<T> set) {
        var rand = new Random();
        var row = rand.nextInt(0, set.size());
        return set.stream().toList().get(row);
    }

}
