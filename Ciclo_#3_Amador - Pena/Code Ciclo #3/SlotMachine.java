import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Simula una máquina tragamonedas compuesta por varias ruedas.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class SlotMachine
{
    private ArrayList<Wheel> wheels;
    private boolean visible;
    private boolean ok;
    private boolean visualConfigured;

    private Rectangle machineBody;
    private Rectangle machineFrame;

    private ArrayList<Rectangle> wheelFrames;
    private ArrayList<Rectangle> wheelWindows;
    private ArrayList<Circle> displays;

    private Rectangle lever;
    private Triangle leverHead;

    /**
     * Constructor de la máquina vacía.
     */
    public SlotMachine()
    {
        wheels = new ArrayList<Wheel>();
        visible = false;
        ok = true;
        visualConfigured = false;

        machineBody = new Rectangle();
        machineFrame = new Rectangle();

        wheelFrames = new ArrayList<Rectangle>();
        wheelWindows = new ArrayList<Rectangle>();
        displays = new ArrayList<Circle>();

        lever = new Rectangle();
        leverHead = new Triangle();
    }

    /**
     * Crea una máquina con n ruedas y n símbolos por rueda.
     *
     * Cada rueda contiene los mismos símbolos en el mismo orden
     * y empieza en una posición aleatoria.
     *
     * @param n cantidad de ruedas y símbolos.
     */
    public SlotMachine(int n)
    {
        this();

        if(n < 3 || n > 10){
            throw new IllegalArgumentException(
                "n debe estar entre 3 y 10."
            );
        }

        String[] colors = {
            "red",
            "blue",
            "green",
            "yellow",
            "magenta",
            "orange",
            "pink",
            "cyan",
            "purple",
            "brown"
        };

        for(int i = 1;
            i <= n;
            i++){

            addWheel(i);

            for(int j = 0;
                j < n;
                j++){

                wheels.get(i - 1)
                      .addSymbol(colors[j]);
            }

            int randomPosition =
                (int)(Math.random() * n);

            wheels.get(i - 1)
                  .placeSymbol(
                      colors[randomPosition]
                  );
        }

        updateDisplay();
    }

    /**
     * Actualiza la representación gráfica.
     */
    private void updateDisplay()
    {
        if(isJackpot()){
            machineBody.changeColor("yellow");
        }
        else{
            machineBody.changeColor("black");
        }

        for(int i = 0;
            i < wheels.size();
            i++){

            String color =
                wheels.get(i)
                      .getVisibleSymbol();

            if(!color.equals("")){
                displays.get(i)
                        .changeColor(color);
            }
        }

        if(visible){

            for(Rectangle frame :
                wheelFrames){

                frame.makeVisible();
            }

            for(Rectangle window :
                wheelWindows){

                window.makeVisible();
            }

            for(Circle display :
                displays){

                display.makeVisible();
            }

            lever.makeVisible();
            leverHead.makeVisible();
        }
    }

    /**
     * Organiza visualmente las ruedas.
     */
    private void updateWheelLayout()
    {
        for(Rectangle frame :
            wheelFrames){

            frame.makeInvisible();
        }

        for(Rectangle window :
            wheelWindows){

            window.makeInvisible();
        }

        for(Circle display :
            displays){

            display.makeInvisible();
        }

        wheelFrames.clear();
        wheelWindows.clear();
        displays.clear();

        int wheelWidth = 90;
        int wheelHeight = 130;
        int space = 15;

        if(wheels.size() > 3){

            wheelWidth =
                Math.max(
                    45,
                    (360 -
                     ((wheels.size() - 1) * 8))
                    / wheels.size()
                );

            space = 8;
        }

        int totalWidth =
            wheels.size() * wheelWidth +
            (wheels.size() - 1) * space;

        int startX =
            (500 - totalWidth) / 2;

        int circleSize =
            Math.min(
                40,
                wheelWidth - 20
            );

        int y = 90;

        for(int i = 0;
            i < wheels.size();
            i++){

            int x =
                startX +
                i * (wheelWidth + space);

            Rectangle frame =
                new Rectangle();

            frame.changeSize(
                wheelHeight,
                wheelWidth
            );

            frame.changeColor("white");

            frame.moveHorizontal(x - 70);
            frame.moveVertical(y - 15);

            Rectangle window =
                new Rectangle();

            window.changeSize(
                wheelHeight - 20,
                wheelWidth - 20
            );

            window.changeColor("black");

            window.moveHorizontal(
                x + 10 - 70
            );

            window.moveVertical(
                y + 10 - 15
            );

            Circle display =
                new Circle();

            display.changeSize(circleSize);

            display.moveHorizontal(
                x +
                (wheelWidth - circleSize) / 2
                - 20
            );

            display.moveVertical(
                y +
                (wheelHeight - circleSize) / 2
                - 15
            );

            wheelFrames.add(frame);
            wheelWindows.add(window);
            displays.add(display);

            if(visible){

                frame.makeVisible();
                window.makeVisible();
                display.makeVisible();
            }
        }
    }

    /**
     * Agrega una rueda.
     *
     * @param pos posición.
     */
    public void addWheel(int pos)
    {
        if(pos < 1 ||
           pos > wheels.size() + 1){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La posición indicada no es válida."
                );
            }

            return;
        }

        wheels.add(
            pos - 1,
            new Wheel()
        );

        updateWheelLayout();
        updateDisplay();

        ok = true;
    }

    /**
     * Elimina una rueda.
     *
     * @param pos posición.
     */
    public void delWheel(int pos)
    {
        if(pos < 1 ||
           pos > wheels.size()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La rueda indicada no existe."
                );
            }

            return;
        }

        wheels.remove(pos - 1);

        updateWheelLayout();
        updateDisplay();

        ok = true;
    }

    /**
     * Intercambia dos ruedas.
     *
     * @param wheel1 primera rueda.
     * @param wheel2 segunda rueda.
     */
    public void swap(int wheel1, int wheel2)
    {
        if(wheel1 < 1 ||
           wheel1 > wheels.size() ||
           wheel2 < 1 ||
           wheel2 > wheels.size()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La rueda indicada no existe."
                );
            }

            return;
        }

        Wheel temp =
            wheels.get(wheel1 - 1);

        wheels.set(
            wheel1 - 1,
            wheels.get(wheel2 - 1)
        );

        wheels.set(
            wheel2 - 1,
            temp
        );

        updateDisplay();

        ok = true;
    }

    /**
     * Bloquea una rueda.
     *
     * @param wheel posición.
     */
    public void lock(int wheel)
    {
        if(wheel < 1 ||
           wheel > wheels.size()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La rueda indicada no existe."
                );
            }

            return;
        }

        wheels.get(wheel - 1).lock();

        ok = true;
    }

    /**
     * Desbloquea una rueda.
     *
     * @param wheel posición.
     */
    public void unlock(int wheel)
    {
        if(wheel < 1 ||
           wheel > wheels.size()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La rueda indicada no existe."
                );
            }

            return;
        }

        wheels.get(wheel - 1).unlock();

        ok = true;
    }

    /**
     * Agrega un símbolo.
     *
     * @param pos rueda.
     * @param color símbolo.
     */
    public void addSymbol(int pos, String color)
    {
        if(pos < 1 ||
           pos > wheels.size()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La rueda indicada no existe."
                );
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
     * @param symbol símbolo.
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
                JOptionPane.showMessageDialog(
                    null,
                    "El símbolo no existe."
                );
            }
        }
    }

    /**
     * Coloca un símbolo.
     *
     * @param wheel rueda.
     * @param symbol símbolo.
     */
    public void placeSymbol(
        int wheel,
        String symbol)
    {
        if(wheel < 1 ||
           wheel > wheels.size()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La rueda indicada no existe."
                );
            }

            return;
        }

        boolean colocado =
            wheels.get(wheel - 1)
                  .placeSymbol(symbol);

        if(colocado){
            ok = true;
            updateDisplay();
        }
        else{
            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "El símbolo no existe en la rueda."
                );
            }
        }
    }

    /**
     * Retorna los símbolos visibles.
     *
     * @return símbolos visibles.
     */
    public String[] symbols()
    {
        String[] result =
            new String[wheels.size()];

        for(int i = 0;
            i < wheels.size();
            i++){

            result[i] =
                wheels.get(i)
                      .getVisibleSymbol();
        }

        return result;
    }

    /**
     * Retorna la configuración.
     *
     * @return configuración.
     */
    public String[] configuration()
    {
        return symbols();
    }

    /**
     * Cuenta los símbolos diferentes visibles.
     *
     * @return cantidad.
     */
    public int distinctSymbols()
    {
        String[] visibles = symbols();
        int distintos = 0;

        for(int i = 0;
            i < visibles.length;
            i++){

            boolean repetido = false;

            for(int j = 0;
                j < i;
                j++){

                if(visibles[i].equals(
                    visibles[j])){

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
     * Indica si existe jackpot.
     *
     * @return true si todas coinciden.
     */
    public boolean isJackpot()
    {
        if(wheels.size() == 0){
            return false;
        }

        String first =
            wheels.get(0)
                  .getVisibleSymbol();

        if(first.equals("")){
            return false;
        }

        for(int i = 1;
            i < wheels.size();
            i++){

            if(!first.equals(
                wheels.get(i)
                      .getVisibleSymbol())){

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
     * Gira una rueda.
     *
     * @param wheel posición.
     */
    public void spin(int wheel)
    {
        if(wheel < 1 ||
           wheel > wheels.size()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La rueda indicada no existe."
                );
            }

            return;
        }

        if(wheels.get(wheel - 1)
                 .isLocked()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La rueda está bloqueada."
                );
            }

            return;
        }

        wheels.get(wheel - 1).spin();

        updateDisplay();

        ok = true;
    }

    /**
     * Gira una rueda una cantidad de pasos.
     *
     * Los pasos pueden ser positivos o negativos.
     *
     * @param wheel posición.
     * @param steps pasos.
     */
    public void spin(
        int wheel,
        int steps)
    {
        if(wheel < 1 ||
           wheel > wheels.size()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La rueda indicada no existe."
                );
            }

            return;
        }

        if(wheels.get(wheel - 1)
                 .isLocked()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La rueda está bloqueada."
                );
            }

            return;
        }

        if(visible){

            int direction = 1;
            int repetitions = steps;

            if(steps < 0){
                direction = -1;
                repetitions = -steps;
            }

            for(int i = 0;
                i < repetitions;
                i++){

                wheels.get(wheel - 1)
                      .spin(direction);

                updateDisplay();

                Canvas.getCanvas()
                      .wait(200);
            }
        }
        else{
            wheels.get(wheel - 1)
                  .spin(steps);

            updateDisplay();
        }

        ok = true;
    }

    /**
     * Lleva la máquina a una configuración.
     *
     * @param setSymbols configuración objetivo.
     */
    public void spin(
        String[] setSymbols)
    {
        if(setSymbols.length !=
           wheels.size()){

            ok = false;

            if(visible){
                JOptionPane.showMessageDialog(
                    null,
                    "La configuración no corresponde a la cantidad de ruedas."
                );
            }

            return;
        }

        for(int i = 0;
            i < wheels.size();
            i++){

            String objetivo =
                setSymbols[i];

            String[] simbolos =
                wheels.get(i).getSymbols();

            int posicionObjetivo = -1;

            for(int j = 0;
                j < simbolos.length;
                j++){

                if(simbolos[j]
                   .equals(objetivo)){

                    posicionObjetivo = j;
                    break;
                }
            }

            if(posicionObjetivo == -1){

                ok = false;

                if(visible){
                    JOptionPane.showMessageDialog(
                        null,
                        "El símbolo no existe en la rueda."
                    );
                }

                return;
            }

            if(wheels.get(i)
                     .isLocked()){

                ok = false;

                if(visible){
                    JOptionPane.showMessageDialog(
                        null,
                        "La rueda está bloqueada."
                    );
                }

                return;
            }

            String actual =
                wheels.get(i)
                      .getVisibleSymbol();

            int posicionActual = 0;

            for(int j = 0;
                j < simbolos.length;
                j++){

                if(simbolos[j]
                   .equals(actual)){

                    posicionActual = j;
                    break;
                }
            }

            int steps =
                posicionObjetivo -
                posicionActual;

            wheels.get(i)
                  .spin(steps);
        }

        updateDisplay();

        ok = true;
    }

    /**
     * Hace visible la máquina.
     */
    public void makeVisible()
    {
        if(!visualConfigured){

            machineFrame.changeColor("white");
            machineFrame.changeSize(
                430,
                420
            );

            machineFrame.moveHorizontal(-30);
            machineFrame.moveVertical(15);

            machineBody.changeColor("black");
            machineBody.changeSize(
                410,
                400
            );

            machineBody.moveHorizontal(-20);
            machineBody.moveVertical(25);

            lever.changeColor("white");
            lever.changeSize(
                70,
                15
            );

            lever.moveHorizontal(175);
            lever.moveVertical(285);

            leverHead.changeColor("white");
            leverHead.changeSize(
                35,
                30
            );

            leverHead.moveHorizontal(112);
            leverHead.moveVertical(245);

            visualConfigured = true;
        }

        visible = true;

        machineFrame.makeVisible();
        machineBody.makeVisible();

        updateWheelLayout();
        updateDisplay();

        lever.makeVisible();
        leverHead.makeVisible();
    }

    /**
     * Oculta la máquina y la ventana del Canvas.
     */
    public void makeInvisible()
    {
        machineFrame.makeInvisible();
        machineBody.makeInvisible();

        for(Rectangle frame :
            wheelFrames){

            frame.makeInvisible();
        }

        for(Rectangle window :
            wheelWindows){

            window.makeInvisible();
        }

        for(Circle display :
            displays){

            display.makeInvisible();
        }

        lever.makeInvisible();
        leverHead.makeInvisible();

        visible = false;

        Canvas.getCanvas()
              .setVisible(false);
    }

    /**
     * Finaliza la simulación.
     */
    public void exit()
    {
        makeInvisible();
    }

    /**
     * Indica si la última operación fue exitosa.
     *
     * @return true si fue exitosa.
     */
    public boolean ok()
    {
        return ok;
    }
}