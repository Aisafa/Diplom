package test;

import data.DataHelper;
import data.DbHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TicketBuyCreditTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void submittingAnEmptyForm() {
        new
                DashboardPage().openCreditPage().submittingAnEmptyForm();
    }

    //Successful Payment
    //проверка успошной оплаты
    @Test
    void successfulPayment() {
        new DashboardPage().openCreditPage()
                .successfulPayment(DataHelper.getValidOwner());
        assertEquals("APPROVED", DbHelper.getCreditStatus());
    }

    //проверка успошной оплаты
    @Test
    void successfulPaymentCreditId() {
        new DashboardPage().openCreditPage()
                .successfulPayment(DataHelper.getValidOwner());
        assertNotNull(DbHelper.getCreditId());
    }

    //Declined Payment
    //проверка оплаты c отклноненной картой
    @Test
    void DeclinedCardPaymentErrorOrderId() {
        new DashboardPage().openCreditPage()
                .DeclinedCardPaymentError(DataHelper.getValidValueDeclinedCard());
        assertNull(DbHelper.getOrderId());
    }

    //проверка оплаты c отклноненной картой
    @Test
    void DeclinedCardPaymentErrorCreditID() {
        new DashboardPage().openCreditPage()
                .DeclinedCardPaymentError(DataHelper.getValidValueDeclinedCard());
        assertNotNull(DbHelper.getCreditId());
    }

    //проверка оплаты c отклноненной картой и с не правильным именем владельца
    @Test
    void DeclinedCardPaymentErrorWithInvalidOwnerPaymentID() {
        new DashboardPage().openCreditPage()
                .DeclinedCardPaymentError(DataHelper.getInvalidOwnerWithDeclinedCard());
        assertNotNull(DbHelper.getCreditId());
    }

    //Negative Test
//Проверка оплаты используя невалидное имя владельца с цифрами
    @Test
    void PaymentWithInvalidOwnerWithNumber() {
        new DashboardPage().openCreditPage()
                .PaymentWithInvalidOwnerWithNumber(DataHelper.getInvalidOwnerWithNumber());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты используя невалидное имя владельца соспецсимволами
    @Test
    void PaymentWithInvalidOwnerWithSpecChar() {
        new DashboardPage().openCreditPage()
                .PaymentWithSpecChar(DataHelper.getInvalidOwnerWithSpecChar());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты используя невалидное имя владельца c кириллицей
    @Test
    void PaymentWithInvalidOwnerWithCyrillic() {
        new DashboardPage().openCreditPage()
                .PaymentWithCyrillic(DataHelper.getInvalidOwnerWithCyrillic());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты используя невалидное значение месяца работы карты
    @Test
    void PaymentWithInValidMonth() {
        new DashboardPage().openCreditPage()
                .PaymentWithInValidMonth(DataHelper.getInValidMonth());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты используя невалидное значение года работы карты дальнего будущего
    @Test
    void PaymentWithInValidLastYear() {
        new DashboardPage().openCreditPage()
                .PaymentWithInValidLastYear(DataHelper.getInValidLastYear());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты используя невалидное значение года работы карты прошлого
    @Test
    void PaymentWithInValidMoreYear() {
        new DashboardPage().openCreditPage()
                .PaymentWithInValidMoreYear(DataHelper.getInValidMoreYear());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты с невалидным СVС кодом
    @Test
    void PaymentWithInvalidCvc() {
        new DashboardPage().openCreditPage()
                .PaymentWithInvalidCvc(DataHelper.getInvalidCvc());
        assertNull(DbHelper.getOrderCreditId());

    }

    //Проверка оплаты с невалидной картой
    @Test
    void PaymentWithInvalidCard() {
        new DashboardPage().openCreditPage()
                .PaymentWithInvalidCard(DataHelper.getInvalidCard());
        assertNull(DbHelper.getOrderCreditId());
    }

}
