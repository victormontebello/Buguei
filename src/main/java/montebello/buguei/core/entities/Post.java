package montebello.buguei.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Post {

    @Id
    @JsonProperty("_id")
    private String id;

    private String title;

    private String content;

    private String author;

    private List<String> tags = new ArrayList<>();

    private String createdAt;

    private String updatedAt;
}
