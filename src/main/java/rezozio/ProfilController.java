package rezozio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfilController {

    @PostMapping("/logged.html")
    public String connection(@ModelAttribute User user)
    {
        return "index";
    }
}
