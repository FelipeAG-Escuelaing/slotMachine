import java.util.ArrayList;

/**
 * Representa una rueda de la máquina tragamonedas.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class Wheel
{
    private ArrayList<Symbol> symbols;
    private int visiblePosition;
    private boolean locked;

    public Wheel()
    {
        symbols = new ArrayList<Symbol>();
        visiblePosition = 0;
        locked = false;
    }

    /**
     * Agrega un símbolo normal usando su color.
     *
     * @param color color del símbolo.
     */
    public void addSymbol(String color)
    {
        symbols.add(new NormalSymbol(color));
    }

    /**
     * Agrega un símbolo.
     *
     * @param symbol símbolo que se agrega.
     */
    public void addSymbol(Symbol symbol)
    {
        symbols.add(symbol);
    }

    /**
     * Elimina un símbolo por su color.
     *
     * @param color color del símbolo.
     * @return true si fue eliminado.
     */
    public boolean delSymbol(String color)
    {
        for(int symbolPosition = 0;
            symbolPosition < symbols.size();
            symbolPosition++){

            if(symbols.get(symbolPosition)
                     .getColor()
                     .equals(color)){

                symbols.remove(symbolPosition);

                if(symbols.size() == 0){
                    visiblePosition = 0;
                }
                else if(visiblePosition >= symbols.size()){
                    visiblePosition = 0;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Coloca un símbolo como visible.
     *
     * @param color color del símbolo.
     * @return true si existe.
     */
    public boolean placeSymbol(String color)
    {
        for(int symbolPosition = 0;
            symbolPosition < symbols.size();
            symbolPosition++){

            if(symbols.get(symbolPosition)
                     .getColor()
                     .equals(color)){

                visiblePosition = symbolPosition;

                symbols.get(symbolPosition)
                       .select();

                return true;
            }
        }

        return false;
    }

    /**
     * Gira una posición.
     */
    public void spin()
    {
        spin(1);
    }

    /**
     * Gira una cantidad de pasos.
     *
     * @param steps cantidad de pasos.
     */
    public void spin(int steps)
    {
        if(!locked && symbols.size() > 0){

            visiblePosition =
                (visiblePosition + steps)
                % symbols.size();

            if(visiblePosition < 0){
                visiblePosition =
                    visiblePosition + symbols.size();
            }

            symbols.get(visiblePosition)
                   .select();
        }
    }

    /**
     * Retorna el color del símbolo seleccionado.
     *
     * @return color del símbolo.
     */
    public String getVisibleSymbol()
    {
        if(symbols.size() == 0){
            return "";
        }

        return symbols.get(visiblePosition)
                      .getColor();
    }

    /**
     * Retorna el objeto Symbol seleccionado.
     *
     * @return símbolo seleccionado.
     */
    public Symbol getVisibleSymbolObject()
    {
        if(symbols.size() == 0){
            return null;
        }

        return symbols.get(visiblePosition);
    }

    /**
     * Retorna los colores de todos los símbolos.
     *
     * @return arreglo con los colores.
     */
    public String[] getSymbols()
    {
        String[] result =
            new String[symbols.size()];

        for(int symbolPosition = 0;
            symbolPosition < symbols.size();
            symbolPosition++){

            result[symbolPosition] =
                symbols.get(symbolPosition)
                       .getColor();
        }

        return result;
    }

    /**
     * Bloquea la rueda.
     */
    public void lock()
    {
        locked = true;
    }

    /**
     * Desbloquea la rueda.
     */
    public void unlock()
    {
        locked = false;
    }

    /**
     * Indica si la rueda está bloqueada.
     *
     * @return true si está bloqueada.
     */
    public boolean isLocked()
    {
        return locked;
    }
}