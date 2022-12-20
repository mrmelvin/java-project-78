package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    private boolean availableChecking = false;

    private String patternString = "";

    private Integer minimumStringLength = 0;


    public StringSchema() {
    }

    public StringSchema required() {
        this.availableChecking = true;
        return this;
    }
    public StringSchema contains(String contStr) {
        this.patternString = contStr;
        predicates.add(checkContains);
        return this;
    }

    public StringSchema minLength(Integer len) {
        this.minimumStringLength = len;
        predicates.add(checkLength);
        return this;
    }


    Predicate<String> checkContains = s -> s.contains(this.patternString);
    Predicate<String> checkLength = s -> s.length() >= this.minimumStringLength;
    Predicate<String> availableCheck = s -> s.length() > 0;
    Predicate<String> nonAvailableCheck = s -> s.length() > -1;


    public boolean isValid(Object obj) {
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
