package tino.playtino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tino.playtino.Bean.Small.*;
import tino.playtino.domain.*;
import tino.playtino.repository.JpaCommentRepository;

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
    JpaCommentRepository jpaCommentRepository;

    @Autowired
    public CommentService(SaveCommentDAOBean saveCommentDAOBean, UpdateCommentDAOBean updateCommentDAOBean, DeleteCommentDAOBean deleteCommentDAOBean, GetCommentDAOBean getCommentDAOBean, GetCommentDAOsBean getCommentDAOsBean, GetBooleanUserHeartExistsBean getBooleanUserHeartExistsBean, JpaCommentRepository jpaCommentRepository){
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.updateCommentDAOBean = updateCommentDAOBean;
        this.deleteCommentDAOBean = deleteCommentDAOBean;
        this.getCommentDAOBean = getCommentDAOBean;
        this.getCommentDAOsBean = getCommentDAOsBean;
        this.getBooleanUserHeartExistsBean = getBooleanUserHeartExistsBean;
        this.jpaCommentRepository = jpaCommentRepository;
    }

    //댓글 조회
    public ResponseCommentDTO read(UUID commentId){

        //id로 댓글(DAO) 찾아서
        Comment comment = getCommentDAOBean.exec(commentId);
        
        //DAO -> DTO 변환(ResponseCommentDTO)
        ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();

        responseCommentDTO.setCommentId(commentId);
        responseCommentDTO.setUserId(comment.getUserId());
        responseCommentDTO.setContent(comment.getContent());
        responseCommentDTO.setHeartCount(comment.getHeartCount());
        responseCommentDTO.setUploadTime(comment.getUploadTime());
        
        //DTO 반환
        return responseCommentDTO;
    }

    //댓글 전체 조회
    public List<ResponseCommentDTO> readAll(){

        //Comment(DAO) 전체(List)를 찾음 -> commentList
        List<Comment> commentList = getCommentDAOsBean.exec();

        //DTOList 생성
        List<ResponseCommentDTO> responseCommentDTOList = new ArrayList<>();

        //commentList의 DAO를 DTO로 변환해 DTOList에 추가
        for(Comment comment: commentList){
            ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();

            responseCommentDTO.setCommentId(comment.getCommentId());
            responseCommentDTO.setUserId(comment.getUserId());
            responseCommentDTO.setContent(comment.getContent());
            responseCommentDTO.setHeartCount(comment.getHeartCount());
            responseCommentDTO.setUploadTime(comment.getUploadTime());

            responseCommentDTOList.add(responseCommentDTO);
        }

        //생성한 responseCommentDTOList 반환
        return responseCommentDTOList;
    }

    //댓글 전체 조회 - 최신순
    public List<ResponseCommentByUserDTO> readAllByTime(UUID userId){

        //Comment(DAO) 전체(List)를 시간순으로 정렬해서 찾음 -> commentList
        List<Comment> commentList = getCommentDAOsBean.execByTime();

        //유저아이디에 따른 DTOList 생성(해당 사용자가 하트를 눌렀는지도 같이 반환해줄 것)
        List<ResponseCommentByUserDTO> responseCommentByUserDTOList = new ArrayList<>();

        //commentList의 DAO를 DTO로 변환해 DTOList에 추가
        for(Comment comment: commentList){
            ResponseCommentByUserDTO responseCommentDTO = new ResponseCommentByUserDTO();

            responseCommentDTO.setCommentId(comment.getCommentId());
            responseCommentDTO.setUserId(comment.getUserId());
            responseCommentDTO.setContent(comment.getContent());
            responseCommentDTO.setHeartCount(comment.getHeartCount());
            responseCommentDTO.setUploadTime(comment.getUploadTime());

            //해당 댓글에 사용자가 하트를 눌렀는지 여부도 저장
            Boolean userHeartExists = getBooleanUserHeartExistsBean.checkUserHeartExists(comment.getCommentId(), userId);
            responseCommentDTO.setUserHeartExists(userHeartExists);

            responseCommentByUserDTOList.add(responseCommentDTO);
        }

        //생성한 responseCommentByUserDTOS 반환
        return responseCommentByUserDTOList;

    }

    //댓글 전체 조회 - 추천순
    public List<ResponseCommentByUserDTO> readAllByHeart(UUID userId){

        //Comment(DAO) 전체(List)를 하트순으로 정렬해서 찾음 -> commentList
        List<Comment> commentList = getCommentDAOsBean.execByHeart();

        //유저아이디에 따른 DTOList 생성(해당 사용자가 하트를 눌렀는지도 같이 반환해줄 것)
        List<ResponseCommentByUserDTO> responseCommentByUserDTOList = new ArrayList<>();

        //commentList의 DAO를 DTO로 변환해 DTOList에 추가
        for(Comment comment: commentList){
            ResponseCommentByUserDTO responseCommentDTO = new ResponseCommentByUserDTO();

            responseCommentDTO.setCommentId(comment.getCommentId());
            responseCommentDTO.setUserId(comment.getUserId());
            responseCommentDTO.setContent(comment.getContent());
            responseCommentDTO.setHeartCount(comment.getHeartCount());
            responseCommentDTO.setUploadTime(comment.getUploadTime());

            //해당 댓글에 사용자가 하트를 눌렀는지 여부도 저장
            Boolean userHeartExists = getBooleanUserHeartExistsBean.checkUserHeartExists(comment.getCommentId(), userId);
            responseCommentDTO.setUserHeartExists(userHeartExists);

            responseCommentByUserDTOList.add(responseCommentDTO);
        }

        //생성한 responseCommentByUserDTOS 반환
        return responseCommentByUserDTOList;

    }

    //댓글 저장
    public ResponseSuccess insert(RequestCommentSaveDTO requestCommentSaveDTO){

        //DAO를 생성해서 [PK, 하트개수, 업로드시간] 초기화
        Comment comment = new Comment();

        comment.setCommentId(UUID.randomUUID());
        comment.setHeartCount(0);
        comment.setUploadTime(LocalDateTime.now());

        //DAO에 DTO의 값(userId, Content) 넣어주기
        comment.setUserId(requestCommentSaveDTO.getUserId());


        comment.setContent(requestCommentSaveDTO.getContent());

        //DAO 저장
        return saveCommentDAOBean.exec(comment);

    }

    //댓글 수정
    public ResponseSuccess update(RequestCommentUpdateDTO requestCommentUpdateDTO){

        //DTO의 commentId로 댓글(DAO) 찾기
        Comment comment = getCommentDAOBean.exec(requestCommentUpdateDTO.getCommentId());

        //찾은 댓글의 내용 수정 후 저장
        return updateCommentDAOBean.exec(comment, requestCommentUpdateDTO);

    }
    
    //댓글 삭제
    public ResponseSuccess delete(RequestCommentDeleteDTO requestCommentDeleteDTO){

        //id를 통해 댓글 찾기(DAO)
        Comment comment = getCommentDAOBean.exec(requestCommentDeleteDTO.getCommentId());

        //찾은 DAO 삭제
        return deleteCommentDAOBean.exec(comment, requestCommentDeleteDTO.getUserId());

    }
}
