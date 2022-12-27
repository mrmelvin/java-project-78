package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    protected List<Predicate> predicates = new ArrayList<>();


    public final boolean isValid(Object obj) {
        boolean validation = true;
        for (var predicate: predicates) {
            if (!predicate.test(obj)) {
                validation = false;
                break;
            }
        }
        return validation;
    };

}
