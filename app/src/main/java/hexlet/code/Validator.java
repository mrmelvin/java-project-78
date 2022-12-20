package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
//import hexlet.code.schemas.StringSchema_;

public class Validator {

    public Validator() {
    }

    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }

//    public BaseSchema string() {
//        return new StringSchema();
//    }
//    public BaseSchema number() {
//        return new NumberSchema();
//    }
//
//    public BaseSchema map() {
//        return new MapSchema();
//    }
}
