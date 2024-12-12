package pages.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LocationComponent {

    public void setLocation(String location) {
        $("#stateCity-wrapper").$(byText(location)).click();
    }
}
