package rezozio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MessageController {

    @Autowired
    private MessageRepository mr;

    @RequestMapping("/")
    public String index(Model model)
    {

        model.addAttribute("message", this.mr.findAll());

        return "index";
    }
}
