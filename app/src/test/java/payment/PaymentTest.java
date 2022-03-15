/**
 * @author Jordan Marshall
 */

package payment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    // Payment Unit Tests

    @Test void payCreditCard(){
        Payment payment = new Payment();
        assertTrue(payment.payCreditCard("1123342534522342"," Eoin McDonough" ,1405,123));
    }

    @Test void isValidNum(){
        Payment payment = new Payment();
        assertTrue(payment.isValid("cardNo", "1123342534522342"));
    }
    @Test void isValidDate(){
        Payment payment = new Payment();
        assertTrue(payment.isValid("cardDate", 1405));
    }
    @Test void isValiddCSV(){
        Payment payment = new Payment();
        assertTrue(payment.isValid("cardBack", 123));
    }
}