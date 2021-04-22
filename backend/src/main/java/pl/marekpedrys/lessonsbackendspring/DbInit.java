package pl.marekpedrys.lessonsbackendspring;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.marekpedrys.lessonsbackendspring.lesson.Lesson;
import pl.marekpedrys.lessonsbackendspring.lesson.LessonRepository;
import pl.marekpedrys.lessonsbackendspring.user.User;
import pl.marekpedrys.lessonsbackendspring.user.UserRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DbInit {
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, LessonRepository lessonRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initDatabase() {
        User teacher1 = new User(
                "Teresa",
                passwordEncoder.encode("teresaPass"),
                "TEACHER",
                "teresa@gmail.com",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkapH9AZ_n5Zw4Ff3W_o01cSuRKVhBBhSxWw&usqp=CAU");
        userRepository.save(teacher1);

        User teacher2 = new User(
                "Thomas",
                passwordEncoder.encode("thomasPass"),
                "TEACHER",
                "thomas@gmail.com",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNJ4yaODcjzEnTaEeuHtZgwhTnHYaSjP705RXtqSlTj1D0cHAQGZAuIh9UPxQBZLQ599s&usqp=CAU");
        userRepository.save(teacher2);

        User teacher3 = new User(
                "Taylor",
                passwordEncoder.encode("taylorPass"),
                "TEACHER",
                "taylor@gmail.com",
                "https://www.spiritexchange.com/wp-content/uploads/2019/01/black-teacher.jpg");
        userRepository.save(teacher3);

        User pupil1 = new User(
                "Petra",
                passwordEncoder.encode("petraPass"),
                "PUPIL",
                "petra@gmail.com",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTchnGtP1kmAKkMSxAU0cNwS12dN_cncIzaRw&usqp=CAU");
        userRepository.save(pupil1);

        User pupil2 = new User(
                "Paul",
                passwordEncoder.encode("paulPass"),
                "PUPIL",
                "paul@gmail.com",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRCGg-Ng7sE-hnnEqQ09meSPLPNy_TLDUlSw&usqp=CAU");
        userRepository.save(pupil2);

        User pupil3 = new User(
                "Pippa",
                passwordEncoder.encode("pippaPass"),
                "PUPIL",
                "pippa@gmail.com",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0Is3EA31ON7z2oReLqc9OcHLcAPqUqFvX9g&usqp=CAU");
        userRepository.save(pupil3);

        Lesson lesson1 = new Lesson(
                "Biology",
                LocalDate.of(2021, 9, 22),
                LocalTime.of(16, 30),
                60,
                BigDecimal.valueOf(10),
                teacher1, pupil1);
        lessonRepository.save(lesson1);

        Lesson lesson2 = new Lesson(
                "History",
                LocalDate.of(2021, 3, 19),
                LocalTime.of(18, 00),
                30,
                BigDecimal.valueOf(5),
                teacher2, null);
        lessonRepository.save(lesson2);

        Lesson lesson3 = new Lesson(
                "Math",
                LocalDate.of(2021, 3, 21),
                LocalTime.of(7, 15),
                45,
                BigDecimal.valueOf(10),
                teacher3, null);
        lessonRepository.save(lesson3);

        Lesson lesson4 = new Lesson(
                "Physics",
                LocalDate.of(2021, 11, 28),
                LocalTime.of(7, 30),
                45,
                BigDecimal.valueOf(15),
                teacher3, null);
        lessonRepository.save(lesson4);

        Lesson lesson5 = new Lesson(
                "Math",
                LocalDate.of(2021, 10, 21),
                LocalTime.of(10, 15),
                60,
                BigDecimal.valueOf(20),
                teacher2, null);
        lessonRepository.save(lesson5);

        Lesson lesson6 = new Lesson(
                "Physics",
                LocalDate.of(2021, 6, 21),
                LocalTime.of(14, 15),
                60,
                BigDecimal.valueOf(20),
                teacher1, pupil2);
        lessonRepository.save(lesson6);
    }
}
