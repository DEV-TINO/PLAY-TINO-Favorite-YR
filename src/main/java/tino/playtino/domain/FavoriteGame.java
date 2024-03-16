package tino.playtino.domain;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tino.playtino.others.StringListConverter;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavoriteGame {
    @Id
    UUID gameId;

    UUID userId;

    @Convert(converter = StringListConverter.class)
    List<Favorite> favoriteList;
}
