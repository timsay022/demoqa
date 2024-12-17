package utils;

import com.github.javafaker.Faker;

public class RandomUtils {
    Faker faker = new Faker();

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getPhoneNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public String getDayOfBirth() {
        return String.format("%02d", faker.number().numberBetween(1, 28));
    }

    public String getMonthOfBirth() {
        return faker.options().option("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
    }

    public String getYearOfBirth() {
        return String.format("%s", faker.number().numberBetween(1924,2024));
    }

    public String getSubjects() {
        return faker.options().option("Hindi", "English", "Maths", "Physics", "Chemistry", "Biology",
                "Computer Science", "Commerce", "Accounting", "Economics", "Arts", "Social Studies", "History",
                "Civics");
    }

    public String getHobbies() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public String getUserPicture() {
        return faker.options().option("img.png", "img2.png");
    }

    public String getUserAddress() {
        return faker.address().fullAddress();
    }

    public String getUserState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public String getCityOfState(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> state;
        };
    }



}
