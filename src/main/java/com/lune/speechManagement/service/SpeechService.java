package com.lune.speechManagement.service;

import com.lune.speechManagement.model.Speech;

import java.time.LocalDate;
import java.util.List;

public interface SpeechService {
    List<Speech> getAllSpeech();
    void save(Speech speech);
    Speech getById(Integer id);
    void deleteViaId(Integer id);
    List<Speech> getByKeywordAndDateRange(String keyword, LocalDate startdate, LocalDate enddate);
    List<Speech> getByKeyword(String keyword);
}
