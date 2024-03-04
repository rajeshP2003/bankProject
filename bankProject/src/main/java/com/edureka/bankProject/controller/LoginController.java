    package com.edureka.bankProject.controller;

    import com.edureka.bankProject.model.User;
    import com.edureka.bankProject.repo.UserRepo;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.ui.ModelMap;
    import org.springframework.web.bind.annotation.*;

    import java.util.Optional;

    @Controller
    public class LoginController {

        @Autowired
        private UserRepo userRepo;


        @RequestMapping(value = "/home2", method = RequestMethod.GET)
        public String getHome(Model model) {
            return "home";
        }

        @GetMapping("/login")
        public String getLogin(Model model) {
            // Return the name of the JSP view that renders the login page
            return "login";
        }

        @PostMapping("/login")
        public ResponseEntity<String> checkLogin(@RequestBody User user){

            String userName = user.getUserName();

            Optional<User> newUser = userRepo.findByUserName(userName);

            if(newUser.isPresent()){
                User storedUser = newUser.get();
                if(user.getPassword().equals(storedUser.getPassword())){
//                    return ResponseEntity.status(HttpStatus.CREATED).body("User Logged In Successfully");
                    return new ResponseEntity<>("User logged in succesfully",HttpStatus.OK);
                }else {
//                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password incorrect");
                    return new ResponseEntity<>("Incorrect Password",HttpStatus.UNAUTHORIZED);

                }
            }else {
//                return new ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");            }
                return new ResponseEntity<>("User not Found",HttpStatus.NOT_FOUND);

            }
    }
    }
