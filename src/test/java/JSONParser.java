import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONParser {


    private ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    void parsingJSONGSONTest() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream is = cl.getResourceAsStream("human.json"); InputStreamReader isr = new InputStreamReader(is)) {
            JsonObject jsonObject = gson.fromJson(isr, JsonObject.class);
            Assertions.assertTrue(jsonObject.get("isClever").getAsBoolean());
            Assertions.assertEquals(37, jsonObject.get("age").getAsInt());
        }
    }

    @Test
    void parsingJSONJacksonTest() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream is = cl.getResourceAsStream("human.json"); InputStreamReader isr = new InputStreamReader(is)) {
            Human human = gson.fromJson(isr, Human.class);
            Assertions.assertTrue(human.isClever);
            Assertions.assertEquals(37, human.age);
        }
    }
}
