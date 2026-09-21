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
     * Resuelve el problema utilizando una máquina invisible.
     *
     * @param n número de ruedas y símbolos.
     * @return secuencia de acciones {rueda, pasos}.
     */
    public int[][] solve(int n)
    {
        SlotMachine machine =
            new SlotMachine(n);

        machine.makeInvisible();

        ArrayList<int[]> acciones =
            new ArrayList<int[]>();

        construirSolucion(
            machine,
            n,
            acciones
        );

        int[][] resultado =
            new int[acciones.size()][2];

        for(int i = 0;
            i < acciones.size();
            i++){

            resultado[i] =
                acciones.get(i);
        }

        return resultado;
    }

    /**
     * Simula visualmente la solución.
     *
     * @param n número de ruedas y símbolos.
     */
    public void simulate(int n)
    {
        SlotMachine machine =
            new SlotMachine(n);

        machine.makeVisible();

        ArrayList<int[]> acciones =
            new ArrayList<int[]>();

        construirSolucion(
            machine,
            n,
            acciones
        );
    }

    /**
     * Construye una solución sobre una máquina.
     *
     * @param machine máquina que se utilizará.
     * @param n número de ruedas y símbolos.
     * @param acciones acciones realizadas.
     */
    private void construirSolucion(
        SlotMachine machine,
        int n,
        ArrayList<int[]> acciones)
    {
        int[] mascaraInicial =
            scanWheel(
                machine,
                1,
                n,
                acciones
            );

        if(machine.isJackpot()){
            return;
        }

        int posicionS = -1;
        int posicionR = -1;

        for(int i = 0;
            i < n;
            i++){

            if(mascaraInicial[i] == 0 &&
               posicionS == -1){

                posicionS = i;
            }

            if(mascaraInicial[i] == 1 &&
               posicionR == -1){

                posicionR = i;
            }
        }

        rotate(
            machine,
            1,
            posicionS,
            acciones
        );

        if(machine.isJackpot()){
            return;
        }

        int[] posicionesObjetivo =
            new int[n];

        posicionesObjetivo[0] =
            posicionS;

        int cambio =
            posicionR - posicionS;

        if(cambio < 0){
            cambio =
                cambio + n;
        }

        for(int rueda = 2;
            rueda <= n;
            rueda++){

            int[] mascaraS =
                scanWheel(
                    machine,
                    rueda,
                    n,
                    acciones
                );

            if(machine.isJackpot()){
                return;
            }

            rotate(
                machine,
                1,
                cambio,
                acciones
            );

            if(machine.isJackpot()){
                return;
            }

            int[] mascaraR =
                scanWheel(
                    machine,
                    rueda,
                    n,
                    acciones
                );

            if(machine.isJackpot()){
                return;
            }

            int posicionSrueda = -1;

            for(int i = 0;
                i < n;
                i++){

                if(mascaraS[i] == 1 &&
                   mascaraR[i] == 0 &&
                   posicionSrueda == -1){

                    posicionSrueda = i;
                }
            }

            posicionesObjetivo[rueda - 1] =
                posicionSrueda;

            rotate(
                machine,
                1,
                -cambio,
                acciones
            );

            if(machine.isJackpot()){
                return;
            }
        }

        for(int rueda = 2;
            rueda <= n;
            rueda++){

            rotate(
                machine,
                rueda,
                posicionesObjetivo[rueda - 1],
                acciones
            );

            if(machine.isJackpot()){
                return;
            }
        }
    }

    /**
     * Recorre una rueda una vuelta completa.
     *
     * Se registra la cantidad de símbolos distintos
     * para cada posición.
     *
     * @param machine máquina.
     * @param wheel rueda.
     * @param n cantidad de símbolos.
     * @param acciones acciones realizadas.
     * @return máscara de posiciones mínimas.
     */
    private int[] scanWheel(
        SlotMachine machine,
        int wheel,
        int n,
        ArrayList<int[]> acciones)
    {
        int[] cantidades =
            new int[n];

        for(int i = 0;
            i < n;
            i++){

            if(machine.isJackpot()){
                return null;
            }

            cantidades[i] =
                machine.distinctSymbols();

            machine.spin(
                wheel,
                1
            );

            acciones.add(
                new int[]{wheel, 1}
            );

            if(machine.isJackpot()){
                return null;
            }
        }

        int minimo =
            cantidades[0];

        for(int i = 1;
            i < n;
            i++){

            if(cantidades[i] < minimo){
                minimo =
                    cantidades[i];
            }
        }

        int[] mascara =
            new int[n];

        for(int i = 0;
            i < n;
            i++){

            if(cantidades[i] == minimo){
                mascara[i] = 1;
            }
            else{
                mascara[i] = 0;
            }
        }

        return mascara;
    }

    /**
     * Realiza una rotación y registra la acción.
     *
     * @param machine máquina.
     * @param wheel rueda.
     * @param steps pasos.
     * @param acciones acciones realizadas.
     */
    private void rotate(
        SlotMachine machine,
        int wheel,
        int steps,
        ArrayList<int[]> acciones)
    {
        if(steps != 0){

            machine.spin(
                wheel,
                steps
            );

            acciones.add(
                new int[]{wheel, steps}
            );
        }
    }
}