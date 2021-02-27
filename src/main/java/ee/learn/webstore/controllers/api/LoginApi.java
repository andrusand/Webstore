package ee.learn.webstore.controllers.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/login")
public class LoginApi {

    //creating endpoint to make sure our login is right.
    @GetMapping("/verify")
    String verifyLogin(){
        return "";//blank space as a convention
    }


}
