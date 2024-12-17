package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomUtils;


public class RegistrationWithPageObjectsTests extends TestBase {
    private String firstName,
                    lastName,
                    userEmail,
                    gender,
                    phoneNumber,
                    dayOfBirth,
                    monthOfBirth,
                    yearOfBirth,
                    userSubjects,
                    userHobbies,
                    userPicture,
                    userAddress,
                    userState,
                    userCity;

    RegistrationPage registrationPage = new RegistrationPage();
    RandomUtils randomUtils = new RandomUtils();

    @BeforeEach
    public void setup() {
        firstName = randomUtils.getFirstName();
        lastName = randomUtils.getLastName();
        userEmail = randomUtils.getEmail();
        gender = randomUtils.getGender();
        phoneNumber = randomUtils.getPhoneNumber();
        dayOfBirth = randomUtils.getDayOfBirth();
        monthOfBirth = randomUtils.getMonthOfBirth();
        yearOfBirth = randomUtils.getYearOfBirth();
        userSubjects = randomUtils.getSubjects();
        userHobbies = randomUtils.getHobbies();
        userPicture = randomUtils.getUserPicture();
        userAddress = randomUtils.getUserAddress();
        userState = randomUtils.getUserState();
        userCity = randomUtils.getCityOfState(userState);

    }
    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(userSubjects)
                .setHobbies(userHobbies)
                .setPicture(userPicture)
                .setAddress(userAddress)
                .setLocation(userState, userCity)
                .submitForm();

        registrationPage.checkResponseModal()
                .checkModalTitle("Thanks for submitting the form");

        registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", userSubjects)
                .checkResult("Hobbies", userHobbies)
                .checkResult("Picture", userPicture)
                .checkResult("Address", userAddress)
                .checkResult("State and City", userState + " " + userCity);
    }

    @Test
    void fillRequiredFieldsTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submitForm();

        registrationPage.checkResponseModal()
                .checkModalTitle("Thanks for submitting the form");

        registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber);
    }

    @Test
    void emptyLastNameTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submitForm();

        registrationPage.checkLastUserField();
    }
}
