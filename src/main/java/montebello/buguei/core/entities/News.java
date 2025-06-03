package montebello.buguei.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @MongoId
    @JsonProperty("_id")
    private ObjectId id;

    private String title;

    private String content;

    private String author;

    private String sourceUrl;
}
