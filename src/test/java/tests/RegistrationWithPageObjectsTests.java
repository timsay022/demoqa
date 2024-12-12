package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;


public class RegistrationWithPageObjectsTests extends TestBase {


    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName("Alex")
                .setLastName("Egorov")
                .setEmail("alex@egorov.com")
                .setGender("Other")
                .setUserNumber("1234567890")
                .setDateOfBirth("30", "July", "2008")
                .setSubjects("Math")
                .setHobbies("Sports")
                .setPicture("img.png")
                .setAddress("Some address 1")
                .setLocation("NCR", "Delhi")
                .submitForm();

        registrationPage.checkResponseModal()
                .checkModalTitle("Thanks for submitting the form");

        registrationPage.checkResult("Student Name", "Alex Egorov")
                .checkResult("Student Email", "alex@egorov.com")
                .checkResult("Gender", "Other")
                .checkResult("Mobile", "1234567890")
                .checkResult("Date of Birth", "30 July,2008")
                .checkResult("Subjects", "Math")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "img.png")
                .checkResult("Address", "Some address 1")
                .checkResult("State and City", "NCR Delhi");
    }

    @Test
    void fillRequiredFieldsTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName("Alex")
                .setLastName("Egorov")
                .setEmail("alex@egorov.com")
                .setGender("Other")
                .setUserNumber("1234567890")
                .submitForm();

        registrationPage.checkResponseModal()
                .checkModalTitle("Thanks for submitting the form");

        registrationPage.checkResult("Student Name", "Alex Egorov")
                .checkResult("Student Email", "alex@egorov.com")
                .checkResult("Gender", "Other")
                .checkResult("Mobile", "1234567890");
    }

    @Test
    void emptyLastNameTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName("Alex")
                .setLastName("Egorov")
                .setGender("Other")
                .setUserNumber("1234567890")
                .submitForm();

        registrationPage.checkLastUserField();
    }
}
