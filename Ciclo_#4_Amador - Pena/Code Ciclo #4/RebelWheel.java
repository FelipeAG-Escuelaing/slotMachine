/**
 * Representa una rueda rebelde.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class RebelWheel extends Wheel
{
    public RebelWheel()
    {
        super();
    }

    @Override
    public void lock()
    {
        // Una rueda rebelde no puede bloquearse.
    }

    @Override
    public boolean isLocked()
    {
        return false;
    }
}