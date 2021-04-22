package pl.marekpedrys.lessonsbackendspring.user;

public class UserDTO {
    private String username;
    private String role;
    private Boolean hasNewOrder;
    private String photo;

    public UserDTO(String username, String role, Boolean hasNewOrder, String photo) {
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
}
