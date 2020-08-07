package cn.org.rookie.common.component;

import java.util.UUID;

public class CommonUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
