package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.model.AccountModel;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class JsonFileParsingTest {
    private final ClassLoader cl = JsonFileParsingTest.class.getClassLoader();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Проверка содержимого Json")
    void jsonParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("SampleJSON.json"),
                StandardCharsets.UTF_8
        )) {
            AccountModel actual = mapper.readValue(reader,AccountModel.class);

            Assertions.assertEquals(201, actual.getId());
            Assertions.assertEquals("Emma", actual.getFirstName());
            Assertions.assertEquals("Johnson",actual.getLastName());
            Assertions.assertEquals("emma.johnson@example.com",actual.getEmail());
            Assertions.assertEquals(27, actual.getAge());
            Assertions.assertEquals("Portland", actual.getAddress().getCity());
            Assertions.assertTrue(actual.getIsActive());
            Assertions.assertEquals("home", actual.getPhoneNumber().getType());
            Assertions.assertNull(actual.getUpdatedAt());

        }
    }
}
