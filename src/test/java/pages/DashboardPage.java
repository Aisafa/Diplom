package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private static SelenideElement heading = $(".heading_size_l");
    private static SelenideElement preview = $(".Order_cardPreview__47B2k");
    private static SelenideElement paymentButton = $$(".button .button__text").find(exactOwnText("Купить"));
    private static SelenideElement creditButton = $$(".button .button__text").find(text("кредит"));

    public DashboardPage() {
        heading.shouldBe(visible).shouldHave(exactOwnText("Путешествие дня"));
        preview.shouldBe(visible).find(String.valueOf(exactOwnText("Марракэш")));
    }

    public DebitPage openDebitPage() {
        paymentButton.click();
        return new DebitPage();
    }

    public CreditPage openCreditPage() {
        creditButton.click();
        return new CreditPage();
    }
}
