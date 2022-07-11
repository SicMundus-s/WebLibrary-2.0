package Web.contollers;

import Web.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Помечает что данный класс является контроллером. Внутри содержит @Component, которая в свою очередь является бином.
@RequestMapping("/people") // Добавляет каждой ссылке people(Очень грубо)
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping() // Действует как ярлык для RequestMapping. Помечает метод как GET HTTP REQUEST
    public String index(Model model) {
        // Model добавляет атрибуты в модель
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    // @PathVariable Извлекает значение из URL запроса
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }


}
