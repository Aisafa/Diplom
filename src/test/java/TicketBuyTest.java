import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class TicketBuyTest {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }




    @Test
    void shouldLogin() {

    }
}


