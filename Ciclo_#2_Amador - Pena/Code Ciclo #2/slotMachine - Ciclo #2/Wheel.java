/**
 * Representa una rueda de una máquina tragamonedas.
 *
 * Cada rueda almacena una colección de símbolos identificados por colores
 * y mantiene la posición del símbolo actualmente visible.
 *
 * La rueda permite agregar y eliminar símbolos, seleccionar un símbolo
 * visible, girar una cantidad de pasos y fijar o liberar la rueda.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * Crea una rueda vacía sin símbolos y con la primera
 * posición visible inicializada en cero.
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
        symbols = new ArrayList<String>();
        visiblePosition = 0;
        locked = false;
    }

    /**
     * Agrega un nuevo símbolo al final de la rueda.
     *
     * @param color color que identifica el símbolo.
     */
    public void addSymbol(String color)
    {
        symbols.add(color);
    }

    /**
     * Elimina la primera aparición del símbolo indicado.
     *
     * Si la posición visible queda fuera del rango válido
     * después de la eliminación, se reinicia a la primera
     * posición disponible.
     *
     * @param color símbolo que se desea eliminar.
     * @return true si el símbolo fue eliminado; false si no existía.
     */
    public boolean delSymbol(String color)
    {
        boolean eliminado = symbols.remove(color);

        if(visiblePosition >= symbols.size() && symbols.size() > 0){
            visiblePosition = 0;
        }

        return eliminado;
    }

    /**
     * Ubica como visible el símbolo indicado.
     *
     * Si el símbolo existe, se cambia la posición visible
     * y se retorna true. Si no existe, se retorna false.
     *
     * @param color símbolo que se desea mostrar.
     * @return true si el símbolo existe; false en caso contrario.
     */
    public boolean placeSymbol(String color)
    {
        for(int i = 0; i < symbols.size(); i++){

            if(symbols.get(i).equals(color)){
                visiblePosition = i;
                return true;
            }
        }

        return false;
    }

    /**
     * Avanza la rueda una posición de forma circular.
     *
     * Cuando se alcanza el último símbolo, la rueda
     * vuelve a la primera posición.
     *
     * La rueda no gira si está bloqueada.
     */
    public void spin()
    {
        if(!locked && symbols.size() > 0){
            visiblePosition = (visiblePosition + 1) % symbols.size();
        }
    }

    /**
     * Avanza la rueda una cantidad determinada de pasos.
     *
     * El giro es circular y la rueda no gira si está bloqueada.
     *
     * @param steps cantidad de pasos que debe avanzar la rueda.
     */
    public void spin(int steps)
    {
        if(!locked && symbols.size() > 0){
            visiblePosition =
                (visiblePosition + steps) % symbols.size();
        }
    }

    /**
     * Retorna el símbolo actualmente visible.
     *
     * @return símbolo visible o cadena vacía si la rueda
     * no contiene símbolos.
     */
    public String getVisibleSymbol()
    {
        if(symbols.size() == 0){
            return "";
        }

        return symbols.get(visiblePosition);
    }

    /**
     * Retorna todos los símbolos de la rueda.
     *
     * @return arreglo con los símbolos de la rueda.
     */
    public String[] getSymbols()
    {
        String[] result = new String[symbols.size()];

        for(int i = 0; i < symbols.size(); i++){
            result[i] = symbols.get(i);
        }

        return result;
    }

    /**
     * Fija la rueda.
     */
    public void lock()
    {
        locked = true;
    }

    /**
     * Libera la rueda para que pueda volver a girar.
     */
    public void unlock()
    {
        locked = false;
    }

    /**
     * Indica si la rueda está fijada.
     *
     * @return true si la rueda está fijada; false en caso contrario.
     */
    public boolean isLocked()
    {
        return locked;
    }
}