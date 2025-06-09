package montebello.buguei.core.validators;

import montebello.buguei.core.entities.News;
import montebello.buguei.core.interfaces.IValidator;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class NewsValidator implements IValidator<News> {
    public boolean isTimeToUpdate() {
        LocalDate date = LocalDate.now();
        return date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean validate(String data) {
        return data != null && !data.trim().isEmpty();
    }
}
