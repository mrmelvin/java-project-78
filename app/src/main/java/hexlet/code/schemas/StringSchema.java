package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    private boolean availableChecking = false;

    private String patternString = "";

    private Integer minimumStringLength = 0;


    public StringSchema() {
    }

    public final StringSchema required() {
        this.availableChecking = true;
        return this;
    }
    public final StringSchema contains(String contStr) {
        this.patternString = contStr;
        predicates.add(checkContains);
        return this;
    }

    public final StringSchema minLength(Integer len) {
        this.minimumStringLength = len;
        predicates.add(checkLength);
        return this;
    }


    private Predicate<String> checkContains = s -> s.contains(this.patternString);
    private Predicate<String> checkLength = s -> s.length() >= this.minimumStringLength;
    private Predicate<String> availableCheck = s -> s.length() > 0;
    private Predicate<String> nonAvailableCheck = s -> s.length() > -1;


    @Override
    public final boolean isValid(Object obj) {
        boolean validation = true;
        String inputString;
        if (obj instanceof String | Objects.equals(obj, null)) {
            if (this.availableChecking) {
                if (Objects.equals(obj, null)) {
                    validation = false;
                } else {
                    inputString = obj.toString();
                    predicates.add(availableCheck);
                    for (var predicate : predicates) {
                        validation &= predicate.test(inputString);
                    }
                }
            } else if (Objects.equals(obj, null)) {
                validation = true;
            } else {
                inputString = obj.toString();
                predicates.add(nonAvailableCheck);
                for (var predicate : predicates) {
                    validation &= predicate.test(inputString);
                }
            }
        } else {
            validation = false;
        }
        return validation;
    }

}
