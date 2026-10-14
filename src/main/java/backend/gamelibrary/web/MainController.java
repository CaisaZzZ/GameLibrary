package backend.gamelibrary.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    public MainController(){
    }

    @GetMapping("/frontpage")
    public String mainController() {
        return "frontpage"; //frontpage.html
    }

    //Game Library View html
    @GetMapping("/library")
    public String libraryController(){
        return "library"; //library.html
    }

}
