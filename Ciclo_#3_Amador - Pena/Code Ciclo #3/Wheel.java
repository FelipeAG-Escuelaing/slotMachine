import java.util.ArrayList;

/**
 * Representa una rueda de una máquina tragamonedas.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class Wheel
{
    private ArrayList<String> symbols;
    private int visiblePosition;
    private boolean locked;

    /**
     * Constructor de la rueda.
     */
    public Wheel()
    {
        symbols =
            new ArrayList<String>();

        visiblePosition = 0;
        locked = false;
    }

    /**
     * Agrega un símbolo.
     *
     * @param color símbolo.
     */
    public void addSymbol(String color)
    {
        symbols.add(color);
    }

    /**
     * Elimina un símbolo.
     *
     * @param color símbolo.
     * @return true si fue eliminado.
     */
    public boolean delSymbol(String color)
    {
        boolean eliminado =
            symbols.remove(color);

        if(visiblePosition >= symbols.size()
           && symbols.size() > 0){

            visiblePosition = 0;
        }

        return eliminado;
    }

    /**
     * Coloca un símbolo como visible.
     *
     * @param color símbolo.
     * @return true si existe.
     */
    public boolean placeSymbol(String color)
    {
        for(int i = 0;
            i < symbols.size();
            i++){

            if(symbols.get(i).equals(color)){
                visiblePosition = i;
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
     * Los pasos pueden ser positivos o negativos.
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
                    visiblePosition
                    + symbols.size();
            }
        }
    }

    /**
     * Retorna el símbolo visible.
     *
     * @return símbolo visible.
     */
    public String getVisibleSymbol()
    {
        if(symbols.size() == 0){
            return "";
        }

        return symbols.get(
            visiblePosition
        );
    }

    /**
     * Retorna todos los símbolos.
     *
     * @return símbolos.
     */
    public String[] getSymbols()
    {
        String[] result =
            new String[symbols.size()];

        for(int i = 0;
            i < symbols.size();
            i++){

            result[i] =
                symbols.get(i);
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
     * Indica si está bloqueada.
     *
     * @return true si está bloqueada.
     */
    public boolean isLocked()
    {
        return locked;
    }
}