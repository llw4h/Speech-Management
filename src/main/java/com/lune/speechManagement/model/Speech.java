package com.lune.speechManagement.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Speech {
    @Id
    @SequenceGenerator(
            name = "speech_id_sequence",
            sequenceName = "speech_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "speech_id_sequence"
    )
    private Integer id;
    @Column(columnDefinition = "text")
    private String speechText;
    @Column
    private String author;
    @Column
    private String subject;
    @Column(columnDefinition = "date")
    private LocalDate speechDate;

    public Speech(Integer id, String speechText, String author, String subject, LocalDate speechDate) {
        this.id = id;
        this.speechText = speechText;
        this.author = author;
        this.subject = subject;
        this.speechDate = speechDate;
    }

    public Speech() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSpeechText() {
        return speechText;
    }

    public void setSpeechText(String speechText) {
        this.speechText = speechText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getSpeechDate() {
        return speechDate;
    }

    public void setSpeechDate(LocalDate speechDate) {
        this.speechDate = speechDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speech speech = (Speech) o;
        return Objects.equals(id, speech.id) && Objects.equals(speechText, speech.speechText) && Objects.equals(author, speech.author) && Objects.equals(subject, speech.subject) && Objects.equals(speechDate, speech.speechDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, speechText, author, subject, speechDate);
    }

    @Override
    public String toString() {
        return "Speech{" +
                "id=" + id +
                ", speechText='" + speechText + '\'' +
                ", author='" + author + '\'' +
                ", subject='" + subject + '\'' +
                ", speechDate=" + speechDate +
                '}';
    }
}
