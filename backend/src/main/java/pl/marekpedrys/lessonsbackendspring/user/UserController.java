package pl.marekpedrys.lessonsbackendspring.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.marekpedrys.lessonsbackendspring.lesson.Lesson;
import pl.marekpedrys.lessonsbackendspring.lesson.LessonDTO;
import pl.marekpedrys.lessonsbackendspring.lesson.LessonRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final PasswordEncoder passwordEncoder;
    User dummyPupil = new User();

    public UserController(LessonRepository lessonRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public void create(@Valid @RequestBody User user) {
        String newPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @GetMapping("/login")
    public UserDTO login() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = authentication.getName();

        String role = authentication.getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow()
                .getAuthority();

        User user = (User) securityContext.getAuthentication().getPrincipal();
        Long id = user.getId();
        boolean hasNewOrder = user.HasNewOrder();
        String photo = user.getPhoto();

        return new UserDTO(id, username, role, hasNewOrder, photo);
    }

    @PatchMapping("/{userId}")
    public void updateTeacherHasNewOrder(@PathVariable Long userId, @RequestBody Boolean requestHasNewOrder) {
        User storedTeacher = userRepository.findById(userId).get();
        storedTeacher.setHasNewOrder(requestHasNewOrder);
        userRepository.save(storedTeacher);
    }

    @GetMapping("/{userId}/lessons")
    public List<LessonDTO> getUsersLessons(@PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        String role = user.getRole();

        if (role.equals("TEACHER")) {
            List<Lesson> lessons = lessonRepository.findLessonsByTeacher(user);

            lessons.stream()
                    .forEach(lesson -> {
                        if (lesson.getPupil() == null) {
                            lesson.setPupil(dummyPupil);
                        }
                    });

            return lessonsToDTO(lessons);
        } else {
            List<Lesson> lessons = lessonRepository.findLessonsByPupil(user);

            lessons.stream()
                    .forEach(lesson -> {
                        if (lesson.getPupil() == null) {
                            lesson.setPupil(dummyPupil);
                        }
                    });

            return lessonsToDTO(lessons);
        }


    }

    private List<LessonDTO> lessonsToDTO(List<Lesson> lessons) {
        return lessons.stream()
                .map(lesson -> new LessonDTO(
                        lesson.getId(),
                        lesson.getSubject(),
                        lesson.getDate(),
                        lesson.getTime(),
                        lesson.getDuration(),
                        lesson.getPrice(),
                        lesson.getTeacher().getId(),
                        lesson.getTeacher().getUsername(),
                        lesson.getTeacher().getEmail(),
                        lesson.getTeacher().getPhoto(),
                        lesson.getPupil().getUsername(),
                        lesson.getPupil().getEmail(),
                        lesson.getPupil().getPhoto()))
                .collect(Collectors.toList());
    }


}
