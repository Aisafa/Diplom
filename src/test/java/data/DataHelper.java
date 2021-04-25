package data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    private static String getCardNumber(String card) {
        switch (card.toLowerCase()) {
            case ("approved"):
                return "4444 4444 4444 4441";
            case ("declined"):
                return "4444 4444 4444 4442";
            default:
                return "4444 4444 4444 4444";
        }
    }

    @Value
    public static class RequiredFields {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvcCvv;
    }

    public static RequiredFields getValidOwner() {
        return new RequiredFields(getCardNumber(
                "APPROVED"), "125", "245", "Aidar Safiullin", "121");
    }

    public static RequiredFields getInvalidOwnerWithNumber() {
        return new RequiredFields(getCardNumber(
                "APPROVED"), "12", "24", "101", "121");
    }

    public static RequiredFields getInvalidOwnerWithCyrillic() {
        return new RequiredFields(getCardNumber(
                "APPROVED"), "12", "24", "Айдар", "121");
    }

    public static RequiredFields getInvalidOwnerWithSpecChar() {
        return new RequiredFields(getCardNumber(
                "APPROVED"), "12", "24", "&*%*", "121");
    }

    public static RequiredFields getInValidMonth() {
        return new RequiredFields(getCardNumber(
                "APPROVED"), "15", "24", "Aidar Safiullin", "121");
    }

    public static RequiredFields getInValidLastYear() {
        return new RequiredFields(getCardNumber(
                "APPROVED"), "12", "10", "Aidar Safiullin", "121");
    }

    public static RequiredFields getInValidMoreYear() {
        return new RequiredFields(getCardNumber(
                "APPROVED"), "12", "44", "Aidar Safiullin", "121");
    }

    public static RequiredFields getInvalidCvc() {
        return new RequiredFields(getCardNumber(
                "APPROVED"), "12", "26", "Aidar Safiullin", "abc");
    }

    public static RequiredFields getValidValueDeclinedCard() {
        return new RequiredFields(getCardNumber(
                "DECLINED"), "12", "26", "Aidar Safiullin", "121");
    }

    public static RequiredFields getInvalidOwnerWithDeclinedCard() {
        return new RequiredFields(getCardNumber(
                "DECLINED"), "12", "24", "??", "121");
    }

    public static RequiredFields getInvalidCard() {
        return new RequiredFields(getCardNumber(
                "INVALID"), "12", "26", "Aidar Safiullin", "121");
    }
}
