package ee.learn.webstore.services;

import ee.learn.webstore.entities.UserEE;
import ee.learn.webstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEE user = repository.findByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    //save user to repository and before saving encode that password
    public void createUser(UserEE user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);

    }

}
