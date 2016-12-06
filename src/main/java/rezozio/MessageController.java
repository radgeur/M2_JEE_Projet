package rezozio;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class MessageController {

    @Autowired
    private MessageRepository mr;

    @RequestMapping(value = "/")
    public String index(Model model)
    {
        for(Message m : this.mr.findAll()){
          model.addAttribute("message", m);
        }
        return "index.html";
    }
}
