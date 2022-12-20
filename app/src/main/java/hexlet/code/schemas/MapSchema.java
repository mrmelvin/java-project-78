package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Objects;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    private boolean availableChecking = false;
    private Integer size = 0;
    private Map<String, BaseSchema> exampleMap = new HashMap<>();

    public final MapSchema required() {
        this.availableChecking = true;
        return this;
    }

    public final MapSchema sizeof(Integer mapSize) {
        this.size = mapSize;
        predicates.add(checkSizeOf);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> patternMap) {
        this.availableChecking = true;
        this.exampleMap = patternMap;
        predicates.add(checkShape);
        return this;
    }

    private Predicate<Map> checkSizeOf = map -> map.size() == this.size;
    private Predicate<Map> defaultCheck = map -> !Objects.equals(map, null);
    private Predicate<Map> checkShape = map -> predicattedCheckShape(map);


    public final boolean predicattedCheckShape(Map<String, Object> testingMap) {
        for (var elem: testingMap.entrySet()) {
            if (!this.exampleMap.get(elem.getKey()).isValid(elem.getValue())) {
                return false;
            }
        }
        return true;
    }


    @Override
    public final boolean isValid(Object obj) {
        boolean validation = true;
        Map<String, Object> currentMap = new HashMap<>();
        if (obj instanceof Map | Objects.equals(obj, null)) {
            if (this.availableChecking) {
                currentMap = (Map) obj;
                predicates.add(defaultCheck);
                for (var predicate : predicates) {
                    validation &= predicate.test(currentMap);
                }
            }
        } else {
            validation = false;
        }
        return validation;
    }
}
