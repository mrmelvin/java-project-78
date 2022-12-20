package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    private boolean availableChecking = false;
    private boolean positiveAvailable = false;
    private Integer rangeMinimum = Integer.MIN_VALUE;
    private Integer rangeMaximum = Integer.MAX_VALUE;


    public NumberSchema required() {
        this.availableChecking = true;
        return this;
    }

    public NumberSchema positive() {
        this.positiveAvailable = true;
        predicates.add(checkPositive);
        return this;
    }

    public NumberSchema range(Integer minimum, Integer maximum) {
        rangeMinimum = minimum;
        rangeMaximum = maximum;
        predicates.add(checkRange);
        return this;
    }

    Predicate<Integer> checkPositive = i -> i > 0;
    Predicate<Integer> checkRange = i -> (i >= this.rangeMinimum) & (i <= this.rangeMaximum);
    Predicate<Integer> defaultCheck = i -> !Objects.equals(i, null);

    public boolean isValid(Object obj) {
        boolean validation = true;
        Integer currentNumber;
        if (obj instanceof Integer | Objects.equals(obj, null)) {
            if (this.availableChecking) {
                currentNumber = (Integer) obj;
                predicates.add(defaultCheck);
                for (var predicate : predicates) {
                    validation &= predicate.test(currentNumber);
                }
            } else if (Objects.equals(obj, null)) {
                validation = true;
            } else {
                currentNumber = (Integer) obj;
                predicates.add(defaultCheck);
                for (var predicate : predicates) {
                    validation &= predicate.test(currentNumber);
                }
            }
        } else {
            validation = false;
        }
        return validation;
    }
}
