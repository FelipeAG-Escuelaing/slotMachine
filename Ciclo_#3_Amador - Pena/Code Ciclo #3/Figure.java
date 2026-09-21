/**
 * Clase abstracta que representa las características
 * comunes de las figuras.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public abstract class Figure implements Movable
{
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Constructor de una figura.
     *
     * @param xPosition posición horizontal.
     * @param yPosition posición vertical.
     * @param color color.
     */
    protected Figure(
        int xPosition,
        int yPosition,
        String color)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        isVisible = false;
    }

    /**
     * Hace visible la figura.
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }

    /**
     * Hace invisible la figura.
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
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
     * @param distance distancia.
     */
    public void moveHorizontal(int distance)
    {
        erase();
        xPosition = xPosition + distance;
        draw();
    }

    /**
     * Mueve la figura verticalmente.
     *
     * @param distance distancia.
     */
    public void moveVertical(int distance)
    {
        erase();
        yPosition = yPosition + distance;
        draw();
    }

    /**
     * Mueve lentamente la figura horizontalmente.
     *
     * @param distance distancia.
     */
    public void slowMoveHorizontal(int distance)
    {
        int delta;

        if(distance < 0){
            delta = -1;
            distance = -distance;
        }
        else{
            delta = 1;
        }

        for(int i = 0;
            i < distance;
            i++){

            xPosition =
                xPosition + delta;

            draw();
        }
    }

    /**
     * Mueve lentamente la figura verticalmente.
     *
     * @param distance distancia.
     */
    public void slowMoveVertical(int distance)
    {
        int delta;

        if(distance < 0){
            delta = -1;
            distance = -distance;
        }
        else{
            delta = 1;
        }

        for(int i = 0;
            i < distance;
            i++){

            yPosition =
                yPosition + delta;

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
        return xPosition;
    }

    /**
     * Retorna la posición vertical.
     *
     * @return posición vertical.
     */
    protected int getYPosition()
    {
        return yPosition;
    }

    /**
     * Retorna el color.
     *
     * @return color actual.
     */
    protected String getColor()
    {
        return color;
    }

    /**
     * Indica si la figura es visible.
     *
     * @return true si es visible.
     */
    protected boolean getIsVisible()
    {
        return isVisible;
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