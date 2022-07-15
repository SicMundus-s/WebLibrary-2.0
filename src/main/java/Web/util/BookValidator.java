//package Web.util;
//
//import Web.dao.BookDAO;
//import Web.models.Book;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//@Component
//public class BookValidator implements Validator {
//
//    private final BookDAO bookDAO;
//
//    @Autowired
//    public BookValidator(BookDAO bookDAO) {
//        this.bookDAO = bookDAO;
//    }
//
//    /**
//     * Метод Supports даёт понять спрингу к какой сущности данный валидатор принадлежит
//     */
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Book.class.equals(clazz);
//    }
//
//    /**
//     * Валидируем по полю title.
//     * Проверяем есть ли книга с таким же оглавлением и возвращает ошибку если есть.
//     */
//    @Override
//    public void validate(Object target, Errors errors) {
//        Book book = (Book) target;
//        if(bookDAO.show(book.getTitle()).isPresent()) {
//            errors.rejectValue("title", "1234", "This title is already taken");
//        }
//    }
//}
