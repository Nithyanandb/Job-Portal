package JobPortal.main.Services;

import JobPortal.main.Entity.UsersType;
import JobPortal.main.Repository.UsersTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeService {

    private final UsersTypeRepository usersTypeRepository;

    public UsersTypeService(UsersTypeRepository usersTypeRepository) {
        this.usersTypeRepository = usersTypeRepository;
    }

    public List<UsersType> getAll()
    {
        return usersTypeRepository.findAll();
    }



}
