package day07;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerDataGeneratorTest {
    @Test
    public void testFakerDataGenerator() {
        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String cellPhone = faker.phoneNumber().cellPhone();

        System.out.println("Full Name: " + fullName);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Cell Phone Number: " + cellPhone);

    }
}
