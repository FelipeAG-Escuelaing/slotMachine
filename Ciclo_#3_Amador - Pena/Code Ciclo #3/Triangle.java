import java.awt.*;

/**
 * Representa un triángulo.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class Triangle extends Figure
{
    public static int VERTICES = 3;

    private int height;
    private int width;

    /**
     * Constructor del triángulo.
     */
    public Triangle()
    {
        super(140, 15, "green");

        height = 30;
        width = 40;
    }

    /**
     * Dibuja el triángulo.
     */
    @Override
    protected void draw()
    {
        if(getIsVisible()){
            Canvas canvas =
                Canvas.getCanvas();

            int[] xpoints = {
                getXPosition(),
                getXPosition() + (width / 2),
                getXPosition() - (width / 2)
            };

            int[] ypoints = {
                getYPosition(),
                getYPosition() + height,
                getYPosition() + height
            };

            canvas.draw(
                this,
                getColor(),
                new Polygon(
                    xpoints,
                    ypoints,
                    3
                )
            );

            canvas.wait(10);
        }
    }

    /**
     * Borra el triángulo.
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