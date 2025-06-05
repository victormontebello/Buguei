package montebello.buguei.core.interfaces;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public interface IImuttableMapper<TClass> {
    TClass Map(Document data);
}
