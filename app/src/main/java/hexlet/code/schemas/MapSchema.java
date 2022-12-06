package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema {

    private boolean availableChecking = false;
    private boolean validation = false;
    private Integer size = 0;
    private Map<Object, Object> currentMap = new HashMap<>();

    public MapSchema required() {
        this.availableChecking = true;
        return this;
    }

    public MapSchema sizeof(Integer mapSize) {
        this.size = mapSize;
        return this;
    }

    public boolean getSizeOf(Map someMap) {
        return someMap.size() == this.size;
    }

    public boolean isValid(Object obj) {
        BaseSchema schema = new BaseSchema();
        if (!this.availableChecking) {
            validation = true;
        } else if (obj instanceof Map<?, ?>) {
            currentMap = (Map) obj;
            validation = getSizeOf(currentMap);
        } else {
            validation = false;
        }
        return validation;
    }
}
