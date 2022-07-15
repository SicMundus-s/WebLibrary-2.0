package Web.contollers;

import Web.dao.PersonDAO;
import Web.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller // Помечает что данный класс является контроллером. Внутри содержит @Component, которая в свою очередь является бином.
@RequestMapping("/people") // Добавляет каждой ссылке people(Очень грубо)
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /**
     * Передаёт список всех людей. Вывод всех людей в представление с помощью th:each
     */
    @GetMapping() // Действует как ярлык для RequestMapping. Помечает метод как GET HTTP REQUEST
    public String index(Model model) {
        // Model добавляет атрибуты в модель
        model.addAttribute("peopleList", personDAO.index());
        return "people/index";
    }

    /**
     * show Передаёт одного человека. Вывод человека представление.
     * getBooksByPersonId возвращает список книг прикреплённых за человеком.
     */
    @GetMapping("/{id}") // Не работает show когда мы создаём нового человека
    // @PathVariable Извлекает значение из URL запроса
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("booksPerson", personDAO.getBooksByPersonId(id));
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

    personDAO.save(person);
        return "redirect:/people";

    }

    /**
     * Возвращает представление на обновление человека.
     */
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
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
        personDAO.update(id, person);
        return "redirect:/people";
    }

    /**
     * Метод удаления человека
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
