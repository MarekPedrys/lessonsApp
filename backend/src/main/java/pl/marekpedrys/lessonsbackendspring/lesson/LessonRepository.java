package pl.marekpedrys.lessonsbackendspring.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.marekpedrys.lessonsbackendspring.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("SELECT l FROM Lesson l WHERE (l.pupil IS NULL AND l.date > :date OR (l.date=:date AND l.time>:time))")
    List<Lesson> findAvailableLessons(@Param("date") LocalDate date, @Param("time") LocalTime time);

    List<Lesson> findLessonsByTeacher(User loggedTeacher);

    List<Lesson> findLessonsByPupil(User loggedPupil);
}
