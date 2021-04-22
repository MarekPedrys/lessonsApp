package pl.marekpedrys.lessonsbackendspring.user;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public void create(@Valid @RequestBody User user) {
        String newPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @PostMapping("/login")
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

        boolean hasNewOrder = user.HasNewOrder();
        String photo = user.getPhoto();

        return new UserDTO(username, role, hasNewOrder, photo);
    }

    @PostMapping("/hideNewOrderBadge")
    @Secured("ROLE_TEACHER")
    public void newOrder(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User teacher = (User) securityContext.getAuthentication().getPrincipal();
        teacher.setHasNewOrder(false);
        userRepository.save(teacher);
    }


    //    //------------- just for testing: -------------------------
////
//    @GetMapping("/secured-content")
//    public List<String > exampleSecuredContent() {
//        return Arrays.asList("AAA","B","CcC");
//    }
//
//    @Secured("ROLE_TEACHER")
//    @GetMapping("/teacher")
//    public String example1() {
//        return "You're a teacher.";
//    }
//
//    @Secured("ROLE_PUPIL")
//    @GetMapping("/pupil")
//    public String example2() {
//        return "You're a pupil.";
//    }
//
//    @GetMapping("/user-details1")
//    public String ud1(Principal principal) {
////        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
//        return principal.getName();
//    }
//
//    @GetMapping("/user-details2")
//    public String ud2() {
//        SecurityContext context = SecurityContextHolder.getContext();
////        User user = (User) context.getAuthentication().getPrincipal();
//        return context.getAuthentication()
//                .getName();
//    }
}
