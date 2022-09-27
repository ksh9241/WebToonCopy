package project.toy.webtoon_copy.util;

public class CheckUtils {

    public static boolean isEmpty(Object obj) {
        String val = String.valueOf(obj); // String.valueOf의 값이 null일 때 String Type의 null로 처리됨.
        if ("null".equals(val) || val.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}
