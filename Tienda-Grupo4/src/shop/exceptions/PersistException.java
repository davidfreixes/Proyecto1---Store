package shop.exceptions;

public class PersistException extends Exception{
    public PersistException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PersistException: ");
        sb.append(getMessage());
        return sb.toString();
    }
}
