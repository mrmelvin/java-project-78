package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @Test
    void testWithoutRequired() throws Exception {
        Validator v1 = new Validator();
        StringSchema schema = v1.string();
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid("Qwerty")).isTrue();
        assertThat(schema.isValid(123)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    void testWithRequired() throws Exception {
        Validator v2 = new Validator();
        StringSchema schema = v2.string();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(123)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("Test string")).isTrue();
        assertThat(schema.isValid("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")).isTrue();
    }
}
