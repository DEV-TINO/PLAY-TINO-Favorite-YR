package tino.playtino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tino.playtino.Bean.Small.*;
import tino.playtino.domain.*;
import tino.playtino.domain.DTO.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    SaveCommentDAOBean saveCommentDAOBean;
    UpdateCommentDAOBean updateCommentDAOBean;
    DeleteCommentDAOBean deleteCommentDAOBean;
    GetCommentDAOBean getCommentDAOBean;
    GetCommentDAOsBean getCommentDAOsBean;
    GetBooleanUserHeartExistsBean getBooleanUserHeartExistsBean;

    @Autowired
    public CommentService(SaveCommentDAOBean saveCommentDAOBean, UpdateCommentDAOBean updateCommentDAOBean, DeleteCommentDAOBean deleteCommentDAOBean, GetCommentDAOBean getCommentDAOBean, GetCommentDAOsBean getCommentDAOsBean, GetBooleanUserHeartExistsBean getBooleanUserHeartExistsBean){
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.updateCommentDAOBean = updateCommentDAOBean;
        this.deleteCommentDAOBean = deleteCommentDAOBean;
        this.getCommentDAOBean = getCommentDAOBean;
        this.getCommentDAOsBean = getCommentDAOsBean;
        this.getBooleanUserHeartExistsBean = getBooleanUserHeartExistsBean;
    }

    /*
    //댓글 조회
    public ResponseCommentDTO read(UUID commentId){

        //id로 댓글(DAO) 찾아서
        CommentDAO commentDAO = getCommentDAOBean.exec(commentId);
        
        //DAO -> DTO 변환(ResponseCommentDTO)
        ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();

        //return ResponseCommentDTO.builder()
        //        .commentId(commentId)
        //        .userId(comment.getUserId())
        //        .build();

        responseCommentDTO.setCommentId(commentId);
        responseCommentDTO.setUserId(commentDAO.getUserId());
        responseCommentDTO.setContent(commentDAO.getContent());
        responseCommentDTO.setHeartCount(commentDAO.getHeartCount());
        responseCommentDTO.setUploadTime(commentDAO.getUploadTime());
        
        //DTO 반환
        return responseCommentDTO;
    }

    //댓글 전체 조회
    public List<ResponseCommentDTO> readAll(){

        //Comment(DAO) 전체(List)를 찾음 -> commentList
        List<CommentDAO> commentDAOList = getCommentDAOsBean.exec();

        //DTOList 생성
        List<ResponseCommentDTO> responseCommentDTOList = new ArrayList<>();

        //commentList의 DAO를 DTO로 변환해 DTOList에 추가
        for(CommentDAO commentDAO : commentDAOList){
            ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();

            responseCommentDTO.setCommentId(commentDAO.getCommentId());
            responseCommentDTO.setUserId(commentDAO.getUserId());
            responseCommentDTO.setContent(commentDAO.getContent());
            responseCommentDTO.setHeartCount(commentDAO.getHeartCount());
            responseCommentDTO.setUploadTime(commentDAO.getUploadTime());

            responseCommentDTOList.add(responseCommentDTO);
        }

        //생성한 responseCommentDTOList 반환
        return responseCommentDTOList;
    }

    //댓글 전체 조회 - 최신순
    public List<ResponseCommentByUserDTO> readAllByTime(UUID userId){

        //Comment(DAO) 전체(List)를 시간순으로 정렬해서 찾음 -> commentList
        List<CommentDAO> commentDAOList = getCommentDAOsBean.execByTime();

        //유저아이디에 따른 DTOList 생성(해당 사용자가 하트를 눌렀는지도 같이 반환해줄 것)
        List<ResponseCommentByUserDTO> responseCommentByUserDTOList = new ArrayList<>();

        //commentList의 DAO를 DTO로 변환해 DTOList에 추가
        for(CommentDAO commentDAO : commentDAOList){
            ResponseCommentByUserDTO responseCommentDTO = new ResponseCommentByUserDTO();

            responseCommentDTO.setCommentId(commentDAO.getCommentId());
            responseCommentDTO.setUserId(commentDAO.getUserId());
            responseCommentDTO.setContent(commentDAO.getContent());
            responseCommentDTO.setHeartCount(commentDAO.getHeartCount());
            responseCommentDTO.setUploadTime(commentDAO.getUploadTime());

            //해당 댓글에 사용자가 하트를 눌렀는지 여부도 저장
            Boolean userHeartExists = getBooleanUserHeartExistsBean.checkUserHeartExists(commentDAO.getCommentId(), userId);
            responseCommentDTO.setUserHeartExists(userHeartExists);

            responseCommentByUserDTOList.add(responseCommentDTO);
        }

        //생성한 responseCommentByUserDTOList 반환
        return responseCommentByUserDTOList;

    }

    // 댓글 전체 조회 - 추천순
    public List<ResponseCommentByUserDTO> readAllByHeart(UUID userId){

        // Comment(DAO) 전체(List)를 하트순으로 정렬해서 찾음 -> commentList
        // 하트 개수가 동일하면 최신순
        List<CommentDAO> commentDAOList = getCommentDAOsBean.execByHeart();

        // 유저아이디에 따른 DTOList 생성(해당 사용자가 하트를 눌렀는지도 같이 반환해줄 것)
        List<ResponseCommentByUserDTO> responseCommentByUserDTOList = new ArrayList<>();

        // commentList의 DAO를 DTO로 변환해 DTOList에 추가
        for(CommentDAO commentDAO : commentDAOList){
            ResponseCommentByUserDTO responseCommentDTO = new ResponseCommentByUserDTO();

            responseCommentDTO.setCommentId(commentDAO.getCommentId());
            responseCommentDTO.setUserId(commentDAO.getUserId());
            responseCommentDTO.setContent(commentDAO.getContent());
            responseCommentDTO.setHeartCount(commentDAO.getHeartCount());
            responseCommentDTO.setUploadTime(commentDAO.getUploadTime());

            // 해당 댓글에 사용자가 하트를 눌렀는지 여부도 저장
            Boolean userHeartExists = getBooleanUserHeartExistsBean.checkUserHeartExists(commentDAO.getCommentId(), userId);
            responseCommentDTO.setUserHeartExists(userHeartExists);

            responseCommentByUserDTOList.add(responseCommentDTO);
        }

        // 생성한 responseCommentByUserDTOList 반환
        return responseCommentByUserDTOList;

    }
    */

    // 댓글 조회 (페이징)
    public List<ResponseCommentByUserDTO> readCommentAll(UUID userId, int pageNo, String criteria){

        // pageNo(페이지넘버)와 criteria(기준)으로 페이징
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.by(Sort.Direction.DESC, criteria));
        Page<CommentDAO> page = getCommentDAOsBean.exec(pageable);

        // 유저아이디에 따른 DTOList 생성(해당 사용자가 하트를 눌렀는지도 같이 반환해줄 것)
        List<ResponseCommentByUserDTO> responseCommentByUserDTOList = new ArrayList<>();

        // commentList의 DAO를 DTO로 변환해 DTOList에 추가
        for(CommentDAO commentDAO : page){
            ResponseCommentByUserDTO responseCommentDTO = new ResponseCommentByUserDTO();

            responseCommentDTO.setCommentId(commentDAO.getCommentId());
            responseCommentDTO.setUserId(commentDAO.getUserId());
            responseCommentDTO.setContent(commentDAO.getContent());
            responseCommentDTO.setHeartCount(commentDAO.getHeartCount());
            responseCommentDTO.setUploadTime(commentDAO.getUploadTime());

            // 해당 댓글에 사용자가 하트를 눌렀는지 여부도 저장
            Boolean userHeartExists = getBooleanUserHeartExistsBean.checkUserHeartExists(commentDAO.getCommentId(), userId);
            responseCommentDTO.setUserHeartExists(userHeartExists);

            responseCommentByUserDTOList.add(responseCommentDTO);
        }

        // 생성한 responseCommentByUserDTOList 반환
        return responseCommentByUserDTOList;

    }

    //댓글 저장
    public ResponseSuccess createComment(RequestCommentSaveDTO requestCommentSaveDTO){

        //DAO를 생성해서 [PK, 하트개수, 업로드시간] 초기화
        CommentDAO commentDAO = new CommentDAO();

        commentDAO.setCommentId(UUID.randomUUID());
        commentDAO.setHeartCount(0);
        commentDAO.setUploadTime(LocalDateTime.now());

        //DAO에 DTO의 값(userId, Content) 넣어주기
        commentDAO.setUserId(requestCommentSaveDTO.getUserId());


        commentDAO.setContent(requestCommentSaveDTO.getContent());

        //DAO 저장
        return saveCommentDAOBean.exec(commentDAO);

    }

    //댓글 수정
    public ResponseSuccess updateComment(RequestCommentUpdateDTO requestCommentUpdateDTO){

        //DTO의 commentId로 댓글(DAO) 찾기
        CommentDAO commentDAO = getCommentDAOBean.exec(requestCommentUpdateDTO.getCommentId());

        //찾은 댓글의 내용 수정 후 저장
        return updateCommentDAOBean.exec(commentDAO, requestCommentUpdateDTO);

    }
    
    //댓글 삭제
    public ResponseSuccess deleteComment(RequestCommentDeleteDTO requestCommentDeleteDTO){

        //id를 통해 댓글 찾기(DAO)
        CommentDAO commentDAO = getCommentDAOBean.exec(requestCommentDeleteDTO.getCommentId());

        //찾은 DAO 삭제
        return deleteCommentDAOBean.exec(commentDAO, requestCommentDeleteDTO.getUserId());

    }
}
