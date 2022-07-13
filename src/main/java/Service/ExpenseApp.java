package Service;
//Spring Boot - Spring Data JPA application with Hibernate and Postgress

//В этом приложении Spring Boot application используем Spring Data JPA для доступа к реляционной базе данных  - Postgres.
//Spring Data JPA мощно упрощает путь программиста для написания кода для уровня доступа к данным,
//Это делается с помощью написания интерфейса repository interfaces как расширение CrudRepository/JpaRepository.
//И Spring Boot делает это легче чем раньше устанавливая настройки Spring Data JPA and Hibernate зависимостей, entity manager factory,
//  transaction manager, annotations.
//Так что  Spring Boot помогает нам написать код для доступа к бд быстро и минимумом настроек.
//В файле application.properties прописывается путь до БД:
//spring.jpa.hibernate.ddl-auto=none
//        spring.datasource.url=jdbc:postgresql://localhost:5432/sales?currentSchema=goods
//        spring.datasource.username=postgres
//        spring.datasource.password=1
//        logging.level.root=WARN
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ExpenseApp implements CommandLineRunner {
    //CommandLineRunner - это функциональный интерфейс Spring Boot, который используется для запуска кода при запуске приложения.
    // Он находится в пакете org.springframework.boot.
    //В процессе запуска после инициализации контекста Spring boot вызывает свой метод run() с аргументами командной строки, предоставленными приложению.
    //Чтобы сообщить Spring Boot о нашем интерфейсе commandlineRunner, мы можем либо реализовать его и добавить аннотацию @Component над классом,
    // либо создать его bean-компонент с помощью @bean.

    //You see, an instance of ExpenseRespository will be injected to an instance of the ExpenseApp class at runtime:
    @Autowired
    ExpenseRepository repository;

    public static void main(String[] args) {
        //Then in the run() method we can use the repository to list all expenses, get the breakfast item and find items
        // with amount greater than 200 USD.
        SpringApplication.run(ExpenseApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Expense("Завтрак", 5));
        repository.save(new Expense("Кофе", 2));
        repository.save(new Expense("Новый SSD диск", 200));
        repository.save(new Expense("Питание для малыша", 350));
        repository.save(new Expense("Несколько яблок", 5));

        Iterable<Expense> iterator = repository.findAll();

        System.out.println("Все дорогие вещи: ");
        iterator.forEach(item -> System.out.println(item));

        List<Expense> breakfast = repository.findByItem("breakfast");
        System.out.println("\nСколько мой завтрак стоит?: ");
        breakfast.forEach(item -> System.out.println(item));

        List<Expense> expensiveItems = repository.listItemsWithPriceOver(200);
        System.out.println("\nДорогие вещи: ");
        expensiveItems.forEach(item -> System.out.println(item));

    }
}