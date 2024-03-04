package tino.playtino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tino.playtino.domain.*;
import tino.playtino.repository.JpaCommentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    JpaCommentRepository jpaCommentRepository;

    @Autowired
    public CommentService(JpaCommentRepository jpaCommentRepository){
        this.jpaCommentRepository = jpaCommentRepository;
    }

    //댓글 저장
    public UUID insert(RequestCommentSaveDTO requestCommentSaveDTO){


        //DTO를 DAO로 변환하기
        //DAO 생성해서 -> UUID 생성해주고 하트개수와 시간 설정해주기
        Comment comment = new Comment();
        
        comment.setCommentId(UUID.randomUUID());
        comment.setHeartCount(0);
        comment.setUploadTime(LocalDateTime.now());

        //생성한 DAO에 DTO의 값(userId, Content) 넣어주기
        comment.setUserId(requestCommentSaveDTO.getUserId());
        comment.setContent(requestCommentSaveDTO.getContent());

        //JpaComm~~에 DAO 저장
        jpaCommentRepository.save(comment);

        //생성한 DAO의 PK 반환
        return comment.getCommentId();
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

    //댓글 수정
    public UUID update(RequestCommentUpdateDTO requestCommentUpdateDTO){

        //DTO의 commentId로 댓글(DAO) 찾기
        Comment comment = jpaCommentRepository.findById(requestCommentUpdateDTO.getCommentId()).get();

        //DTO와 찾은 DAO의 userId가 일치하는지 확인, false면 return
        if(!(comment.getUserId().equals(requestCommentUpdateDTO.getUserId())))
            return null;

        //DAO의 댓글 내용을 DTO의 댓글 내용으로 수정
        comment.setContent(requestCommentUpdateDTO.getContent());
        // LocalDateTime updateTime = LocalDateTime.now();
        
        //값이 변경된 DAO 저장
        jpaCommentRepository.save(comment);
        
        //저장된 comment의 PK 반환
        return comment.getCommentId();
    }
    
    //댓글 삭제
    public UUID delete(RequestCommentDeleteDTO requestCommentDeleteDTO){

        //id를 통해 객체 찾기(DAO)
        Comment comment = jpaCommentRepository.findById(requestCommentDeleteDTO.getCommentId()).get();

        //찾은 DAO 삭제
        jpaCommentRepository.delete(comment);

        //삭제한 Comment의 PK 반환
        return comment.getCommentId();
    }
}
