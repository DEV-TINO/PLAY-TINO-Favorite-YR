package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import tino.playtino.domain.FavoriteCountDAO;
import tino.playtino.repository.JpaFavoriteCountRepository;

@Component
public class GetFavoriteCountDAOsBean {

    JpaFavoriteCountRepository jpaFavoriteCountRepository;

    @Autowired
    public GetFavoriteCountDAOsBean(JpaFavoriteCountRepository jpaFavoriteCountRepository){
        this.jpaFavoriteCountRepository = jpaFavoriteCountRepository;
    }

    // 검색 - 페이징
    public Page<FavoriteCountDAO> exec(Pageable pageable){
        return jpaFavoriteCountRepository.findAll(pageable);
    }

}
