package hexlet.code;

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
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid("Qwerty")).isTrue();
        assertThat(schema.isValid(123)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
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
        assertThat(schema2.isValid("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")).isTrue();
    }

    @Test
    void testNumberWithoutRequired() throws Exception {
        Validator v3 = new Validator();
        NumberSchema schema3 = v3.number();
        assertThat(schema3.isValid(null)).isTrue();
        assertThat(schema3.positive().isValid(null)).isTrue();
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
    void testMapWithoutRequired() throws Exception {
        Validator v5 = new Validator();
        MapSchema schema5 = v5.map();
        assertThat(schema5.isValid(null)).isTrue();
        assertThat(schema5.isValid("String")).isTrue();
        assertThat(schema5.isValid(new HashMap<>())).isTrue();
        assertThat(schema5.isValid(new HashMap<>(Map.of("foo", "bar")))).isTrue();
    }

    @Test
    void testMapWithRequired() throws Exception {
        Validator v6 = new Validator();
        MapSchema schema6 = v6.map();
        schema6.required();
        assertThat(schema6.isValid("String")).isFalse();
        assertThat(schema6.isValid(new HashMap<>())).isTrue();
    }

    @Test
    void testMapWithSize() throws Exception {
        Validator v6 = new Validator();
        MapSchema schema6 = v6.map();
        schema6.required();
        schema6.sizeof(2);
        assertThat(schema6.isValid(new HashMap<>())).isFalse();
        Map<String, String> tempMap = new HashMap<>(Map.of("foo", "bar", "lorem", "ipsum"));
        assertThat(schema6.isValid(tempMap)).isTrue();
    }
}
