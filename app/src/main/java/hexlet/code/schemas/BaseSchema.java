package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema implements BasicValidation {

    protected List<Predicate> predicates = new ArrayList<>();

    public void defaultCheck() {
        Predicate<Object> defaultCheck = obj -> obj instanceof Object;
        predicates.add(defaultCheck);
    }

    public boolean isValid(Object obj) {
        boolean validation = true;
        defaultCheck();
        System.out.println(predicates);
        for (var predicate: predicates) {
            if (!predicate.test(obj)) {
                validation = false;
                break;
            }
        }
        return validation;
    };

}
