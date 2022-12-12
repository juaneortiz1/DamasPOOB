package domain;

/**
 * Clase de excepcion del programa
 */
public class DamasPOOBExcepcion extends Exception {
    public static final String TYPE_TXT_ERROR= "La extencion no es .txt";
    public DamasPOOBExcepcion(String message){super(message);}
}
