import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas para SlotMachine correspondiente al Ciclo 2.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class SlotMachineC2Test
{
    /**
     * Constructor de la clase de pruebas.
     */
    public SlotMachineC2Test()
    {
    }

    /**
     * Configuración previa a cada prueba.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Limpieza posterior a cada prueba.
     */
    @AfterEach
    public void tearDown()
    {
    }

    /**
     * Verifica que swap intercambie correctamente dos ruedas.
     */
    @Test
    public void swapShouldExchangeWheels()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "blue");
        machine.addSymbol(2, "red");
        machine.addSymbol(3, "green");

        machine.swap(1, 3);

        assertArrayEquals(
            new String[]{"green", "red", "blue"},
            machine.symbols()
        );
    }

    /**
     * Verifica que swap no realice cambios cuando
     * una de las ruedas indicadas no existe.
     */
    @Test
    public void swapShouldFailWithInvalidWheel()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "blue");
        machine.addSymbol(2, "red");
        machine.addSymbol(3, "green");

        machine.swap(1, 4);

        assertFalse(machine.ok());

        assertArrayEquals(
            new String[]{"blue", "red", "green"},
            machine.symbols()
        );
    }

    /**
     * Verifica que una rueda bloqueada no pueda girar.
     */
    @Test
    public void lockShouldPreventWheelFromSpinning()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addSymbol(1, "red");
        machine.addSymbol(1, "blue");

        machine.lock(1);
        machine.spin(1);

        assertFalse(machine.ok());

        assertArrayEquals(
            new String[]{"red"},
            machine.symbols()
        );
    }

    /**
     * Verifica que una rueda pueda volver a girar
     * después de ser desbloqueada.
     */
    @Test
    public void unlockShouldAllowWheelToSpin()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addSymbol(1, "red");
        machine.addSymbol(1, "blue");

        machine.lock(1);
        machine.unlock(1);
        machine.spin(1);

        assertTrue(machine.ok());

        assertArrayEquals(
            new String[]{"blue"},
            machine.symbols()
        );
    }

    /**
     * Verifica que spin(wheel, steps) gire una rueda
     * la cantidad indicada de pasos.
     */
    @Test
    public void spinStepsShouldRotateWheel()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);

        machine.addSymbol(1, "red");
        machine.addSymbol(1, "blue");
        machine.addSymbol(1, "green");

        machine.spin(1, 2);

        assertTrue(machine.ok());

        assertArrayEquals(
            new String[]{"green"},
            machine.symbols()
        );
    }

    /**
     * Verifica que spin(wheel, steps) realice el giro
     * de forma circular cuando los pasos superan la cantidad
     * de símbolos.
     */
    @Test
    public void spinStepsShouldBeCircular()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);

        machine.addSymbol(1, "red");
        machine.addSymbol(1, "blue");
        machine.addSymbol(1, "green");

        machine.spin(1, 4);

        assertTrue(machine.ok());

        assertArrayEquals(
            new String[]{"blue"},
            machine.symbols()
        );
    }

    /**
     * Verifica que spin(wheel, steps) no pueda girar
     * una rueda que está bloqueada.
     */
    @Test
    public void spinStepsShouldFailWhenWheelIsLocked()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);

        machine.addSymbol(1, "red");
        machine.addSymbol(1, "blue");
        machine.addSymbol(1, "green");

        machine.lock(1);
        machine.spin(1, 2);

        assertFalse(machine.ok());

        assertArrayEquals(
            new String[]{"red"},
            machine.symbols()
        );
    }

    /**
     * Verifica que spin(wheel, steps) no acepte
     * una cantidad negativa de pasos.
     */
    @Test
    public void spinStepsShouldFailWithNegativeSteps()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);

        machine.addSymbol(1, "red");
        machine.addSymbol(1, "blue");
        machine.addSymbol(1, "green");

        machine.spin(1, -2);

        assertFalse(machine.ok());

        assertArrayEquals(
            new String[]{"red"},
            machine.symbols()
        );
    }

    /**
     * Verifica que spin(wheel, steps) falle cuando
     * la rueda indicada no existe.
     */
    @Test
    public void spinStepsShouldFailWithInvalidWheel()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);

        machine.addSymbol(1, "red");
        machine.addSymbol(1, "blue");
        machine.addSymbol(1, "green");

        machine.spin(2, 3);

        assertFalse(machine.ok());

        assertArrayEquals(
            new String[]{"red"},
            machine.symbols()
        );
    }

    /**
     * Verifica que spin(String[]) lleve la máquina
     * a la configuración solicitada.
     */
    @Test
    public void spinConfigurationShouldReachTarget()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "red");
        machine.addSymbol(1, "blue");
        machine.addSymbol(1, "green");

        machine.addSymbol(2, "red");
        machine.addSymbol(2, "blue");
        machine.addSymbol(2, "green");

        machine.addSymbol(3, "red");
        machine.addSymbol(3, "blue");
        machine.addSymbol(3, "green");

        machine.spin(
            new String[]{"blue", "green", "blue"}
        );

        assertTrue(machine.ok());

        assertArrayEquals(
            new String[]{"blue", "green", "blue"},
            machine.symbols()
        );
    }

    /**
     * Verifica que spin(String[]) falle cuando
     * la cantidad de símbolos no corresponde a la
     * cantidad de ruedas.
     */
    @Test
    public void spinConfigurationShouldFailWithInvalidSize()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");
        machine.addSymbol(3, "green");

        machine.spin(
            new String[]{"red", "blue"}
        );

        assertFalse(machine.ok());

        assertArrayEquals(
            new String[]{"red", "blue", "green"},
            machine.symbols()
        );
    }

    /**
     * Verifica que spin(String[]) falle cuando
     * el símbolo solicitado no existe en una rueda.
     */
    @Test
    public void spinConfigurationShouldFailWithInvalidSymbol()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");
        machine.addSymbol(3, "green");

        machine.spin(
            new String[]{"red", "purple", "green"}
        );

        assertFalse(machine.ok());

        assertArrayEquals(
            new String[]{"red", "blue", "green"},
            machine.symbols()
        );
    }

    /**
     * Verifica que spin(String[]) falle cuando
     * una de las ruedas necesarias está bloqueada.
     */
    @Test
    public void spinConfigurationShouldFailWithLockedWheel()
    {
        SlotMachine machine = new SlotMachine();

        machine.addWheel(1);
        machine.addWheel(2);
        machine.addWheel(3);

        machine.addSymbol(1, "red");
        machine.addSymbol(2, "blue");
        machine.addSymbol(3, "green");

        machine.lock(2);

        machine.spin(
            new String[]{"red", "green", "blue"}
        );

        assertFalse(machine.ok());

        assertArrayEquals(
            new String[]{"red", "blue", "green"},
            machine.symbols()
        );
    }
}