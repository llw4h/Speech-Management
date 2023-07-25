package com.lune.speechManagement.repository;

import com.lune.speechManagement.model.Speech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface SpeechRepository extends JpaRepository<Speech, Integer> {

    //custom query for finding a keyword in columns author, subject and speech text
    @Query(value = "select * from speech s where (s.author like %:keyword% or s.subject like %:keyword% or s.speech_text like %:keyword%)", nativeQuery = true)
   List<Speech> findByKeyword(@Param("keyword") String keyword);

    //custom query for finding keywords in columns author, subject, speech text AND date range
    @Query(value = "SELECT * FROM speech s WHERE (s.author LIKE %:keyword% OR s.subject LIKE %:keyword% OR s.speech_text LIKE %:keyword%) AND (s.speech_date BETWEEN :startDate AND :endDate)", nativeQuery = true)
    List<Speech> findByKeywordAndDateRange(@Param("keyword") String keyword, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
