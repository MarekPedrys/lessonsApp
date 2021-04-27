package pl.marekpedrys.lessonsbackendspring.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.marekpedrys.lessonsbackendspring.lesson.Lesson;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 4, max = 12)
    private String username;
    @Size(min = 8, max = 2048)
    private String password;
    @NotEmpty
    private String role;
    @Email
    private String email;
    @OneToMany(mappedBy = "teacher")
    private List<Lesson> teachersLessons;
    @OneToMany(mappedBy = "pupil")
    private List<Lesson> pupilsLessons;
    private boolean hasNewOrder;
    @NotEmpty
    private String photo;

    public User() {
    }

    public User(String username, String password, String role, String email, String photo) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.photo = photo;
        this.hasNewOrder = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public List<Lesson> getTeachersLessons() {
        return teachersLessons;
    }

    public List<Lesson> getPupilsLessons() {
        return pupilsLessons;
    }

    public boolean HasNewOrder() {
        return hasNewOrder;
    }

    public String getPhoto() {
        return photo;
    }

    public void setHasNewOrder(boolean hasNewOrder) {
        this.hasNewOrder = hasNewOrder;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
