package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema implements BasicValidation {

    @Override
    public void defaultCheck() {
        Predicate<Object> defaultCheck = obj -> (obj instanceof Integer | Objects.equals(obj, null));
        predicates.add(defaultCheck);
    }

    public final NumberSchema required() {
        Predicate<Object> checkRequired = i -> !Objects.equals(i, null);
        predicates.add(checkRequired);
        return this;
    }

    public final NumberSchema positive() {
        Predicate<Object> checkPositive = i -> ((i instanceof Integer)
                                                    ? ((Integer) i > 0)
                                                    : (Objects.equals(i, null)));
        predicates.add(checkPositive);
        return this;
    }

    public final NumberSchema range(Integer minimum, Integer maximum) {
        Predicate<Object> checkRange = i -> ((i instanceof Integer)
                                                && (((Integer) i >= minimum) & ((Integer) i <= maximum)));
        predicates.add(checkRange);
        return this;
    }

}
