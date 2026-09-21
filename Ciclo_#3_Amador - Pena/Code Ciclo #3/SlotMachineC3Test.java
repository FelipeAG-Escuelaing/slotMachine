import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Pruebas para la solución del Ciclo 3.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class SlotMachineC3Test
{
    /**
     * Prueba que solve() pueda resolver una máquina
     * con el número mínimo de ruedas permitido.
     */
    @Test
    public void shouldSolveThreeWheels()
    {
        SlotMachineContest contest =
            new SlotMachineContest();

        int[][] resultado =
            contest.solve(3);

        assertNotNull(resultado);
        assertTrue(resultado.length > 0);
    }

    /**
     * Prueba que solve() pueda resolver una máquina
     * de 5 ruedas.
     */
    @Test
    public void shouldSolveFiveWheels()
    {
        SlotMachineContest contest =
            new SlotMachineContest();

        int[][] resultado =
            contest.solve(5);

        assertNotNull(resultado);
        assertTrue(resultado.length > 0);
    }

    /**
     * Prueba que solve() pueda resolver una máquina
     * con el número máximo de ruedas permitido.
     */
    @Test
    public void shouldSolveTenWheels()
    {
        SlotMachineContest contest =
            new SlotMachineContest();

        int[][] resultado =
            contest.solve(10);

        assertNotNull(resultado);
        assertTrue(resultado.length > 0);
    }

    /**
     * Prueba que las acciones devueltas por solve()
     * tengan una rueda válida y un número de pasos diferente de cero.
     */
    @Test
    public void shouldReturnValidActions()
    {
        SlotMachineContest contest =
            new SlotMachineContest();

        int[][] resultado =
            contest.solve(5);

        for(int i = 0;
            i < resultado.length;
            i++){

            assertNotNull(resultado[i]);

            assertEquals(
                2,
                resultado[i].length
            );

            int rueda =
                resultado[i][0];

            int pasos =
                resultado[i][1];

            assertTrue(
                rueda >= 1 &&
                rueda <= 5
            );

            assertTrue(
                pasos != 0
            );
        }
    }

    /**
     * Prueba que solve() rechace una máquina
     * con menos de 3 ruedas.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSolveWithTwoWheels()
    {
        SlotMachineContest contest =
            new SlotMachineContest();

        contest.solve(2);
    }

    /**
     * Prueba que solve() rechace una máquina
     * con más de 10 ruedas.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSolveWithElevenWheels()
    {
        SlotMachineContest contest =
            new SlotMachineContest();

        contest.solve(11);
    }

    /**
     * Prueba que solve() pueda ejecutarse varias veces
     * con diferentes configuraciones iniciales aleatorias.
     */
    @Test
    public void shouldSolveSeveralRandomConfigurations()
    {
        SlotMachineContest contest =
            new SlotMachineContest();

        for(int i = 0;
            i < 5;
            i++){

            int[][] resultado =
                contest.solve(5);

            assertNotNull(resultado);
            assertTrue(resultado.length > 0);
        }
    }

    /**
     * Prueba que la solución no supere el máximo
     * de 10000 acciones permitido por el problema.
     */
    @Test
    public void shouldRespectMaximumActions()
    {
        SlotMachineContest contest =
            new SlotMachineContest();

        int[][] resultado =
            contest.solve(10);

        assertTrue(
            resultado.length <= 10000
        );
    }
}