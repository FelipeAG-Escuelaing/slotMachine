/**
 * Representa una rueda lefty.
 *
 * Una rueda lefty necesita una rueda a su izquierda
 * y, al girar, copia el estado visible de esa rueda.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class LeftyWheel extends Wheel
{
    private Wheel leftWheel;
    private Symbol copiedSymbol;

    /**
     * Constructor.
     */
    public LeftyWheel()
    {
        super();
        leftWheel = null;
        copiedSymbol = null;
    }

    /**
     * Asigna la rueda que está a la izquierda.
     *
     * @param wheel rueda de la izquierda.
     */
    public void setLeftWheel(Wheel wheel)
    {
        leftWheel = wheel;

        if(wheel == null){
            copiedSymbol = null;
        }
    }

    /**
     * Gira la rueda lefty.
     *
     * Al girar, copia el estado visible de la
     * rueda que está inmediatamente a su izquierda.
     *
     * @param steps cantidad de pasos.
     */
    @Override
    public void spin(int steps)
    {
        if(!isLocked() &&
           leftWheel != null){

            copiedSymbol =
                leftWheel.getVisibleSymbolObject();
        }
    }

    /**
     * Retorna el símbolo visible.
     *
     * Si la rueda ya copió el estado de la rueda
     * izquierda, retorna ese símbolo.
     *
     * @return símbolo visible.
     */
    @Override
    public String getVisibleSymbol()
    {
        if(copiedSymbol != null){
            return copiedSymbol.getColor();
        }

        return super.getVisibleSymbol();
    }

    /**
     * Retorna el objeto Symbol visible.
     *
     * @return símbolo visible.
     */
    @Override
    public Symbol getVisibleSymbolObject()
    {
        if(copiedSymbol != null){
            return copiedSymbol;
        }

        return super.getVisibleSymbolObject();
    }
}