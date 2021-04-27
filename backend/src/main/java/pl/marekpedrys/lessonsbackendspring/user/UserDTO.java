package pl.marekpedrys.lessonsbackendspring.user;

public class UserDTO {
    private Long id;
    private String username;
    private String role;
    private Boolean hasNewOrder;
    private String photo;

    public UserDTO(Long id, String username, String role, Boolean hasNewOrder, String photo) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.hasNewOrder = hasNewOrder;
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public Boolean getHasNewOrder() {
        return hasNewOrder;
    }

    public String getPhoto() {
        return photo;
    }

    public Long getId() { return id; }
}
