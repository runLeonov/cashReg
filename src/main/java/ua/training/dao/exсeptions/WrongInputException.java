package ua.training.dao.exсeptions;

public class WrongInputException extends Exception {
    public WrongInputException() {
        super();
    }

    public WrongInputException(String message) {
        super(message);
    }

}
