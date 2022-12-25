package hexlet.code.schemas;

import java.util.Objects;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema implements BasicValidation {

    @Override
    public void defaultCheck() {
        Predicate<Object> defaultCheck = obj -> (obj instanceof Map<?, ?> | Objects.equals(obj, null));
        predicates.add(defaultCheck);
    }

    public final MapSchema required() {
        Predicate<Object> checkRequired = someMap -> (!Objects.equals(someMap, null)
                                                            & someMap instanceof Map<?, ?>);
        predicates.add(checkRequired);
        return this;
    }

    public final MapSchema sizeof(Integer mapSize) {
        Predicate<Object> checkMapSize = someMap -> ((someMap instanceof Map<?, ?>)
                                                        && (((Map) someMap).size() >= mapSize));
        predicates.add(checkMapSize);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> patternMap) {
        Predicate<Object> shapingMap = someMap -> ((someMap instanceof Map<?, ?>)
                                                        && predicattedCheckShape((Map) someMap, patternMap));
        predicates.add(shapingMap);
        return this;
    }


    public final boolean predicattedCheckShape(Map<String, Object> testingMap, Map<String, BaseSchema> patternMap) {
        for (var elem: testingMap.entrySet()) {
            if (!patternMap.get(elem.getKey()).isValid(elem.getValue())) {
                return false;
            }
        }
        return true;
    }

}
