package model;


public class Member {
    private int id;
    private String name;
    private String email;
    private String passwordHash;
    private Role role;

    public Member(String name, String email, String passwordHash, Role role) {
        validateDetails(name, email, passwordHash, role);

        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;

    }


    public Member(int id, String name, String email, String passwordHash, Role role) {
        validateDetails(name, email, passwordHash, role);

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;

    }

    private void validateDetails(String name, String email, String passwordHash, Role role) {
    if (name == null || name.isBlank()) {
        throw new IllegalArgumentException("Name cannot be empty");
    }

    if (email == null || !email.contains("@")) {
        throw new IllegalArgumentException("Invalid email");
    }

    if (role == null) {
        throw new IllegalArgumentException("Role cannot be null");
    }

    if (passwordHash == null || passwordHash.isBlank()) {
    throw new IllegalArgumentException("Password hash cannot be empty");
    }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name !=null && !name.isBlank()) {
            this.name = name;
        }
        
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }


    public Role getRole () {
        return role;
    }
    public void setRole(Role role) {
        if(role != null) {
            this.role = role;
        }
        
    }


}
