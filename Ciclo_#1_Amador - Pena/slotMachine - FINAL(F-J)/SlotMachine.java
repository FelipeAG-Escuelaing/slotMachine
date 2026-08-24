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
        for(int i = 0; i < wheels.size() && i < displays.size(); i++){

            String color = wheels.get(i).getVisibleSymbol();

            if(!color.equals("")){
                displays.get(i).changeColor(color);
            }
        }

        if(isJackpot()){
            machineBody.changeColor("yellow");
        }
        else{
            machineBody.changeColor("black");
        }
    }

    /**
     * Agrega una rueda en una posición.
     */
    public void addWheel(int pos)
    {
        if(pos < 1){
            pos = 1;
        }

        if(pos > wheels.size() + 1){
            pos = wheels.size() + 1;
        }

        wheels.add(pos - 1, new Wheel());

        Circle c = new Circle();
        c.changeSize(40);

        // Ajuste para que los círculos aparezcan dentro de la máquina
        c.moveHorizontal(50 + (pos - 1) * 60);

        displays.add(pos - 1, c);

        ok = true;
    }

    /**
     * Elimina una rueda.
     */
    public void delWheel(int pos)
    {
        if(wheels.size() == 0){
            ok = false;
            return;
        }

        if(pos < 1){
            pos = 1;
        }

        if(pos > wheels.size()){
            pos = wheels.size();
        }

        wheels.remove(pos - 1);
        displays.remove(pos - 1);

        ok = true;
    }

    /**
     * Agrega un símbolo a una rueda específica.
     */
    public void addSymbol(int pos, String color)
    {
        if(pos < 1 || pos > wheels.size()){
            ok = false;
            return;
        }

        wheels.get(pos - 1).addSymbol(color);

        ok = true;
    }

    /**
     * Elimina un símbolo de todas las ruedas.
     */
    public void delSymbol(String symbol)
    {
        for(Wheel wheel : wheels){
            wheel.delSymbol(symbol);
        }

        updateDisplay();

        ok = true;
    }

    /**
     * Hace visible un símbolo en una rueda específica.
     */
    public void placeSymbol(int wheel, String symbol)
    {
        if(wheel < 1 || wheel > wheels.size()){
            ok = false;
            return;
        }

        wheels.get(wheel - 1).placeSymbol(symbol);

        updateDisplay();

        ok = true;
    }

    /**
     * Retorna los símbolos visibles de todas las ruedas.
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
     * símbolo visible.
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
     */
    public void spin(int wheel)
    {
        if(wheels.size() == 0){
            ok = false;
            return;
        }

        if(wheel < 1){
            wheel = 1;
        }

        if(wheel > wheels.size()){
            wheel = wheels.size();
        }

        wheels.get(wheel - 1).spin();

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

        updateDisplay();

        for(Circle c : displays){
            c.makeVisible();
        }

        visible = true;
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
     */
    public boolean ok()
    {
        return ok;
    }
}