package exceptions;

public class PriQUnderflowException extends Exception
{
    private static final long serialVersionUID = 1L;
    public PriQUnderflowException()
    {
        super();
    }
    public PriQUnderflowException(String message)
    {
        super(message);
    }
}
