package com.justgo.admin.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auth jp
 * @Date 2019/3/4
 */
@Table(name="QUESTION_ANSWER")
public class QuestionAnswer {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "QUESTIONER")
    private String questioner;

    @Column(name = "QUESTION_TIME")
    private Date questionTime;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "ANSWERER")
    private String answerer;

    @Column(name = "ANSWER_TIME")
    private Date answerTime;

    @Column(name = "FILE")
    private byte[] file;

    @Column(name = "FILE_NAME")
    private String fileName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestioner() {
        return questioner;
    }

    public void setQuestioner(String questioner) {
        this.questioner = questioner;
    }

    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerer() {
        return answerer;
    }

    public void setAnswerer(String answerer) {
        this.answerer = answerer;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getQuestionTimeString() {
        if (questionTime == null){
            return "";
        }else{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(questionTime);
        }
    }

    public String getAnswerTimeString() {
        if (answerTime == null){
            return "";
        }else{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(answerTime);
        }
    }

}
