package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomUtils;

import static io.qameta.allure.Allure.step;

@Tag("demoqa")
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
                    userCity,
                    modalTitle;

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
        modalTitle = "Thanks for submitting the form";

    }


    @Test
    void fillFormTest() {
        step("Открыть форму", () -> {
            registrationPage.openPage();
            registrationPage.removeBanners();
        });

        step("Заполнить форму", () -> {
            registrationPage.setFirstName(firstName)
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
        });

        step("Проверить модалку результатов на наличие заголовка", () -> {
            registrationPage.checkResponseModal()
                    .checkModalTitle(modalTitle);
        });

        step("Проверить форму на корректность", () -> {
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
        });
    }

    @Test
    void fillRequiredFieldsTest() {
        step("Открыть форму", () -> {
            registrationPage.openPage()
                    .removeBanners();
        });

        step("Заполнить обязательные поля формы", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(userEmail)
                    .setGender(gender)
                    .setUserNumber(phoneNumber)
                    .submitForm();
        });


        step("Проверить модалку результатов на наличие заголовка", () -> {
            registrationPage.checkResponseModal()
                    .checkModalTitle(modalTitle);
        });

        step("Проверить форму на корректность", () -> {
            registrationPage.checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Student Email", userEmail)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", phoneNumber);
        });
    }

    @Test
    void emptyLastNameTest() {
        step("Открыть форму", () -> {
            registrationPage.openPage()
                    .removeBanners();
        });

        step("Заполнить обязательные поля формы, кроме LastName", () -> {
            registrationPage.setFirstName(firstName)
                    .setGender(gender)
                    .setUserNumber(phoneNumber)
                    .submitForm();
        });

        step("Проверить заполненность поля LastName", () -> {
            registrationPage.checkLastUserField();
        });

    }
}
