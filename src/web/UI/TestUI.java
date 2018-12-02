package web.UI;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/homepage"})
public class TestUI {
	@RequestMapping(method=GET)
	public String index(){
		return "index";
	}
}
