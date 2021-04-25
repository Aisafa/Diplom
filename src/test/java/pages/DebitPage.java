package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitPage {
    private static SelenideElement heading = $$(".heading_size_m")
            .findBy(text("Оплата"));
    private static SelenideElement cardNumberField = $$(".input__control").get(0);
    private static SelenideElement cardNumberFieldError = $$(".input__sub").get(0);
    private static SelenideElement monthField = $$(".input__control").get(1);
    private static SelenideElement monthFieldError = $$(".input__sub").get(1);
    private static SelenideElement yearField = $$(".input__control").get(2);
    private static SelenideElement yearFieldError = $$(".input__sub").get(2);
    private static SelenideElement ownerField = $$(".input__control").get(3);
    private static SelenideElement ownerFieldError = $$(".input__sub").get(3);
    private static SelenideElement cvcField = $$(".input__control").get(4);
    private static SelenideElement cvcFieldError = $$(".input__sub").get(4);
    private static SelenideElement proceedButton = $(".form-field .button");
    private static SelenideElement succeedNotification = $(".icon_name_ok");
    private static SelenideElement succeedNotificationTitle = $$(".notification__title").findBy(text("Успешно"));
    private static SelenideElement succeedMessage = $$(".notification__content").findBy(text("одобрена"));
    private static SelenideElement errorNotification = $(".icon_name_error");
    private static SelenideElement errorNotificationTitle = $$(".notification__title").findBy(text("Ошибка"));
    private static SelenideElement errorMessage = $$(".notification__content").findBy(text("отказал"));

    private final int delay = 25;

    public void successfulPayment(DataHelper.RequiredFields fields) {
        cardNumberField.setValue(fields.getCardNumber());
        monthField.setValue(fields.getMonth());
        yearField.setValue(fields.getYear());
        ownerField.setValue(fields.getOwner());
        cvcField.setValue(fields.getCvcCvv());
        proceedButton.click();
        succeedNotification.shouldBe(visible, Duration.ofSeconds(delay));
        succeedNotificationTitle.shouldBe(visible, Duration.ofSeconds(delay)).shouldHave(exactOwnText("Успешно"));
        succeedMessage.shouldBe(visible, Duration.ofSeconds(delay)).shouldHave(exactOwnText("Операция одобрена Банком."));
    }

    public void DeclinedCardPaymentError(DataHelper.RequiredFields fields) {
        cardNumberField.setValue(fields.getCardNumber());
        monthField.setValue(fields.getMonth());
        yearField.setValue(fields.getYear());
        ownerField.setValue(fields.getOwner());
        cvcField.setValue(fields.getCvcCvv());
        proceedButton.click();
        errorNotification.shouldBe(visible, Duration.ofSeconds(delay));
        errorNotificationTitle.shouldBe(visible, Duration.ofSeconds(delay)).shouldHave(exactOwnText("Ошибка"));
        errorMessage.shouldBe(visible, Duration.ofSeconds(delay)).
                shouldHave(exactOwnText("Банк отказал в проведении операции."));
    }

    public void submittingAnEmptyForm() {
        proceedButton.click();
        cardNumberFieldError.shouldBe(visible).shouldHave(exactOwnText("Неверный формат"));
        monthFieldError.shouldBe(visible).shouldHave(exactOwnText("Неверный формат"));
        yearFieldError.shouldBe(visible).shouldHave(exactOwnText("Неверный формат"));
        ownerFieldError.shouldBe(visible).shouldHave(exactOwnText("Поле обязательно для заполнения"));
        cvcFieldError.shouldBe(visible).shouldHave(exactOwnText("Неверный формат"));
    }

    public void PaymentWithInvalidOwnerWithNumber(DataHelper.RequiredFields fields) {
        SelenideElement ownerFieldError = $$(".input__sub").get(0);
        cardNumberField.setValue(fields.getCardNumber());
        monthField.setValue(fields.getMonth());
        yearField.setValue(fields.getYear());
        ownerField.setValue(fields.getOwner());
        cvcField.setValue(fields.getCvcCvv());
        proceedButton.click();
        ownerFieldError.shouldBe(visible).shouldHave(exactOwnText("Поддерживается только латиница"));
    }

    public void PaymentWithSpecChar(DataHelper.RequiredFields fields) {
        SelenideElement ownerFieldError = $$(".input__sub").get(0);
        cardNumberField.setValue(fields.getCardNumber());
        monthField.setValue(fields.getMonth());
        yearField.setValue(fields.getYear());
        ownerField.setValue(fields.getOwner());
        cvcField.setValue(fields.getCvcCvv());
        proceedButton.click();
        ownerFieldError.shouldBe(visible).shouldHave(exactOwnText("Поддерживается только латиница"));
    }

    public void PaymentWithCyrillic(DataHelper.RequiredFields fields) {
        SelenideElement ownerFieldError = $$(".input__sub").get(0);
        cardNumberField.setValue(fields.getCardNumber());
        monthField.setValue(fields.getMonth());
        yearField.setValue(fields.getYear());
        ownerField.setValue(fields.getOwner());
        cvcField.setValue(fields.getCvcCvv());
        proceedButton.click();
        ownerFieldError.shouldBe(visible).shouldHave(exactOwnText("Поддерживается только латиница"));
    }

    public void PaymentWithInValidMonth(DataHelper.RequiredFields fields) {
        SelenideElement monthFieldError = $$(".input__sub").get(0);
        cardNumberField.setValue(fields.getCardNumber());
        monthField.setValue(fields.getMonth());
        yearField.setValue(fields.getYear());
        ownerField.setValue(fields.getOwner());
        cvcField.setValue(fields.getCvcCvv());
        proceedButton.click();
        monthFieldError.shouldBe(visible).shouldHave(exactOwnText("Неверно указан срок действия карты"));
    }

    public void PaymentWithInValidLastYear(DataHelper.RequiredFields fields) {
        SelenideElement yearFieldError = $$(".input__sub").get(0);
        cardNumberField.setValue(fields.getCardNumber());
        monthField.setValue(fields.getMonth());
        yearField.setValue(fields.getYear());
        ownerField.setValue(fields.getOwner());
        cvcField.setValue(fields.getCvcCvv());
        proceedButton.click();
        yearFieldError.shouldBe(visible).shouldHave(exactOwnText("Истёк срок действия карты"));
    }

    public void PaymentWithInValidMoreYear(DataHelper.RequiredFields fields) {
        SelenideElement yearFieldError = $$(".input__sub").get(0);
        cardNumberField.setValue(fields.getCardNumber());
        monthField.setValue(fields.getMonth());
        yearField.setValue(fields.getYear());
        ownerField.setValue(fields.getOwner());
        cvcField.setValue(fields.getCvcCvv());
        proceedButton.click();
        yearFieldError.shouldBe(visible).shouldHave(exactOwnText("Неверно указан срок действия карты"));
    }

    public void PaymentWithInvalidCvc(DataHelper.RequiredFields fields) {
        SelenideElement cvcFieldError = $$(".input__sub").get(0);
        cardNumberField.setValue(fields.getCardNumber());
        monthField.setValue(fields.getMonth());
        yearField.setValue(fields.getYear());
        ownerField.setValue(fields.getOwner());
        cvcField.setValue(fields.getCvcCvv());
        proceedButton.click();
        cvcFieldError.shouldBe(visible).shouldHave(exactOwnText("Неверный формат"));
    }

    public void PaymentWithInvalidCard(DataHelper.RequiredFields fields) {
        SelenideElement cardNumberFieldError = $$(".input__sub").get(0);
        cardNumberField.setValue(fields.getCardNumber());
        monthField.setValue(fields.getMonth());
        yearField.setValue(fields.getYear());
        ownerField.setValue(fields.getOwner());
        cvcField.setValue(fields.getCvcCvv());
        proceedButton.click();
        errorNotification.shouldBe(visible, Duration.ofSeconds(delay));
        errorNotificationTitle.shouldBe(visible, Duration.ofSeconds(delay)).shouldHave(exactOwnText("Ошибка"));
        cardNumberFieldError.shouldBe(visible).shouldHave(exactOwnText("Номер карты введён неверно"));

    }
}

