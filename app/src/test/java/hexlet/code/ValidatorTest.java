package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @Test
    void testStringWithoutRequired() throws Exception {
        Validator v1 = new Validator();
        StringSchema schema = v1.string();
        assertThat(schema.isValid(123)).isFalse();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid("Qwerty")).isTrue();
    }

    @Test
    void testStringWithRequired() throws Exception {
        Validator v2 = new Validator();
        StringSchema schema2 = v2.string();
        schema2.required();
        assertThat(schema2.isValid(null)).isFalse();
        assertThat(schema2.isValid(123)).isFalse();
        assertThat(schema2.isValid("")).isFalse();
        assertThat(schema2.isValid("Test string")).isTrue();
        assertThat(schema2.isValid("Lorem ipsum dolor sit amet, consectetur adipiscing elit")).isTrue();
    }

    @Test
    void testNumberWithoutRequired() throws Exception {
        Validator v3 = new Validator();
        NumberSchema schema3 = v3.number();
        assertThat(schema3.isValid(null)).isTrue();
        assertThat(schema3.positive().isValid(-9)).isFalse();
    }

    @Test
    void testNumberRange() throws Exception {
        Validator v4 = new Validator();
        NumberSchema schema4 = v4.number();
        schema4.required();
        schema4.range(0, 10);
        assertThat(schema4.isValid(8)).isTrue();
        assertThat(schema4.isValid(11)).isFalse();
    }

    @Test
    void testNumberPositive() throws Exception {
        Validator v5 = new Validator();
        NumberSchema schema5 = v5.number();
        schema5.positive();
        assertThat(schema5.isValid(8)).isTrue();
        assertThat(schema5.isValid(-11)).isFalse();
    }

    @Test
    void testMapWithoutRequired() throws Exception {
        Validator v6 = new Validator();
        MapSchema schema6 = v6.map();
        assertThat(schema6.isValid("String")).isFalse();
        assertThat(schema6.isValid(null)).isTrue();
        assertThat(schema6.isValid(new HashMap<>())).isTrue();
        assertThat(schema6.isValid(new HashMap<>(Map.of("foo", "bar")))).isTrue();
    }

    @Test
    void testMapWithRequired() throws Exception {
        Validator v7 = new Validator();
        MapSchema schema7 = v7.map();
        schema7.required();
        assertThat(schema7.isValid("String")).isFalse();
        assertThat(schema7.isValid(new HashMap<>())).isTrue();
    }

    @Test
    void testMapWithSize() throws Exception {
        Validator v8 = new Validator();
        MapSchema schema8 = v8.map();
        schema8.required();
        schema8.sizeof(2);
        assertThat(schema8.isValid(new HashMap<>())).isFalse();
        Map<String, String> tempMap = new HashMap<>(Map.of("foo", "bar", "lorem", "ipsum"));
        assertThat(schema8.isValid(tempMap)).isTrue();
    }


    @Test
    void testNestedValidation() throws Exception {
        Validator v9 = new Validator();
        MapSchema schema9 = v9.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v9.string().required());
        schemas.put("age", v9.number().positive());
        schema9.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertThat(schema9.isValid(human1)).isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema9.isValid(human2)).isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema9.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertThat(schema9.isValid(human4)).isFalse();

    }
}
