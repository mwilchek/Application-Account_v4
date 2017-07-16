package exceptions;

/**
 * Created by seeme on 7/16/2017.
 */

public class PriQOverflowException extends Exception
{
    private static final long serialVersionUID = 1L;
    public PriQOverflowException()
    {
        super();
    }
    public PriQOverflowException(String message)
    {
        super(message);
    }
}