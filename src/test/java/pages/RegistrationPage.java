package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.LocationComponent;
import pages.components.TableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            userSubjects = $("#subjectsInput"),
            userHobbies = $("#hobbiesWrapper"),
            calendarInput = $("#dateOfBirthInput"),
            userPictureInput = $("#uploadPicture"),
            userAddress = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitWrapper = $("#submit"),
            modalForm = $(".modal-dialog"),
            modalFormTitle = $("#example-modal-sizes-title-lg");

    CalendarComponent calendarComponent = new CalendarComponent();
    LocationComponent locationComponent = new LocationComponent();
    TableComponent tableComponent = new TableComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;
    }

    public RegistrationPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String value) {
        userSubjects.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobbies (String value) {
        userHobbies.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setPicture (String filePath) {
        userPictureInput.uploadFromClasspath(filePath);

        return this;
    }

    public RegistrationPage setAddress(String value) {
        userAddress.setValue(value);

        return this;
    }

    public RegistrationPage setLocation(String state, String city) {
        stateInput.click();
        locationComponent.setLocation(state);
        cityInput.click();
        locationComponent.setLocation(city);

        return this;
    }

    public void submitForm () {
        submitWrapper.click();
    }

    public RegistrationPage checkResponseModal () {
        modalForm.should(visible);

        return this;
    }

    public RegistrationPage checkModalTitle (String title) {
        modalFormTitle.shouldHave(text(title));

        return this;
    }


    public RegistrationPage checkResult(String key, String value) {
        tableComponent.getCellValueByKey(key, value);

        return this;
    }

    public RegistrationPage checkLastUserField() {
        lastNameInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)")); // Проверка на hex #dc3545 в формате rgb
        return this;
    }
}
