package application;

public class ExceptionHandler extends Exception{ //Taget fra Huberts Library-app fra kurset.

    private static final long serialVersionUID = 5644804383994321392L;
    public ExceptionHandler(String err){
        super(err);
    }
}
