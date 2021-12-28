package utilext;

public class CompositePatternException extends Exception
{
    public CompositePatternException() {
        super("Method not implemented and should not be called.");
    }

    public CompositePatternException(String str) {
        super(str);
    }
}
