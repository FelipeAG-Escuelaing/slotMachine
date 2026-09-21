import java.awt.geom.*;

/**
 * Representa un círculo.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class Circle extends Figure
{
    public static final double PI = 3.1416;

    private int diameter;

    /**
     * Constructor del círculo.
     */
    public Circle()
    {
        super(20, 15, "blue");
        diameter = 30;
    }

    /**
     * Dibuja el círculo.
     */
    @Override
    protected void draw()
    {
        if(getIsVisible()){
            Canvas canvas =
                Canvas.getCanvas();

            canvas.draw(
                this,
                getColor(),
                new Ellipse2D.Double(
                    getXPosition(),
                    getYPosition(),
                    diameter,
                    diameter
                )
            );

            canvas.wait(10);
        }
    }

    /**
     * Borra el círculo.
     */
    @Override
    protected void erase()
    {
        if(getIsVisible()){
            Canvas.getCanvas().erase(this);
        }
    }

    /**
     * Cambia el tamaño del círculo.
     *
     * @param newDiameter nuevo diámetro.
     */
    public void changeSize(int newDiameter)
    {
        erase();

        diameter = newDiameter;

        draw();
    }
}