import org.junit.Test;
import com.temp_calculator.TemperatureConverter;
import static org.junit.Assert.*;

public class mainTest {
    TemperatureConverter tempc = new TemperatureConverter();

    @Test
    public void fahrenheitToCelcius() {
        assertEquals(122, tempc.celciusToFahrenheit(50), 0.001);
    }

    @Test
    public void celciusToFahrenheit() {
        assertEquals(50, tempc.fahrenheitToCelcius(122), 0.01);
    }

    @Test
    public void isExtremeTemperature() {
        assertEquals(true, tempc.isExtremeTemperature(55));
        assertEquals(true, tempc.isExtremeTemperature(-45));
        assertEquals(false, tempc.isExtremeTemperature(20));
    }

    @Test
    public void kelvinToCelcius() {
        assertEquals(0, tempc.kelvinToCelcius(273.15), 0.01);
        assertEquals(100, tempc.kelvinToCelcius(373.15), 0.01);
    }
}
