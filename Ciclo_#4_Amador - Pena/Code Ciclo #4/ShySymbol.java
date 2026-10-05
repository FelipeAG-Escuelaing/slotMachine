/**
 * Representa un símbolo tímido.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class ShySymbol extends Symbol
{
    public ShySymbol(String color)
    {
        super(color);
    }

    @Override
    public void select()
    {
        setVisible(!isVisible());
    }
}