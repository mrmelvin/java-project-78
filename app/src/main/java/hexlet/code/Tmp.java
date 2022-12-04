package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class Tmp {

    public static void main(String[] args) {
        Validator v = new Validator();


        StringSchema schema = v.string();
        System.out.println(schema.isValid(1223));
        System.out.println(schema.isValid("1223"));
        schema.required();
        System.out.println("Enable method required()");
        System.out.println(schema.isValid(1223));
        System.out.println(schema.isValid("1223"));
        System.out.println("Check length");
        System.out.println(schema.minLength(3).isValid("abcde"));
        System.out.println(schema.minLength(3).isValid("abc"));
        System.out.println("Check contains substring");
        System.out.println(schema.contains("what").isValid("what does the fox say"));
        System.out.println(schema.contains("xyz").isValid("qwerty"));
        System.out.println("Check contains substring and minLength");
        System.out.println(schema.minLength(5).contains("fox").isValid("fox"));
        System.out.println(schema.minLength(3).contains("qwe").isValid("qwerty"));
    }
}
