package day06;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

public class SerializationDeserializationTest {

    // POJO -> Json
    @Test
    public void testSerialization() throws JsonProcessingException {

        // Creating Java object using POJO class
        String[] courses = {"Java", "SQL", "Selenium"};
        Student student = new Student("Burak", "Ankara", "8889996655", courses);

        // Convert POJO -> Json
        ObjectMapper mapper = new ObjectMapper();
        String jsonStudent = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println(jsonStudent);

    }

    // Json -> POJO
    @Test
    public void testDeserialization() throws JsonProcessingException {

        // Creating Json
       String json = "{\n" +
               "  \"name\" : \"Burak\",\n" +
               "  \"location\" : \"Ankara\",\n" +
               "  \"phone\" : \"8889996655\",\n" +
               "  \"courses\" : [ \"Java\", \"SQL\", \"Selenium\" ]\n" +
               "}\n";

        // Convert Json -> POJO
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(json, Student.class);
        System.out.println(student.name());
        //System.out.println(student);

    }
}
