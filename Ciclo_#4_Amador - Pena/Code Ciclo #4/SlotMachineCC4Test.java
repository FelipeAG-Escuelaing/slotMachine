import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias compartidas para SlotMachine correspondientes
 * al Ciclo 4.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class SlotMachineCC4Test
{
    public SlotMachineCC4Test()
    {
    }

    @BeforeEach
    public void setUp()
    {
    }

    @AfterEach
    public void tearDown()
    {
    }

    /**
     * Verifica que una rueda rebelde no pueda bloquearse.
     */
    @Test
    public void accordingGonzalezAPenaRShouldNotLockRebelWheel()
    {
        SlotMachine machine =
            new SlotMachine();

        machine.addWheel(1);
        machine.addWheel("rebel", 2);

        machine.lock(2);

        assertFalse(machine.ok());
    }

    /**
     * Verifica que una rueda lefty gire en sentido contrario.
     */
    @Test
    public void accordingGonzalezAPenaRShouldSpinLeftyInOppositeDirection()
    {
        SlotMachine machine =
            new SlotMachine();

        machine.addWheel(1);
        machine.addWheel("lefty", 2);

        machine.addSymbol(2, "red");
        machine.addSymbol(2, "blue");
        machine.addSymbol(2, "green");

        machine.spin(2);

        assertArrayEquals(
            new String[]{"", "green"},
            machine.symbols()
        );
    }
}