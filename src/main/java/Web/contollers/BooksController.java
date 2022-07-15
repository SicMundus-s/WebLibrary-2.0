package Web.contollers;

import Web.dao.BookDAO;
import Web.dao.PersonDAO;
import Web.models.Book;
import Web.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO; // Внедрение одной сущности в другую является ошибкой. Внедряю только ради эмпирического опыта


    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("bookList", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        // @ModelAtt Создаёт пустого человека и передаёт его в Show.html для назначения из выпадающего списка
        model.addAttribute("book", bookDAO.show(id));


        if(bookDAO.getBooksOwner(id).isPresent()) {
            model.addAttribute("ownerBook", bookDAO.getBooksOwner(id).get()); // Get возвращает объект из optional
        } else
            model.addAttribute("peopleListToAssignBook", personDAO.index()); // Возвращаем список людей для Выпадающего списка
        return "books/show";
    }


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

       // bookValidator.validate(book, bindingResult); // Проверка на одинаковые значения по полю title

        if(bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/bookAway")
    public String bookAway(@PathVariable("id") int id) {
        bookDAO.giveTheBookAway(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assignBook")
    // Тут @ModelAtt создаёт пустого человека и принимает выбранного(по id) из выпадающего списка в html(show) файле
    // Тем самым мы получаем id выбранного человека из HTTP запроса и назначаем ему книгу
    public String assignBook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDAO.assign(id, person);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {

       // bookValidator.validate(book, bindingResult); // Проверка на одинаковые значения по полю title

        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.updateBook(id, book);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
