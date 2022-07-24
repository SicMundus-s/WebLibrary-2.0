package Web.contollers;

import Web.dao.BookDAO;
import Web.dao.PersonDAO;
import Web.models.Book;
import Web.models.Person;
import Web.util.BookValidator;
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
    private final BookValidator bookValidator;


    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }
/**
 * Метод получает список всех людей из DAO(метод index) и передаёт их в представление.
 * Где с помощью th:each проходится по списку.
 */
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("bookList", bookDAO.index());
        return "books/index";
    }

    /**
     * Метод получает одного человека из DAO(метод show) и передаёт его в представление.
     * getBooksOwner проверяет принадлежит ли книга человеку. Возвращает id человека если книги за кем-то прикреплена
     * id передаётся в представление где в дальнейшем выводится владелец книги.
     * Если книга никому не принадлежит, то в представление передаётся список людей,
     * он выводится с помощью выпадающего списка(select/option)
     * Объект person передаётся для получения id(th:value) из выпадающего списка
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));


        if(bookDAO.getBooksOwner(id).isPresent()) {
            model.addAttribute("ownerBook", bookDAO.getBooksOwner(id).get()); // Get возвращает объект из optional
        } else
            model.addAttribute("peopleListToAssignBook", personDAO.index()); // Возвращаем список людей для Выпадающего списка
        return "books/show";
    }


    /**
     * Представление создание книги
     */
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    /**
     * Создание книги
     */
    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

       bookValidator.validate(book, bindingResult); // Проверка на одинаковые значения по полю title

        if(bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    /**
     * Возвращение книги от владельца
     */
    @PatchMapping("/{id}/bookAway")
    public String bookAway(@PathVariable("id") int id) {
        bookDAO.giveTheBookAway(id);
        return "redirect:/books/" + id;
    }

    /**
     * @param id Получает из строки адреса id книги
     * @param person Для получения id из ранее переданного пустого человека в методе book.show
     * @return представление на страницу книги
     * Метод assign. id - айди книги без владельца. person - кому назначить
     */
    @PatchMapping("/{id}/assignBook")
    // Тут @ModelAtt создаёт пустого человека и принимает выбранного(по id) из выпадающего списка в html(show) файле
    // Тем самым мы получаем id выбранного человека из HTTP запроса и назначаем ему книгу
    public String assignBook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDAO.assign(id, person);
        return "redirect:/books/" + id;
    }

    /**
     * Представление на обновление книги
     */
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    /**
     * Обновление книги
     */
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {

       bookValidator.validate(book, bindingResult); // Проверка на одинаковые значения по полю title

        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.updateBook(id, book);
        return "redirect:/books/" + id;
    }
    /**
     * Удаление книги
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
