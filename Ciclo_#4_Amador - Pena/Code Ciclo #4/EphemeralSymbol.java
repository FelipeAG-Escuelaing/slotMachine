/**
 * Representa un símbolo efímero.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class EphemeralSymbol extends Symbol
{
    private int size;

    private static final int INITIAL_SIZE = 30;
    private static final int MINIMUM_SIZE = 5;
    private static final int SIZE_DECREASE = 5;

    public EphemeralSymbol(String color)
    {
        super(color);
        size = INITIAL_SIZE;
    }

    @Override
    public void select()
    {
        if(size > MINIMUM_SIZE){
            size = size - SIZE_DECREASE;
        }
    }

    public int getSize()
    {
        return size;
    }
}