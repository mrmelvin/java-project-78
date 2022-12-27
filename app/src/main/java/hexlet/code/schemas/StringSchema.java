package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class StringSchema extends BaseSchema {


    public final StringSchema required() {
        Predicate<Object> checkRequired = str -> (!Objects.equals(str, null) && (str instanceof String))
                                                    ? ((String) str).length() > 0
                                                    : false;
        predicates.add(checkRequired);
        return this;
    }
    public final StringSchema contains(String contStr) {
        Predicate<Object> checkContains = str -> ((str instanceof String) && ((String) str).contains(contStr));
        predicates.add(checkContains);
        return this;
    }

    public final StringSchema minLength(Integer len) {
        Predicate<Object> checkLength = str -> ((str instanceof String) && ((String) str).length() >= len);
        predicates.add(checkLength);
        return this;
    }

}
