package it.beije.security;


import it.beije.mgmt.entity.User;
import it.beije.mgmt.repository.UserRepository;
import it.beije.mgmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserAuthenticationService authenticationService;
    
    @Autowired
    private UserRepository userRepository;

    public String register(String username, String password) throws IllegalArgumentException {
        userService.getByUsername(username)
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Username already in use.");
                });

        User user = new User();
        user.setEmail(username);
        user.setPassword(password);
        userRepository.save(user);

        return authenticationService.login(username, password);
    }
}