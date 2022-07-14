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
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

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
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));


        if(bookDAO.getBooksOwner(id).isPresent()) {
            model.addAttribute("ownerBook", bookDAO.getBooksOwner(id).get()); // Get возвращает объект из optional
        } else
            model.addAttribute("noBookOwner", bookDAO.getBooksOwner(id));
        return "books/show";
    }


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
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
}
