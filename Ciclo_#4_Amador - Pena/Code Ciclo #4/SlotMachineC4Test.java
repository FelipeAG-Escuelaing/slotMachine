import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para las nuevas funcionalidades del Ciclo 4.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class SlotMachineC4Test
{
    @Test
    public void rebelWheelShouldNotBeDeleted()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel("rebel", 2);

        machine.delWheel(2);

        assertFalse(machine.ok());
        assertEquals(2, machine.symbols().length);
    }

    @Test
    public void rebelWheelShouldNotBeSwapped()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel("rebel", 2);

        machine.swap(1, 2);

        assertFalse(machine.ok());
    }

    @Test
    public void rebelWheelShouldNotBeLocked()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel("rebel", 2);

        machine.lock(2);

        assertFalse(machine.ok());

        machine.addSymbol(2, "red");
        machine.spin(2);

        assertTrue(machine.ok());
    }

    @Test
    public void leftyWheelShouldSpinInOppositeDirection()
    {
        SlotMachine machine = new SlotMachine();

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

    @Test
    public void ephemeralSymbolShouldBecomeSmaller()
    {
        EphemeralSymbol symbol =
            new EphemeralSymbol("red");

        int initialSize =
            symbol.getSize();

        symbol.select();

        assertTrue(
            symbol.getSize() < initialSize
        );
    }

    @Test
    public void shySymbolShouldAlternateVisibility()
    {
        ShySymbol symbol =
            new ShySymbol("blue");

        assertTrue(symbol.isVisible());

        symbol.select();

        assertFalse(symbol.isVisible());

        symbol.select();

        assertTrue(symbol.isVisible());
    }

    @Test
    public void comodinShouldRepresentAnyColor()
    {
        Comodin comodin =
            new Comodin();

        assertTrue(
            comodin.represents("red")
        );

        assertTrue(
            comodin.represents("blue")
        );

        assertTrue(
            comodin.represents("green")
        );
    }
}