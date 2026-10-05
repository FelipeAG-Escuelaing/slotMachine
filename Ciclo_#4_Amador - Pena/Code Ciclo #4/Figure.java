/**
 * Clase abstracta que representa las características
 * comunes de las figuras.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public abstract class Figure
{
    private int horizontalPosition;
    private int verticalPosition;
    private String color;
    private boolean visible;

    /**
     * Constructor de una figura.
     *
     * @param horizontalPosition posición horizontal.
     * @param verticalPosition posición vertical.
     * @param color color de la figura.
     */
    protected Figure(
        int horizontalPosition,
        int verticalPosition,
        String color)
    {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
        this.color = color;
        visible = false;
    }

    /**
     * Hace visible la figura.
     */
    public void makeVisible()
    {
        visible = true;
        draw();
    }

    /**
     * Hace invisible la figura.
     */
    public void makeInvisible()
    {
        erase();
        visible = false;
    }

    /**
     * Mueve la figura a la derecha.
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Mueve la figura a la izquierda.
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Mueve la figura hacia arriba.
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Mueve la figura hacia abajo.
     */
    public void moveDown()
    {
        moveVertical(20);
    }

    /**
     * Mueve la figura horizontalmente.
     *
     * @param distance distancia del movimiento.
     */
    public void moveHorizontal(int distance)
    {
        erase();

        horizontalPosition =
            horizontalPosition + distance;

        draw();
    }

    /**
     * Mueve la figura verticalmente.
     *
     * @param distance distancia del movimiento.
     */
    public void moveVertical(int distance)
    {
        erase();

        verticalPosition =
            verticalPosition + distance;

        draw();
    }

    /**
     * Mueve lentamente la figura horizontalmente.
     *
     * @param distance distancia del movimiento.
     */
    public void slowMoveHorizontal(int distance)
    {
        int direction;

        if(distance < 0){
            direction = -1;
            distance = -distance;
        }
        else{
            direction = 1;
        }

        for(int step = 0;
            step < distance;
            step++){

            horizontalPosition =
                horizontalPosition + direction;

            draw();
        }
    }

    /**
     * Mueve lentamente la figura verticalmente.
     *
     * @param distance distancia del movimiento.
     */
    public void slowMoveVertical(int distance)
    {
        int direction;

        if(distance < 0){
            direction = -1;
            distance = -distance;
        }
        else{
            direction = 1;
        }

        for(int step = 0;
            step < distance;
            step++){

            verticalPosition =
                verticalPosition + direction;

            draw();
        }
    }

    /**
     * Cambia el color de la figura.
     *
     * @param newColor nuevo color.
     */
    public void changeColor(String newColor)
    {
        erase();

        color = newColor;

        draw();
    }

    /**
     * Retorna la posición horizontal.
     *
     * @return posición horizontal.
     */
    protected int getXPosition()
    {
        return horizontalPosition;
    }

    /**
     * Retorna la posición vertical.
     *
     * @return posición vertical.
     */
    protected int getYPosition()
    {
        return verticalPosition;
    }

    /**
     * Retorna el color actual.
     *
     * @return color actual.
     */
    protected String getColor()
    {
        return color;
    }

    /**
     * Indica si la figura está visible.
     *
     * @return true si está visible.
     */
    protected boolean getIsVisible()
    {
        return visible;
    }

    /**
     * Dibuja la figura.
     */
    protected abstract void draw();

    /**
     * Borra la figura.
     */
    protected abstract void erase();
}