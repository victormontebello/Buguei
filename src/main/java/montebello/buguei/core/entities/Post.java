package montebello.buguei.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @MongoId
    @JsonProperty("_id")
    private ObjectId id;

    private String title;

    private String content;

    private String author;

    private List<String> tags = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
