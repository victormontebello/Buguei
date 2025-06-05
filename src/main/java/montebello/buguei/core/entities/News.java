package montebello.buguei.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class News {

    @MongoId
    @JsonProperty("_id")
    private String id;

    private String title;

    private String description;

    private String content;

    private String author;

    private String urlToImage;

    private String publishedAt;

    private String url;
}
