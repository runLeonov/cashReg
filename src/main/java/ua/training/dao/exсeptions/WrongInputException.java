package ua.training.dao.exсeptions;

public class WrongInputException extends NumberFormatException {
    public WrongInputException() {
        super();
    }

    public WrongInputException(String message) {
        super(message);
    }

}
