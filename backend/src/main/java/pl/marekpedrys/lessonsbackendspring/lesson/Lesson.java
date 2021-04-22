package pl.marekpedrys.lessonsbackendspring.lesson;

import pl.marekpedrys.lessonsbackendspring.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String subject;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;
    @NotNull
    private int duration;
    @NotNull
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;
    @ManyToOne
    @JoinColumn(name = "pupil_id")
    private User pupil;

    public Lesson() {
    }

    public Lesson(String subject, LocalDate date, LocalTime time, int duration, BigDecimal price, User teacher, User pupil) {
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.teacher = teacher;
        this.pupil = pupil;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public User getPupil() {
        return pupil;
    }

    public void setPupil(User pupil) {
        this.pupil = pupil;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
