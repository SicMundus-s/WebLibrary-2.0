package Web.contollers;


import Web.models.Person;
import Web.servies.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller // Помечает что данный класс является контроллером. Внутри содержит @Component, которая в свою очередь является бином.
@RequestMapping("/people") // Добавляет каждой ссылке people(Очень грубо)
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    /**
     * Передаёт список всех людей. Вывод всех людей в представление с помощью th:each
     */
    @GetMapping() // Действует как ярлык для RequestMapping. Помечает метод как GET HTTP REQUEST
    public String index(Model model) {
        // Model добавляет атрибуты в модель
        model.addAttribute("peopleList", peopleService.findAll());
        return "people/index";
    }

    /**
     * show Передаёт одного человека. Вывод человека представление.
     * getBooksByPersonId возвращает список книг прикреплённых за человеком.
     */
    @GetMapping("/{id}") // Не работает show когда мы создаём нового человека
    // @PathVariable извлекает значение из URL запроса
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("booksPerson", peopleService.getBooksByPersonId(id));
        return "people/show";
    }
    /**
     * Возвращает представление на создание нового человека.
     */
    @GetMapping("/new") //
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    /**
     * Создание нового человека.
     */
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) { // ModelAttribute создаёт новый пустой объект класса с помощью PreparedStatement
        if(bindingResult.hasErrors())
           return "people/new";

        peopleService.save(person);
        return "redirect:/people";

    }

    /**
     * Возвращает представление на обновление человека.
     */
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    /**
     * Метод обновления человека
     */
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, @PathVariable("id") int id,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(id, person);
        return "redirect:/people";
    }

    /**
     * Метод удаления человека
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
