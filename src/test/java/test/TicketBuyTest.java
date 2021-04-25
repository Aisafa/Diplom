package test;

import data.DataHelper;
import data.DbHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class TicketBuyTest {


    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void submittingAnEmptyForm() {
        new DashboardPage().openDebitPage().submittingAnEmptyForm();
    }

    //Successful Payment
    //проверка успошной оплаты
    @Test
    void successfulPayment() {
        new DashboardPage().openDebitPage()
                .successfulPayment(DataHelper.getValidOwner());
        assertEquals("APPROVED", DbHelper.getPaymentStatus());
    }

    //проверка успошной оплаты
    @Test
    void shouldInsertPaymentAmount() {
        int expectedAmount = 45_000_00;
        new DashboardPage().openDebitPage()
                .successfulPayment(DataHelper.getValidOwner());
        assertEquals(expectedAmount, DbHelper.getPaymentAmount());
    }

    //Declined Payment
    //проверка оплаты c отклноненной картой
    @Test
    void DeclinedCardPaymentError() {
        new DashboardPage().openDebitPage()
                .DeclinedCardPaymentError(DataHelper.getValidValueDeclinedCard());
        assertNull(DbHelper.getOrderCreditId());

    }

    //проверка оплаты c отклноненной картой
    @Test
    void DeclinedCardPaymentErrorPaymentID() {
        new DashboardPage().openDebitPage()
                .DeclinedCardPaymentError(DataHelper.getValidValueDeclinedCard());
        assertNotNull(DbHelper.getPaymentId());
    }

    //проверка оплаты c отклноненной картой и с не правильным именем владельца
    @Test
    void DeclinedCardPaymentErrorWithInvalidOwnerPaymentID() {
        new DashboardPage().openDebitPage()
                .DeclinedCardPaymentError(DataHelper.getInvalidOwnerWithDeclinedCard());
        assertNotNull(DbHelper.getPaymentId());
    }

    //Negative Test
//Проверка оплаты используя невалидное имя владельца с цифрами
    @Test
    void PaymentWithInvalidOwnerWithNumber() {
        new DashboardPage().openDebitPage()
                .PaymentWithInvalidOwnerWithNumber(DataHelper.getInvalidOwnerWithNumber());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты используя невалидное имя владельца соспецсимволами
    @Test
    void PaymentWithInvalidOwnerWithSpecChar() {
        new DashboardPage().openDebitPage()
                .PaymentWithSpecChar(DataHelper.getInvalidOwnerWithSpecChar());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты используя невалидное имя владельца c кириллицей
    @Test
    void PaymentWithInvalidOwnerWithCyrillic() {
        new DashboardPage().openDebitPage()
                .PaymentWithCyrillic(DataHelper.getInvalidOwnerWithCyrillic());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты используя невалидное значение месяца работы карты
    @Test
    void PaymentWithInValidMonth() {
        new DashboardPage().openDebitPage()
                .PaymentWithInValidMonth(DataHelper.getInValidMonth());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты используя невалидное значение года работы карты дальнего будущего
    @Test
    void PaymentWithInValidLastYear() {
        new DashboardPage().openDebitPage()
                .PaymentWithInValidLastYear(DataHelper.getInValidLastYear());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты используя невалидное значение года работы карты прошлого
    @Test
    void PaymentWithInValidMoreYear() {
        new DashboardPage().openDebitPage()
                .PaymentWithInValidMoreYear(DataHelper.getInValidMoreYear());
        assertNull(DbHelper.getOrderCreditId());
    }

    //Проверка оплаты с невалидным СVС кодом
    @Test
    void PaymentWithInvalidCvc() {
        new DashboardPage().openDebitPage()
                .PaymentWithInvalidCvc(DataHelper.getInvalidCvc());
        assertNull(DbHelper.getOrderCreditId());

    }

    //Проверка оплаты с невалидной картой
    @Test
    void PaymentWithInvalidCard() {
        new DashboardPage().openDebitPage()
                .PaymentWithInvalidCard(DataHelper.getInvalidCard());
        assertNull(DbHelper.getOrderCreditId());
    }
}


