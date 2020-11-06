package hu.elte.fswp.theater_booking.utility;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static <T> List<T> ConvertIterableToList(Iterable<T> iterable) {
        List<T> retval = new ArrayList<T>();
        for (T val : iterable) {
            retval.add(val);
        }
        return retval;
    }
}
