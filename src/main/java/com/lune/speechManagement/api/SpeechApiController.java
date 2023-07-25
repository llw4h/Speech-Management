package com.lune.speechManagement.api;

import com.lune.speechManagement.model.Speech;
import com.lune.speechManagement.service.SpeechServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/speeches")
public class SpeechApiController {
    @Autowired
    private SpeechServiceImpl speechServiceImpl;

    //viewing all speech
    @GetMapping
    public List<Speech> getAllSpeeches() {
        return speechServiceImpl.getAllSpeech();
    }
    record NewSpeechRequest (
            LocalDate speechDate,
            String author,
            String subject,
            String speechText
    ){
    }

    // add speech
    @PostMapping("/addSpeech")
    public void addSpeech(@RequestBody NewSpeechRequest request) {
        Speech speech = new Speech();
        speech.setSpeechDate(request.speechDate());
        speech.setAuthor(request.author());
        speech.setSubject(request.subject());
        speech.setSpeechText(request.speechText());
        speechServiceImpl.save(speech);
    }

    // deleting
    @DeleteMapping("{speechId}")
    public void deleteSpeech(@PathVariable("speechId") Integer id) {
        speechServiceImpl.deleteViaId(id);
    }

    // update
    @PutMapping("/speech/{id}")
    public void updateSpeech(@PathVariable(value = "id") Integer id, @RequestBody Speech speech) {
        speech.setId(id);
        speechServiceImpl.save(speech);
    }

    // search by keyword
    @GetMapping("/search/keyword={keyword}")
    public List<Speech> searchByKeyword( @PathVariable(value = "keyword") String keyword) {
        return speechServiceImpl.getByKeyword(keyword);
    }

    /** Required parameter 'startdate' is not present.",
    @GetMapping("/keyword={keyword}&startdate={startdate}&enddate={enddate}")
    public List<Speech> searchByKeywordAndDateRange( @PathVariable(value = "keyword") String keyword,
                                                     @RequestParam(value = "startdate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startdate,
                                                     @RequestParam(value = "enddate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate enddate) {
        return speechServiceImpl.getByKeywordAndDateRange(keyword,startdate,enddate);
    }
     **/
}
