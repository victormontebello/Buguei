package montebello.buguei.core.interfaces;

public interface IValidator<TClass> {
    boolean validate(String data);

    boolean isTimeToUpdate();
}
