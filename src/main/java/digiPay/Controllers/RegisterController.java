package digiPay.Controllers;


import digiPay.Exceptions.ValidationException;
import digiPay.Models.UserInfo;
import digiPay.Repositories.UserInfoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {


    final private UserInfoRepository userInfoRepository;

    public RegisterController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }


    @PostMapping("/register")
    public Boolean create(@RequestBody Map<String, String> body) throws ValidationException {
        String username = body.get("username");
        if (userInfoRepository.existsByUsername(username)) {
            throw new ValidationException("UserName already existed");
        }

        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        userInfoRepository.save(
                new UserInfo(
                        body.get("firstName"),
                        body.get("lastName"),
                        username,
                        encodedPassword
                )
        );

        return true;
    }
}
