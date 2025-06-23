package com.tenco.blog.repository;

import com.tenco.blog.model.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardNativeRepository {

    // JPA의 핵심 인터페이스
    private EntityManager em;

    public BoardNativeRepository(EntityManager em){
        this.em = em;
    }

    @Transactional
    public void save(String title, String content , String username){
        Query query = em.createNativeQuery("insert into board_tb(title,content,username,created_at)" +
                "values (?,?,?,now()) ");
        query.setParameter(1,title);
        query.setParameter(2,content);
        query.setParameter(3,username);

        query.executeUpdate();
    }

    public List<Board> findAll(){
        Query query = em.createNativeQuery("select * from board_tb order by id desc",Board.class);
        
        // query.getResultList() : 여러 행의 결과를 List 객체로 반환
        // query.getSingleResult() ; 단일 결과만 반환 (한 개의 row 데이터만 있을 때)
        List<Board> boardList = query.getResultList();

        return boardList;
        
    }
}
