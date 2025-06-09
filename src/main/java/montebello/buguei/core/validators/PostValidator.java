package montebello.buguei.core.validators;

import montebello.buguei.core.entities.Post;
import montebello.buguei.core.interfaces.IValidator;
import org.springframework.stereotype.Component;

@Component
public class PostValidator implements IValidator<Post> {
    public boolean validate(String data) {
        return false;
    }

    public boolean isTimeToUpdate(){
        return true;
    }
}
