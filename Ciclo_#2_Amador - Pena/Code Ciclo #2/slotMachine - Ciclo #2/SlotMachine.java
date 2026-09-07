/**
 * Simula una máquina tragamonedas compuesta por varias ruedas.
 *
 * La máquina permite administrar ruedas y símbolos,
 * consultar configuraciones, girar ruedas, verificar
 * estados ganadores y mostrar una representación visual
 * utilizando componentes gráficos del proyecto Shapes.
 *
 * Los símbolos son identificados mediante colores y cada
 * rueda mantiene exactamente un símbolo visible.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Simula una máquina tragamonedas.
 */
public class SlotMachine
{
    private ArrayList<Wheel> wheels;
    private boolean visible;
    private boolean ok;

    private Rectangle machineBody;
    private ArrayList<Circle> displays;

    /**
     * Constructor de la máquina.
     */
    public SlotMachine()
    {
        wheels = new ArrayList<Wheel>();
        visible = false;
        ok = true;

        machineBody = new Rectangle();
        displays = new ArrayList<Circle>();
    }

    /**
     * Actualiza la representación gráfica de la máquina.
     *
     * Cada círculo toma el color del símbolo visible
     * correspondiente en su rueda.
     *
     * Adicionalmente, la máquina cambia de color cuando
     * alcanza un estado ganador (jackpot).
     */
    private void updateDisplay()
    {
        if(isJackpot()){
            machineBody.changeColor("yellow");
        }
        else{
            machineBody.changeColor("black");
        }

        for(int i = 0; i < wheels.size() && i < displays.size(); i++){

            String color = wheels.get(i).getVisibleSymbol();

            if(!color.equals("")){
                displays.get(i).changeColor(color);
            }
        }
    }

    /**
     * Agrega una rueda en una posición.
     *
     * La posición válida para agregar una rueda está entre
     * 1 y el número actual de ruedas más 1.
     *
     * @param pos posición donde se desea agregar la rueda.
     */
    public void addWheel(int pos)
    {
        if(pos < 1 || pos > wheels.size() + 1){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La posición indicada no es válida.");
            }

            return;
        }

        wheels.add(pos - 1, new Wheel());

        Circle c = new Circle();
        c.changeSize(40);

        // Ajuste para que los círculos aparezcan dentro de la máquina
        c.moveHorizontal(50 + (pos - 1) * 60);

        displays.add(pos - 1, c);

        if(visible){
            c.makeVisible();
            updateDisplay();
        }

        ok = true;
    }

    /**
     * Elimina una rueda.
     *
     * La posición debe corresponder a una rueda existente.
     *
     * @param pos posición de la rueda que se desea eliminar.
     */
    public void delWheel(int pos)
    {
        if(pos < 1 || pos > wheels.size()){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La rueda indicada no existe.");
            }

            return;
        }

        displays.get(pos - 1).makeInvisible();

        wheels.remove(pos - 1);
        displays.remove(pos - 1);

        updateDisplay();

        ok = true;
    }

    /**
     * Intercambia dos ruedas de la máquina.
     *
     * Las posiciones deben corresponder a ruedas existentes.
     *
     * @param wheel1 posición de la primera rueda.
     * @param wheel2 posición de la segunda rueda.
     */
    public void swap(int wheel1, int wheel2)
    {
        if(wheel1 < 1 || wheel1 > wheels.size() ||
           wheel2 < 1 || wheel2 > wheels.size()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La rueda indicada no existe.");
            }

            return;
        }

        Wheel tempWheel = wheels.get(wheel1 - 1);
        wheels.set(wheel1 - 1, wheels.get(wheel2 - 1));
        wheels.set(wheel2 - 1, tempWheel);

        updateDisplay();

        ok = true;
    }

    /**
     * Fija una rueda de la máquina.
     *
     * La posición debe corresponder a una rueda existente.
     *
     * @param wheel posición de la rueda que se desea fijar.
     */
    public void lock(int wheel)
    {
        if(wheel < 1 || wheel > wheels.size()){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La rueda indicada no existe.");
            }

            return;
        }

        wheels.get(wheel - 1).lock();

        ok = true;
    }

    /**
     * Libera una rueda de la máquina.
     *
     * La posición debe corresponder a una rueda existente.
     *
     * @param wheel posición de la rueda que se desea liberar.
     */
    public void unlock(int wheel)
    {
        if(wheel < 1 || wheel > wheels.size()){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La rueda indicada no existe.");
            }

            return;
        }

        wheels.get(wheel - 1).unlock();

        ok = true;
    }

    /**
     * Agrega un símbolo a una rueda específica.
     *
     * @param pos posición de la rueda.
     * @param color color que identifica al símbolo.
     */
    public void addSymbol(int pos, String color)
    {
        if(pos < 1 || pos > wheels.size()){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La rueda indicada no existe.");
            }

            return;
        }

        wheels.get(pos - 1).addSymbol(color);

        updateDisplay();

        ok = true;
    }

    /**
     * Elimina un símbolo de todas las ruedas.
     *
     * @param symbol símbolo que se desea eliminar.
     */
    public void delSymbol(String symbol)
    {
        boolean eliminado = false;

        for(Wheel wheel : wheels){

            if(wheel.delSymbol(symbol)){
                eliminado = true;
            }
        }

        updateDisplay();

        if(eliminado){
            ok = true;
        }
        else{
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "El símbolo no existe.");
            }
        }
    }

    /**
     * Hace visible un símbolo en una rueda específica.
     *
     * @param wheel posición de la rueda.
     * @param symbol símbolo que se desea mostrar.
     */
    public void placeSymbol(int wheel, String symbol)
    {
        if(wheel < 1 || wheel > wheels.size()){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La rueda indicada no existe.");
            }

            return;
        }

        boolean colocado = wheels.get(wheel - 1).placeSymbol(symbol);

        if(colocado){
            ok = true;
            updateDisplay();
        }
        else{
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "El símbolo no existe en la rueda.");
            }
        }
    }

    /**
     * Retorna los símbolos visibles de todas las ruedas.
     *
     * @return arreglo con los símbolos visibles.
     */
    public String[] symbols()
    {
        String[] result = new String[wheels.size()];

        for(int i = 0; i < wheels.size(); i++){
            result[i] = wheels.get(i).getVisibleSymbol();
        }

        return result;
    }

    /**
     * Retorna la configuración actual de la máquina.
     *
     * La configuración corresponde a los símbolos visibles
     * de todas las ruedas ordenados de izquierda a derecha.
     *
     * @return arreglo con los símbolos visibles.
     */
    public String[] configuration()
    {
        return symbols();
    }

    /**
     * Cuenta la cantidad de símbolos visibles diferentes
     * presentes en la máquina.
     *
     * @return número de símbolos distintos visibles.
     */
    public int distinctSymbols()
    {
        String[] visibles = symbols();
        int distintos = 0;

        for(int i = 0; i < visibles.length; i++){

            boolean repetido = false;

            for(int j = 0; j < i; j++){

                if(visibles[i].equals(visibles[j])){
                    repetido = true;
                }
            }

            if(!repetido){
                distintos++;
            }
        }

        return distintos;
    }

    /**
     * Determina si la máquina se encuentra en un estado ganador.
     *
     * Existe jackpot cuando todas las ruedas muestran el mismo
     * símbolo visible y las ruedas contienen al menos un símbolo.
     *
     * @return true si todas las ruedas muestran el mismo símbolo;
     * false en caso contrario.
     */
    public boolean isJackpot()
    {
        if(wheels.size() == 0){
            return false;
        }

        String primerSimbolo = wheels.get(0).getVisibleSymbol();

        if(primerSimbolo.equals("")){
            return false;
        }

        for(int i = 1; i < wheels.size(); i++){

            if(!primerSimbolo.equals(wheels.get(i).getVisibleSymbol())){
                return false;
            }
        }

        return true;
    }

    /**
     * Gira todas las ruedas.
     */
    public void spin()
    {
        for(Wheel wheel : wheels){
            wheel.spin();
        }

        updateDisplay();

        ok = true;
    }

    /**
     * Gira una rueda específica.
     *
     * La posición debe corresponder a una rueda existente.
     *
     * @param wheel posición de la rueda.
     */
    public void spin(int wheel)
    {
        if(wheel < 1 || wheel > wheels.size()){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La rueda indicada no existe.");
            }

            return;
        }

        if(wheels.get(wheel - 1).isLocked()){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La rueda está bloqueada.");
            }

            return;
        }

        wheels.get(wheel - 1).spin();

        updateDisplay();

        ok = true;
    }

    /**
     * Gira una rueda específica una cantidad determinada de pasos.
     *
     * La posición debe corresponder a una rueda existente y
     * la rueda no debe estar bloqueada.
     *
     * @param wheel posición de la rueda.
     * @param steps cantidad de pasos que debe girar la rueda.
     */
    public void spin(int wheel, int steps)
    {
        if(wheel < 1 || wheel > wheels.size()){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La rueda indicada no existe.");
            }

            return;
        }

        if(wheels.get(wheel - 1).isLocked()){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La rueda está bloqueada.");
            }

            return;
        }

        if(steps < 0){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La cantidad de pasos no es válida.");
            }

            return;
        }

        if(visible){
            for(int i = 0; i < steps; i++){

                wheels.get(wheel - 1).spin();

                updateDisplay();

                Canvas.getCanvas().wait(200);
            }
        }
        else{
            wheels.get(wheel - 1).spin(steps);

            updateDisplay();
        }

        ok = true;
    }

    /**
     * Deja la máquina en una configuración determinada.
     *
     * Cada posición del arreglo representa el símbolo que se desea
     * dejar visible en la rueda correspondiente.
     *
     * @param setSymbols configuración deseada.
     */
    public void spin(String[] setSymbols)
    {
        if(setSymbols.length != wheels.size()){
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(null,
                    "La configuración no corresponde a la cantidad de ruedas.");
            }

            return;
        }

        for(int i = 0; i < wheels.size(); i++){

            String objetivo = setSymbols[i];
            String[] simbolos = wheels.get(i).getSymbols();

            int posicionObjetivo = -1;

            for(int j = 0; j < simbolos.length; j++){

                if(simbolos[j].equals(objetivo)){
                    posicionObjetivo = j;
                    break;
                }
            }

            if(posicionObjetivo == -1){
                ok = false;

                if(visible){
                    JOptionPane.showMessageDialog(null,
                        "El símbolo no existe en la rueda.");
                }

                return;
            }

            if(wheels.get(i).isLocked()){
                ok = false;

                if(visible){
                    JOptionPane.showMessageDialog(null,
                        "La rueda está bloqueada.");
                }

                return;
            }

            String actual = wheels.get(i).getVisibleSymbol();
            int posicionActual = 0;

            for(int j = 0; j < simbolos.length; j++){

                if(simbolos[j].equals(actual)){
                    posicionActual = j;
                    break;
                }
            }

            int steps = posicionObjetivo - posicionActual;

            if(steps < 0){
                steps = steps + simbolos.length;
            }

            wheels.get(i).spin(steps);
        }

        updateDisplay();

        ok = true;
    }

    /**
     * Hace visible la representación gráfica de la máquina.
     *
     * Se dibuja el cuerpo de la máquina y todos los símbolos
     * visibles de sus ruedas.
     */
    public void makeVisible()
    {
        machineBody.changeColor("black");
        machineBody.changeSize(100, 250);
        machineBody.makeVisible();

        visible = true;

        updateDisplay();

        for(Circle c : displays){
            c.makeVisible();
        }
    }

    /**
     * Oculta la representación gráfica de la máquina.
     */
    public void makeInvisible()
    {
        machineBody.makeInvisible();

        for(Circle c : displays){
            c.makeInvisible();
        }

        visible = false;
    }

    /**
     * Finaliza la simulación ocultando la máquina.
     */
    public void exit()
    {
        makeInvisible();
    }

    /**
     * Indica si la última operación fue exitosa.
     *
     * @return true si la última operación fue exitosa;
     * false en caso contrario.
     */
    public boolean ok()
    {
        return ok;
    }
}