/**
 * Representa una rueda de una máquina tragamonedas.
 *
 * Cada rueda almacena una colección de símbolos identificados por colores
 * y mantiene la posición del símbolo actualmente visible.
 *
 * La rueda permite agregar y eliminar símbolos, seleccionar un símbolo
 * visible y girar para cambiar la posición mostrada.
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

    /**
     * Constructor de la rueda.
     */
    public Wheel()
    {
        symbols = new ArrayList<String>();
        visiblePosition = 0;
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
     */
    public void delSymbol(String color)
    {
        symbols.remove(color);

        if (visiblePosition >= symbols.size() && symbols.size() > 0) {
            visiblePosition = 0;
        }
    }

    /**
     * Ubica como visible el símbolo indicado.
     *
     * Si el símbolo existe varias veces, se selecciona
     * la última aparición encontrada durante el recorrido.
     *
     * @param color símbolo que se desea mostrar.
     */
    public void placeSymbol(String color)
    {
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).equals(color)) {
                visiblePosition = i;
            }
        }
    }

    /**
     * Avanza la rueda una posición de forma circular.
     *
     * Cuando se alcanza el último símbolo, la rueda
     * vuelve a la primera posición.
     */
    public void spin()
    {
        if (symbols.size() > 0) {
            visiblePosition = (visiblePosition + 1) % symbols.size();
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
        if (symbols.size() == 0) {
            return "";
        }

        return symbols.get(visiblePosition);
    }

    /**
     * Retorna todos los símbolos de la rueda.
     */
    public String[] getSymbols()
    {
        String[] result = new String[symbols.size()];

        for (int i = 0; i < symbols.size(); i++) {
            result[i] = symbols.get(i);
        }

        return result;
    }
}