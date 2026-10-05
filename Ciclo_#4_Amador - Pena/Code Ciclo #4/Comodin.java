/**
 * Representa un símbolo comodín.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class Comodin extends Symbol
{
    public Comodin()
    {
        super("white");
    }

    @Override
    public void select()
    {
        setVisible(true);
    }

    public boolean represents(String color)
    {
        return true;
    }
}