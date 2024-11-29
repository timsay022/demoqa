package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SortAssertions {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void haveJUnitExampleCode() {
        open("/");

        $(".search-input").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$("[data-testid='results-list']").first().$("a").click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
        $("#wiki-tab").click();

        $(".markdown-body").shouldHave(text("Soft assertions"));
        $(".markdown-body").find(byText("Soft assertions")).click();

        $("#user-content-3-using-junit5-extend-test-class").preceding(0).shouldHave(text("JUnit5"));
        $("#user-content-3-using-junit5-extend-test-class").parent().sibling(0).shouldBe(visible);
        $("#user-content-3-using-junit5-extend-test-class").parent().sibling(0).find("pre").shouldBe(visible);
        sleep(5000);
    }
}
