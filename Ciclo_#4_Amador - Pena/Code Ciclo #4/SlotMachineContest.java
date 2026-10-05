import java.util.ArrayList;

/**
 * Resuelve y simula el problema de Slot Machine.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class SlotMachineContest
{
    /**
     * Constructor.
     */
    public SlotMachineContest()
    {
    }

    /**
     * Resuelve el problema usando una máquina invisible.
     *
     * @param n cantidad de ruedas y símbolos.
     * @return acciones realizadas {rueda, pasos}.
     */
    public int[][] solve(int n)
    {
        SlotMachine machine =
            new SlotMachine(n);

        machine.makeInvisible();

        ArrayList<int[]> actions =
            new ArrayList<int[]>();

        buildSolution(
            machine,
            n,
            actions
        );

        int[][] result =
            new int[actions.size()][2];

        for(int actionPosition = 0;
            actionPosition < actions.size();
            actionPosition++){

            result[actionPosition] =
                actions.get(actionPosition);
        }

        return result;
    }

    /**
     * Simula visualmente la solución.
     *
     * @param n cantidad de ruedas y símbolos.
     */
    public void simulate(int n)
    {
        SlotMachine machine =
            new SlotMachine(n);

        machine.makeVisible();

        ArrayList<int[]> actions =
            new ArrayList<int[]>();

        buildSolution(
            machine,
            n,
            actions
        );
    }

    /**
     * Construye la solución completa.
     *
     * @param machine máquina.
     * @param n cantidad de ruedas.
     * @param actions acciones realizadas.
     */
    private void buildSolution(
        SlotMachine machine,
        int n,
        ArrayList<int[]> actions)
    {
        boolean finished =
            makeWheelsDifferent(
                machine,
                n,
                actions
            );

        if(finished){
            return;
        }

        if(machine.isJackpot()){
            return;
        }

        int[] nextWheel =
            findWheelOrder(
                machine,
                n,
                actions
            );

        if(nextWheel == null){
            return;
        }

        if(machine.isJackpot()){
            return;
        }

        finishMachine(
            machine,
            n,
            nextWheel,
            actions
        );
    }

    /**
     * Hace que todas las ruedas muestren
     * símbolos diferentes.
     *
     * @param machine máquina.
     * @param n cantidad de ruedas.
     * @param actions acciones realizadas.
     * @return true si ya se consiguió el jackpot.
     */
    private boolean makeWheelsDifferent(
        SlotMachine machine,
        int n,
        ArrayList<int[]> actions)
    {
        for(int wheelPosition = 1;
            wheelPosition <= n;
            wheelPosition++){

            int bestPosition = 0;
            int bestDifferentSymbols = -1;

            for(int position = 0;
                position < n;
                position++){

                if(machine.isJackpot()){
                    return true;
                }

                int differentSymbols =
                    machine.distinctSymbols();

                if(differentSymbols >
                   bestDifferentSymbols){

                    bestDifferentSymbols =
                        differentSymbols;

                    bestPosition = position;
                }

                rotateAndSave(
                    machine,
                    wheelPosition,
                    1,
                    actions
                );

                if(machine.isJackpot()){
                    return true;
                }
            }

            if(bestPosition != 0){

                rotateAndSave(
                    machine,
                    wheelPosition,
                    bestPosition,
                    actions
                );

                if(machine.isJackpot()){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Descubre el orden de las ruedas.
     *
     * @param machine máquina.
     * @param n cantidad de ruedas.
     * @param actions acciones realizadas.
     * @return arreglo que indica la siguiente rueda.
     */
    private int[] findWheelOrder(
        SlotMachine machine,
        int n,
        ArrayList<int[]> actions)
    {
        int[] nextWheel =
            new int[n];

        for(int wheelPosition = 0;
            wheelPosition < n;
            wheelPosition++){

            nextWheel[wheelPosition] = -1;
        }

        for(int firstWheel = 1;
            firstWheel <= n;
            firstWheel++){

            for(int secondWheel = 1;
                secondWheel <= n;
                secondWheel++){

                if(firstWheel != secondWheel &&
                   nextWheel[firstWheel - 1] == -1){

                    rotateAndSave(
                        machine,
                        firstWheel,
                        1,
                        actions
                    );

                    if(machine.isJackpot()){
                        return null;
                    }

                    rotateAndSave(
                        machine,
                        secondWheel,
                        -1,
                        actions
                    );

                    if(machine.isJackpot()){
                        return null;
                    }

                    if(machine.distinctSymbols() == n){

                        nextWheel[firstWheel - 1] =
                            secondWheel;
                    }

                    rotateAndSave(
                        machine,
                        secondWheel,
                        1,
                        actions
                    );

                    if(machine.isJackpot()){
                        return null;
                    }

                    rotateAndSave(
                        machine,
                        firstWheel,
                        -1,
                        actions
                    );

                    if(machine.isJackpot()){
                        return null;
                    }
                }
            }
        }

        return nextWheel;
    }

    /**
     * Lleva todas las ruedas al mismo símbolo.
     *
     * @param machine máquina.
     * @param n cantidad de ruedas.
     * @param nextWheel siguiente rueda.
     * @param actions acciones realizadas.
     */
    private void finishMachine(
        SlotMachine machine,
        int n,
        int[] nextWheel,
        ArrayList<int[]> actions)
    {
        int currentWheel = 1;

        for(int distance = 1;
            distance < n;
            distance++){

            currentWheel =
                nextWheel[currentWheel - 1];

            if(currentWheel == -1){
                return;
            }

            rotateAndSave(
                machine,
                currentWheel,
                -distance,
                actions
            );

            if(machine.isJackpot()){
                return;
            }
        }
    }

    /**
     * Gira una rueda y guarda la acción.
     *
     * @param machine máquina.
     * @param wheel rueda.
     * @param steps pasos.
     * @param actions lista de acciones.
     */
    private void rotateAndSave(
        SlotMachine machine,
        int wheel,
        int steps,
        ArrayList<int[]> actions)
    {
        if(steps != 0){

            machine.spin(
                wheel,
                steps
            );

            actions.add(
                new int[]{wheel, steps}
            );
        }
    }
}