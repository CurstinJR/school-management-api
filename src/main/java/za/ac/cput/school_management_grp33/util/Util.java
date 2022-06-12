package za.ac.cput.school_management_grp33.util;


import org.springframework.util.StringUtils;

/**
 * Utility Class, also known as Helper class.
 * It contains a bunch of related methods,
 * so they can be reused across the application.
 */
public final class Util {
    public static boolean isEmptyOrNull(String str) {
        return StringUtils.hasText(str);

    }
    public static String setEmptyIfNull(String str) {
    if(isEmptyOrNull(str)) return "";

        return str;
    }
    public static void  checkStringParam(String paramName, String paramValue){
        if(isEmptyOrNull(paramValue))
            throw new IllegalArgumentException(String.format("invalid value for param: %s",paramName));
    }
}
