package br.com.security.exercicio.model.users;

public record UserRequestRegisterDTO(String login, String password, UserRoles role) {
}
