package montebello.buguei.core.interfaces;

public interface IValidator<TClass> {
    /**
     * Validates the input data.
     *
     * @param data The data to validate.
     * @return true if the data is valid, false otherwise.
     */
    boolean validate(String data);

    /**
     * Checks if the current time is suitable for an update.
     *
     * @return true if it's time to update, false otherwise.
     */
    boolean isTimeToUpdate();
}
