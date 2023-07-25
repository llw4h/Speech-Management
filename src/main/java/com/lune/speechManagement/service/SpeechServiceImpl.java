package com.lune.speechManagement.service;

import com.lune.speechManagement.repository.SpeechRepository;
import com.lune.speechManagement.model.Speech;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SpeechServiceImpl implements SpeechService {
    @Autowired private SpeechRepository speechRepo;

    @Override public List<Speech> getAllSpeech() {
        return speechRepo.findAll();
    }
    @Override public void save(Speech speech) {
        speechRepo.save(speech);
    }
    @Override public Speech getById(Integer id) {
        Optional<Speech> optional = speechRepo.findById(id);
        Speech speech = null;
        if (optional.isPresent()) {
            speech = optional.get();
        } else {
            throw new RuntimeException("Speech not found for id : " + id);
        }
        return speech;
    }
    @Override public void deleteViaId(Integer id) {
        speechRepo.deleteById(id);
    }

    @Override public List<Speech> getByKeywordAndDateRange(String keyword, LocalDate startdate, LocalDate enddate){
        return speechRepo.findByKeywordAndDateRange(keyword, startdate, enddate);
    }
    @Override public List<Speech> getByKeyword(String keyword){
        return speechRepo.findByKeyword(keyword);
    }
}
