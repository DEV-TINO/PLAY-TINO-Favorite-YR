package tino.playtino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tino.playtino.Bean.Small.*;
import tino.playtino.domain.DTO.RequestFavoriteRankDTO;
import tino.playtino.domain.DTO.ResponseFavoriteRankDTO;
import tino.playtino.domain.DTO.ResponseFavoriteRanksDTO;
import tino.playtino.domain.Favorite;
import tino.playtino.domain.FavoriteRank;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.repository.JpaFavoriteRankRepository;

import java.util.*;

@Service
public class FavoriteRankService {

    JpaFavoriteRankRepository jpaFavoriteRankRepository;
    SaveFavoriteRankDAOBean saveFavoriteRankDAOBean;
    GetFavoriteRankCountBean getFavoriteRankCountBean;
    GetFavoriteDAOsBean getFavoriteDAOsBean;
    GetFavoriteDAOBean getFavoriteDAOBean;

    @Autowired
    public FavoriteRankService(JpaFavoriteRankRepository jpaFavoriteRankRepository, SaveFavoriteRankDAOBean saveFavoriteRankDAOBean, GetFavoriteRankCountBean getFavoriteRankCountBean, GetFavoriteDAOsBean getFavoriteDAOsBean, GetFavoriteDAOBean getFavoriteDAOBean){
        this.jpaFavoriteRankRepository = jpaFavoriteRankRepository;
        this.saveFavoriteRankDAOBean = saveFavoriteRankDAOBean;
        this.getFavoriteRankCountBean = getFavoriteRankCountBean;
        this.getFavoriteDAOsBean = getFavoriteDAOsBean;
        this.getFavoriteDAOBean = getFavoriteDAOBean;
    }

    // 랭킹[게임에서 1등한 Favorite] 저장
    public ResponseSuccess saveRank(RequestFavoriteRankDTO requestFavoriteRankDTO){
        // FavoriteRank(DAO) 생성, DTO를 DAO로 변환
        FavoriteRank favoriteRank = new FavoriteRank();
        favoriteRank.setGameId(requestFavoriteRankDTO.getGameId());
        favoriteRank.setFavoriteId(requestFavoriteRankDTO.getFavoriteId());

        // favoriteRank DAO 저장, '성공 여부'를 반환
        return saveFavoriteRankDAOBean.exec(favoriteRank);

    }

    // 랭킹 전체 조회
    public ResponseFavoriteRanksDTO readAll(){

        // favoriteId와 count를 저장할 Map<UUID, Integer> 생성
        Map<UUID, Integer> map =  new HashMap<>();

        // Favorite 전체 검색
        List<Favorite> favoriteList = getFavoriteDAOsBean.exec();

        // Favorite 하나씩 꺼내(for)
        for(Favorite favorite : favoriteList) {

            // favorite에서 FavoriteId get하고
            UUID favoriteId = favorite.getFavoriteId();

            // favoriteId로 getFavoriteRankCountBean의 exec(favoriteId) 써서 카운트 알아내고
            Integer count = getFavoriteRankCountBean.exec(favoriteId);

            // favoriteId와 count를 Map에 Put 한다.
            map.put(favoriteId, count);

        }
        // -> favoriteId : count 로 구성된 Map이 된다.

        // Map에서 count를 기준으로 내림차순 정렬
        List<Map.Entry<UUID, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(new Comparator<Map.Entry<UUID, Integer>>() {
            @Override
            public int compare(Map.Entry<UUID, Integer> o1, Map.Entry<UUID, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        // 내림차순 정렬된 순서대로 (반환할) DTO(랭킹)의 리스트를 생성하고 값 설정하는 과정!
        // List<ResponseFavoriteRankDTO> favoriteRankList 생성
        List<ResponseFavoriteRankDTO> favoriteRankList = new ArrayList<>();

        // [ count : 내림차순 ] 정렬된 map에서 key 하나씩 꺼내(for)
        for(Map.Entry<UUID, Integer> entry : entryList) {

            UUID favoriteId = entry.getKey();

            // favoriteId로 Favorite 찾아
            Favorite favorite = getFavoriteDAOBean.exec(favoriteId);

            // ResponseFavoriteRankDTO 생성 및 초기화
            ResponseFavoriteRankDTO favoriteRank = new ResponseFavoriteRankDTO();
            favoriteRank.setFavoriteId(favoriteId);
            favoriteRank.setFavoriteImage(favorite.getFavoriteImage());
            favoriteRank.setFavoriteTitle(favorite.getFavoriteTitle());
            favoriteRank.setFavoriteRankCount(map.get(favoriteId));

            // favoriteRankList에 저장(add)
            favoriteRankList.add(favoriteRank);
        }

        // RanksDTO 생성
        ResponseFavoriteRanksDTO responseFavoriteRanksDTO = new ResponseFavoriteRanksDTO();

        // DTO 값 설정 : 완료된 전체 게임 수, 찾아서 정렬한 favoriteRankList
        responseFavoriteRanksDTO.setFavoriteRankTotal(getFavoriteRankCountBean.exec());
        responseFavoriteRanksDTO.setFavoriteRanks(favoriteRankList);

        // 설정된 RanksDTO 반환
        return responseFavoriteRanksDTO;
    }
}
