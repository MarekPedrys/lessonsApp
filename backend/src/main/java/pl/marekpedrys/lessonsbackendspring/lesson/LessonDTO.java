package pl.marekpedrys.lessonsbackendspring.lesson;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class LessonDTO {
    private Long id;
    private String subject;
    private LocalDate date;
    private String time;
    private int duration;
    private BigDecimal price;
    private String teacher;
    private String teacherEmail;
    private String teacherPhoto;
    private String pupil;
    private String pupilEmail;
    private String pupilPhoto;


    public LessonDTO(Long id, String subject, LocalDate date, LocalTime time, int duration, BigDecimal price, String teacher, String teacherEmail, String teacherPhoto, String pupil, String pupilEmail, String pupilPhoto) {
        this.id = id;
        this.subject = subject;
        this.date = date;
        this.time = time.toString().substring(0,5);
        this.duration = duration;
        this.price = price;
        this.teacher = teacher;
        this.teacherEmail = teacherEmail;
        this.teacherPhoto = teacherPhoto;
        this.pupil = pupil;
        this.pupilEmail = pupilEmail;
        this.pupilPhoto = pupilPhoto;
    }

    public LessonDTO() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getPupil() {
        return pupil;
    }

    public void setPupil(String pupil) {
        this.pupil = pupil;
    }

    public String getPupilEmail() {
        return pupilEmail;
    }

    public void setPupilEmail(String pupilEmail) {
        this.pupilEmail = pupilEmail;
    }

    public String getTeacherPhoto() {
        return teacherPhoto;
    }

    public void setTeacherPhoto(String teacherPhoto) {
        this.teacherPhoto = teacherPhoto;
    }

    public String getPupilPhoto() {
        return pupilPhoto;
    }

    public void setPupilPhoto(String pupilPhoto) {
        this.pupilPhoto = pupilPhoto;
    }
}
