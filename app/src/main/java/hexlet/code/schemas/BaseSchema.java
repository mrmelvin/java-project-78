package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    protected List<Predicate> predicates = new ArrayList<>();
    public boolean isValid(Object obj) {
        return this.isValid(obj);
    }

}
