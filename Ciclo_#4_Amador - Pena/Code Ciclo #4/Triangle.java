import java.awt.Polygon;

/**
 * Representa un triángulo.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public class Triangle extends Figure
{
    private int height;
    private int width;

    /**
     * Constructor del triángulo.
     */
    public Triangle()
    {
        super(80, 20, "white");

        height = 30;
        width = 30;
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

            int[] horizontalPoints = {
                getXPosition(),
                getXPosition() + width / 2,
                getXPosition() + width
            };

            int[] verticalPoints = {
                getYPosition() + height,
                getYPosition(),
                getYPosition() + height
            };

            canvas.draw(
                this,
                getColor(),
                new Polygon(
                    horizontalPoints,
                    verticalPoints,
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
     * Cambia el tamaño del triángulo.
     *
     * @param newHeight nueva altura.
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