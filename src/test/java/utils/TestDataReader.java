package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class TestDataReader {

    private static JsonNode testData;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream is = TestDataReader.class
                    .getClassLoader()
                    .getResourceAsStream("testdata/authData.json");

            if (is == null) {
                throw new RuntimeException("authData.json not found in resources");
            }

            testData = mapper.readTree(is);


        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    public static String get(String userType, String field) {
        return testData.get(userType).get(field).asText();
    }
}
