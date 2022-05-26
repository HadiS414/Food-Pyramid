//Hadi Salameh
//#112110954
//R05

/**
 * This class is to catch an exception when there is no position available for a new prey to be added
 */

public class PositionNotAvailableException extends Exception {

    public PositionNotAvailableException(String message) {
        super(message);
    }

}
