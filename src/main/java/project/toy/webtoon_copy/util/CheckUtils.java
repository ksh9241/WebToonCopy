package project.toy.webtoon_copy.util;

public class CheckUtils {

    public static boolean isEmpty(Object obj) {
        String val = String.valueOf(obj);
        if (val == null || val.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}
