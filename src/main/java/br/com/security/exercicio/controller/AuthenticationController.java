package br.com.security.exercicio.controller;

import br.com.security.exercicio.model.users.UserAuthenticationDTO;
import br.com.security.exercicio.model.users.UserRequestRegisterDTO;
import br.com.security.exercicio.model.users.UserResponseDTO;
import br.com.security.exercicio.model.users.Users;
import br.com.security.exercicio.repositories.UsersRepository;
import br.com.security.exercicio.secutity.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserAuthenticationDTO userAuthenticationDTO) {
        var authenticationRequest = new UsernamePasswordAuthenticationToken(userAuthenticationDTO.login(), userAuthenticationDTO.password());
        System.out.println("Authentication Request"+authenticationRequest);

        try{
            var authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
            System.out.println("authentication response: "+authenticationResponse);

            var token = tokenService.generateToken((Users)authenticationResponse.getPrincipal());
            System.out.println(new UserResponseDTO(token));
            return ResponseEntity.ok().body(new UserResponseDTO(token));
        }catch(BadCredentialsException ex){
            System.out.println("Incorrect username or password"+ex);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRequestRegisterDTO userRequestRegisterDTO){
        if(this.usersRepository.findByLogin(userRequestRegisterDTO.login())!=null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRequestRegisterDTO.password());

        Users users = new Users(
                userRequestRegisterDTO.login(),
                encryptedPassword,
                userRequestRegisterDTO.role()
        );
        this.usersRepository.save(users);

        return ResponseEntity.ok().build();
    }
}
