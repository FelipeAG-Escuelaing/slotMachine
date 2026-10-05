/**
 * Clase abstracta que representa un símbolo
 * de la máquina tragamonedas.
 *
 * @author Felipe Amador Gonzalez - Jean Paolo Pena Romero
 * @version 1.0
 */
public abstract class Symbol
{
    private String color;
    private boolean visible;

    protected Symbol(String color)
    {
        this.color = color;
        visible = true;
    }

    public abstract void select();

    public String getColor()
    {
        return color;
    }

    public void changeColor(String newColor)
    {
        color = newColor;
    }

    public boolean isVisible()
    {
        return visible;
    }

    protected void setVisible(boolean newVisibility)
    {
        visible = newVisibility;
    }
}