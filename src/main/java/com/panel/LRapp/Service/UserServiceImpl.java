package com.panel.LRapp.Service;

import com.panel.LRapp.Dto.UserDTO;
import com.panel.LRapp.Dto.loginDTO;
import com.panel.LRapp.Entity.ImageData;
import com.panel.LRapp.Entity.User;
import com.panel.LRapp.Entity.code;
import com.panel.LRapp.Repo.UserRepo;
import com.panel.LRapp.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StorageService storageService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Override
    public LoginResponse addUser(UserDTO userDTO) {
        User user =new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getPhone()
        );
        code c=new code();
        c.setCode("1");
        user.setCo(c);
        ImageData imageData=new ImageData();
        imageData.setName("initial image");
        imageData.setType("png");
        imageData.setImageData(null);
        user.setImageData(imageData);

        if(! (userDTO.getPassword().equals(userDTO.getConfirmPass())))
            return new LoginResponse("Password and Confirm Password must be Same :(", false,null);
        if(!(userDTO.getPhone().length()==11))
            return new LoginResponse("Phone must be 11 number :(", false,null);
        if(! (userDTO.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
            return new LoginResponse("Email not Valid :(", false,null);
        if(! (userRepo.findByEmail(userDTO.getEmail())==null))
            return new LoginResponse("Email Existed , please Sign in :)", false,null);

        return new LoginResponse("Registered Successfully :)", true,userRepo.save(user));
    }
    UserDTO userDTO;
    @Override
    public LoginResponse loginUser(loginDTO login) {

            String msg = "";
        if(! (login.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
            return new LoginResponse("Email not Valid :(", false,null);
            User user = userRepo.findByEmail(login.getEmail());
            if (user != null) {
                String password = login.getPassword();

                if (user.getPassword().equals(login.getPassword())) {
                    Optional<User> user1 = userRepo.findOneByEmailAndPassword(login.getEmail(), login.getPassword());
                    if (user1.isPresent()) {
                        return new LoginResponse("Login Success", true,user);
                    } else {
                        return new LoginResponse("Login Failed", false,null);
                    }
                } else {
                    return new LoginResponse("password Not Match", false,null);
                }
            }else {
                return new LoginResponse("Email not exits", false,null);
            }

    }
    @Override
    public void deleteById(int id){
        userRepo.deleteById(id);
    }

    @Override
    public LoginResponse findById(int id) {
        if(id<=0){
            return new LoginResponse("not found user with id <=0 :(", false,null);
        }
        if(userRepo.findById(id).isEmpty()){
            return new LoginResponse("not found user with id : "+id, false,null);
        }

        return new LoginResponse("user found", true,userRepo.findById(id).get());
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
    @Override
    public LoginResponse update(String name, String email, String pass, String phone) {

        if(! (email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
            return new LoginResponse("Email not Valid :(", false,null);
        if(userRepo.findByEmail(email)==null)
            return new LoginResponse("Email Not Existed ", false,null);
        if(!(phone.length()==11))
            return new LoginResponse("Phone must be 11 number :(", false,null);
        // Retrieve the entity object
        Optional<User> optionalUser = Optional.ofNullable(userRepo.findByEmail(email));

        if (optionalUser.isPresent()) {
            // Modify the fields of the entity object
            User user = optionalUser.get();
            user.setName(name);
            user.setPassword(pass);
            user.setPhone(phone);
            // Save the entity
            userRepo.save(user);
            return new LoginResponse("profile updated",true,user);
        }
        return null;
    }
    @Override
    public User checkEmailExist(String email){
        return userRepo.findByEmail(email);}

    @Override
    public void save(User user){
        userRepo.save(user);
    }
    @Override
    public User findByMail(String email){
      return userRepo.findByEmail(email);
    }


     }
