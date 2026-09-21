import java.awt.*;

/**
 * Representa un rectángulo.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class Rectangle extends Figure
{
    public static int EDGES = 4;

    private int height;
    private int width;

    /**
     * Constructor del rectángulo.
     */
    public Rectangle()
    {
        super(70, 15, "magenta");

        height = 30;
        width = 40;
    }

    /**
     * Dibuja el rectángulo.
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
                new java.awt.Rectangle(
                    getXPosition(),
                    getYPosition(),
                    width,
                    height
                )
            );

            canvas.wait(10);
        }
    }

    /**
     * Borra el rectángulo.
     */
    @Override
    protected void erase()
    {
        if(getIsVisible()){
            Canvas.getCanvas().erase(this);
        }
    }

    /**
     * Cambia el tamaño.
     *
     * @param newHeight nuevo alto.
     * @param newWidth nuevo ancho.
     */
    public void changeSize(
        int newHeight,
        int newWidth)
    {
        erase();

        height = newHeight;
        width = newWidth;

        draw();
    }
}