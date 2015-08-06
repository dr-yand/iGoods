package client.igooods.model;

import java.util.HashMap;
import java.util.Map;

public enum ServerResponseStatus {
    OK(0), ERROR(-10);

    public final int value;

    private static Map<Integer, ServerResponseStatus> map = new HashMap<Integer, ServerResponseStatus>();

    static {
        for (ServerResponseStatus srs : ServerResponseStatus.values()) {
            map.put(srs.value, srs);
        }
    }

    private ServerResponseStatus(int i) {
        value = i;
    }

    public static ServerResponseStatus valueOf(int i){
        return map.get(i);
    }
}
