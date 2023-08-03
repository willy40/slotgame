package game.Extemsions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneDArrayExtension {
    public static <T> List<T> oneDArrayToList(T[] twoDArray) {
        List<T> list = new ArrayList<T>();
        for (T array : twoDArray) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }
}
