package tino.playtino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tino.playtino.Bean.Small.DeleteCommentDAOBean;
import tino.playtino.Bean.Small.SaveCommentDAOBean;
import tino.playtino.Bean.Small.UpdateCommentDAOBean;
import tino.playtino.domain.*;
import tino.playtino.repository.JpaCommentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    JpaCommentRepository jpaCommentRepository;
    SaveCommentDAOBean saveCommentDAOBean;
    UpdateCommentDAOBean updateCommentDAOBean;
    DeleteCommentDAOBean deleteCommentDAOBean;

    @Autowired
    public CommentService(JpaCommentRepository jpaCommentRepository, SaveCommentDAOBean saveCommentDAOBean, UpdateCommentDAOBean updateCommentDAOBean, DeleteCommentDAOBean deleteCommentDAOBean){
        this.jpaCommentRepository = jpaCommentRepository;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.updateCommentDAOBean = updateCommentDAOBean;
        this.deleteCommentDAOBean = deleteCommentDAOBean;
    }

    //댓글 조회
    public ResponseCommentDTO read(UUID commentId){

        //id로 댓글(DAO) 찾아서
        Comment comment = jpaCommentRepository.findById(commentId).get();
        
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
        List<Comment> commentList = jpaCommentRepository.findAll();

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
        Comment comment = jpaCommentRepository.findById(requestCommentUpdateDTO.getCommentId()).get();

        //찾은 댓글의 내용 수정 후 저장
        return updateCommentDAOBean.exec(comment, requestCommentUpdateDTO);

    }
    
    //댓글 삭제
    public ResponseSuccess delete(RequestCommentDeleteDTO requestCommentDeleteDTO){

        //id를 통해 댓글 찾기(DAO)
        Comment comment = jpaCommentRepository.findById(requestCommentDeleteDTO.getCommentId()).get();

        //찾은 DAO 삭제
        return deleteCommentDAOBean.exec(comment, requestCommentDeleteDTO.getUserId());

    }
}
