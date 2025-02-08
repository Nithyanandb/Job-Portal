package JobPortal.main.Services;

import JobPortal.main.Entity.Users;
import JobPortal.main.Repository.UsersRepository;
import JobPortal.main.Util.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Couldn't find the Username"));
        return new CustomUserDetails(user);
    }
}
