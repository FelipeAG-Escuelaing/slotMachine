import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias compartidas para SlotMachine correspondientes
 * al Ciclo 2.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class SlotMachineCC2Test
{
    public SlotMachineCC2Test() {}

    @BeforeEach
    public void setUp() {}

    @AfterEach
    public void tearDown() {}

    /**
     * Verifica que dos ruedas puedan intercambiar sus posiciones.
     */
    @Test
    public void accordingGonzalezAPenaRShouldSwapWheels()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");

        machine.swap(1, 2);

        assertArrayEquals(
            new String[]{"blue", "red"},
            machine.symbols()
        );
    }

    /**
     * Verifica que una rueda pueda girar una cantidad determinada
     * de pasos.
     */
    @Test
    public void accordingGonzalezAPenaRShouldSpinWheel()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);

        machine.addSymbol(1, "red");
        machine.addSymbol(1, "blue");
        machine.addSymbol(1, "green");

        machine.spin(1, 2);

        assertArrayEquals(
            new String[]{"green"},
            machine.symbols()
        );
    }
}