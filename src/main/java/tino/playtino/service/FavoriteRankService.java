package tino.playtino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tino.playtino.Bean.Small.*;
import tino.playtino.domain.*;
import tino.playtino.domain.DTO.RequestFavoriteRankDTO;
import tino.playtino.domain.DTO.ResponseFavoriteRankDTO;
import tino.playtino.repository.JpaFavoriteRankRepository;

import java.util.*;

@Service
public class FavoriteRankService {

    JpaFavoriteRankRepository jpaFavoriteRankRepository;
    SaveFavoriteRankDAOBean saveFavoriteRankDAOBean;
    GetFavoriteRankCountBean getFavoriteRankCountBean;
    GetFavoriteDAOsBean getFavoriteDAOsBean;
    GetFavoriteDAOBean getFavoriteDAOBean;
    SaveFavoriteCountDAOBean saveFavoriteCountDAOBean;
    GetFavoriteCountDAOsBean getFavoriteCountDAOsBean;

    @Autowired
    public FavoriteRankService(JpaFavoriteRankRepository jpaFavoriteRankRepository, SaveFavoriteRankDAOBean saveFavoriteRankDAOBean, GetFavoriteRankCountBean getFavoriteRankCountBean, GetFavoriteDAOsBean getFavoriteDAOsBean, GetFavoriteDAOBean getFavoriteDAOBean, SaveFavoriteCountDAOBean saveFavoriteCountDAOBean, GetFavoriteCountDAOsBean getFavoriteCountDAOsBean){
        this.jpaFavoriteRankRepository = jpaFavoriteRankRepository;
        this.saveFavoriteRankDAOBean = saveFavoriteRankDAOBean;
        this.getFavoriteRankCountBean = getFavoriteRankCountBean;
        this.getFavoriteDAOsBean = getFavoriteDAOsBean;
        this.getFavoriteDAOBean = getFavoriteDAOBean;
        this.saveFavoriteCountDAOBean = saveFavoriteCountDAOBean;
        this.getFavoriteCountDAOsBean = getFavoriteCountDAOsBean;
    }

    // 랭킹[게임에서 1등한 Favorite] 저장
    public ResponseSuccess saveRank(RequestFavoriteRankDTO requestFavoriteRankDTO){
        // FavoriteRank(DAO) 생성, DTO를 DAO로 변환
        FavoriteRankDAO favoriteRankDAO = new FavoriteRankDAO();
        favoriteRankDAO.setGameId(requestFavoriteRankDTO.getGameId());
        favoriteRankDAO.setFavoriteId(requestFavoriteRankDTO.getFavoriteId());

        // favoriteRank DAO 저장, '성공 여부'를 반환
        return saveFavoriteRankDAOBean.exec(favoriteRankDAO);

    }

    /*
    // 랭킹 전체 조회
    public List<ResponseFavoriteRankDTO> readAll(){

        // favoriteId와 count를 저장할 Map<UUID, Integer> 생성
        Map<UUID, Integer> map =  new HashMap<>();

        // Favorite 전체 검색
        List<FavoriteDAO> favoriteDAOList = getFavoriteDAOsBean.exec();

        Integer totalCount = 0;

        // Favorite 하나씩 꺼내(for)
        for(FavoriteDAO favoriteDAO : favoriteDAOList) {

            // favorite에서 FavoriteId get하고
            UUID favoriteId = favoriteDAO.getFavoriteId();

            // favoriteId로 getFavoriteRankCountBean의 exec(favoriteId) 써서 카운트 알아내고
            Integer count = getFavoriteRankCountBean.exec(favoriteId);

            totalCount += count;

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
            Integer rankCount = map.get(favoriteId);

            // favoriteId로 Favorite 찾아
            FavoriteDAO favoriteDAO = getFavoriteDAOBean.exec(favoriteId);

            // ResponseFavoriteRankDTO 생성 및 초기화
            ResponseFavoriteRankDTO favoriteRank = new ResponseFavoriteRankDTO();
            favoriteRank.setFavoriteId(favoriteId);
            favoriteRank.setFavoriteImage(favoriteDAO.getFavoriteImage());
            favoriteRank.setFavoriteTitle(favoriteDAO.getFavoriteTitle());
            favoriteRank.setFavoriteRankCount(rankCount);
            if(totalCount!=0) favoriteRank.setFavoriteRankPercentage((double)rankCount/(double)totalCount);
            else favoriteRank.setFavoriteRankPercentage(0.0);

            // favoriteRankList에 저장(add)
            favoriteRankList.add(favoriteRank);
        }

        return favoriteRankList;
    }
    */

    // 랭킹 조회 - 페이징
    public List<ResponseFavoriteRankDTO> readRankAll(Integer pageNo){

        // Favorite 전체 검색
        List<FavoriteDAO> favoriteDAOList = getFavoriteDAOsBean.exec();

        Integer totalCount = 0;

        // Favorite 하나씩 꺼내(for)
        for(FavoriteDAO favoriteDAO : favoriteDAOList) {

            // favorite에서 FavoriteId get하고
            UUID favoriteId = favoriteDAO.getFavoriteId();

            // favoriteId로 getFavoriteRankCountBean의 exec(favoriteId) 써서 카운트 알아내고
            Integer rankCount = getFavoriteRankCountBean.exec(favoriteId);

            totalCount += rankCount;

            // favoriteId와 rankCount를 가지는 DAO생성, Respository에 저장
            FavoriteCountDAO favoriteCountDAO = new FavoriteCountDAO();
            favoriteCountDAO.setFavoriteId(favoriteId);
            favoriteCountDAO.setRankCount(rankCount);
            saveFavoriteCountDAOBean.exec(favoriteCountDAO);
        }

        // FavoriteCount를 pageNo(페이지넘버), rnakCount 내림차순 기준으로 정렬 페이징
        Pageable pageable = PageRequest.of(pageNo, 3, Sort.by(Sort.Direction.DESC, "rankCount"));
        Page<FavoriteCountDAO> page = getFavoriteCountDAOsBean.exec(pageable);


        // 내림차순 정렬된 순서대로 (반환할) DTO(랭킹)의 리스트를 생성하고 값 설정하는 과정!
        // List<ResponseFavoriteRankDTO> favoriteRankList 생성
        List<ResponseFavoriteRankDTO> favoriteRankList = new ArrayList<>();

        // [ count : 내림차순 ] 정렬된 map에서 key 하나씩 꺼내(for)
        for(FavoriteCountDAO favoriteCountDAO : page) {

            UUID favoriteId = favoriteCountDAO.getFavoriteId();
            Integer rankCount = favoriteCountDAO.getRankCount();

            // favoriteId로 Favorite 찾아
            FavoriteDAO favoriteDAO = getFavoriteDAOBean.exec(favoriteId);

            // ResponseFavoriteRankDTO 생성 및 초기화
            ResponseFavoriteRankDTO favoriteRankDTO = new ResponseFavoriteRankDTO();
            favoriteRankDTO.setFavoriteId(favoriteId);
            favoriteRankDTO.setFavoriteImage(favoriteDAO.getFavoriteImage());
            favoriteRankDTO.setFavoriteTitle(favoriteDAO.getFavoriteTitle());
            favoriteRankDTO.setFavoriteRankCount(rankCount);
            if(totalCount!=0) favoriteRankDTO.setFavoriteRankPercentage((double)rankCount/(double)totalCount);
            else favoriteRankDTO.setFavoriteRankPercentage(0.0);

            // favoriteRankList에 저장(add)
            favoriteRankList.add(favoriteRankDTO);
        }

        return favoriteRankList;
    }



}
