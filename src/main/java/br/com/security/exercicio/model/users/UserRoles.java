package br.com.security.exercicio.model.users;

public enum UserRoles {

    ADMIN("admin"),
    USER ("user");

    private String role;

    UserRoles(String role){
        this.role = role;
    }
}
