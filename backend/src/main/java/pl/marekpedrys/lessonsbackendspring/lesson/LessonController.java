package pl.marekpedrys.lessonsbackendspring.lesson;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.marekpedrys.lessonsbackendspring.user.User;
import pl.marekpedrys.lessonsbackendspring.user.UserRepository;


import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/lesson")
public class LessonController {
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    User dummyPupil = new User();

    public LessonController(LessonRepository lessonRepository, UserRepository userRepository) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<LessonDTO> getAvailableLessons() {
        List<Lesson> lessons = lessonRepository.findAvailableLessons(LocalDate.now(), LocalTime.now());

        lessons.stream()
                .forEach(lesson -> {
                    if (lesson.getPupil() == null) {
                        lesson.setPupil(dummyPupil);
                    }
                });

        return lessons.stream()
                .map(lesson -> new LessonDTO(
                        lesson.getId(),
                        lesson.getSubject(),
                        lesson.getDate(),
                        lesson.getTime(),
                        lesson.getDuration(),
                        lesson.getPrice(),
                        lesson.getTeacher().getUsername(),
                        lesson.getTeacher().getEmail(),
                        lesson.getTeacher().getPhoto(),
                        lesson.getPupil().getUsername(),
                        lesson.getPupil().getEmail(),
                        lesson.getPupil().getPhoto()))
                .collect(Collectors.toList());
    }

    @GetMapping("/byTeacher")
    @Secured("ROLE_TEACHER")
    public List<LessonDTO> getTeachersLessons() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = authentication.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User loggedTeacher = optionalUser.get();

        List<Lesson> lessons = lessonRepository.findLessonsByTeacher(loggedTeacher);

        lessons.stream()
                .forEach(lesson -> {
                    if (lesson.getPupil() == null) {
                        lesson.setPupil(dummyPupil);
                    }
                });

        return lessons.stream()
                .map(lesson -> new LessonDTO(
                        lesson.getId(),
                        lesson.getSubject(),
                        lesson.getDate(),
                        lesson.getTime(),
                        lesson.getDuration(),
                        lesson.getPrice(),
                        lesson.getTeacher().getUsername(),
                        lesson.getTeacher().getEmail(),
                        lesson.getTeacher().getPhoto(),
                        lesson.getPupil().getUsername(),
                        lesson.getPupil().getEmail(),
                        lesson.getPupil().getPhoto()))
                .collect(Collectors.toList());
    }

    @GetMapping("/byPupil")
    @Secured("ROLE_PUPIL")
    public List<LessonDTO> getPupilsLessons() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = authentication.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User loggedPupil = optionalUser.get();

        List<Lesson> lessons = lessonRepository.findLessonsByPupil(loggedPupil);

        return lessons.stream()
                .map(lesson -> new LessonDTO(
                        lesson.getId(),
                        lesson.getSubject(),
                        lesson.getDate(),
                        lesson.getTime(),
                        lesson.getDuration(),
                        lesson.getPrice(),
                        lesson.getTeacher().getUsername(),
                        lesson.getTeacher().getEmail(),
                        lesson.getTeacher().getPhoto(),
                        lesson.getPupil().getUsername(),
                        lesson.getPupil().getEmail(),
                        lesson.getPupil().getPhoto()))
                .collect(Collectors.toList());
    }


    @PostMapping()
    @Secured("ROLE_TEACHER")
    public void addLesson(@Valid @RequestBody Lesson lesson) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = authentication.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User loggedTeacher = optionalUser.get();
        lesson.setTeacher(loggedTeacher);
        lessonRepository.save(lesson);
    }

    @PostMapping("/{lessonId}")
    @Secured("ROLE_PUPIL")
    public void orderLesson(@PathVariable long lessonId) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = authentication.getName();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User loggedPupil = optionalUser.get();
        Lesson lesson = lessonRepository.findById(lessonId).get();
        lesson.setPupil(loggedPupil);
        lessonRepository.save(lesson);

        User teacher = lesson.getTeacher();
        teacher.setHasNewOrder(true);
        userRepository.save(teacher);
    }

}
