/**
 * Representa un símbolo normal.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class NormalSymbol extends Symbol
{
    public NormalSymbol(String color)
    {
        super(color);
    }

    @Override
    public void select()
    {
        setVisible(true);
    }
}