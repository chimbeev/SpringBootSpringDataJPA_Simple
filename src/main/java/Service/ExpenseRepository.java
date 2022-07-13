package Service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//As you can see, this interface extends the CrudRepository interface which defines standard CRUD operations.
// In the generic parameters, we specify the domain type to work with is Expense and the type of domain's ID is Long.
//Spring Data предоставляет абстракцию CrudRepository, которая типизируется целевой сущностью и её id.
// CrudRepository имеет набор базовых методов для работы с сущностью, названия которых говорят сами за себя:
//        Нам достаточно расширить этот интерфейс, добавить сигнатуры необходимых нам методов и Spring автоматически создаст реализацию этого интерфейса!
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    public List<Expense> findByItem(String item);

    @Query("SELECT e FROM Expense e WHERE e.amount >= :amount")
    //This method will return expense items whose amount greater than a specified value.
    public List<Expense> listItemsWithPriceOver(@Param("amount") float amount);
}