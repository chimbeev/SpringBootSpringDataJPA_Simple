package Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//As you can see, this is a very simple domain model class to map with the table expense in the database.
// The class name and its attribute names are identical to table name and field names makes the mapping simple.
//@Entity говорит о том, что это - класс-сущность
@Entity
public class Expense {
    //@Table позволяет в явном виде задать имя таблицы в БД, но если имя класса совпадает с названием таблицы, то эту аннотацию можно не указывать.
    // @Id указывает на поле, которое будет идентификатором сущности. В таблице это поле должно быть помечено как primary key.
    // @GeneratedValue позволяет задать способ генерации этого идентификатора. В нашем случае мы используем то значение, которое будет присваиваться в БД.
    // Поле name совпадает с названием поля в базе, поэтому явно имя колонки указывать не требуется.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private float amount;

    protected Expense() {
    }

    protected Expense(String item, float amount) {
        this.item = item;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

// getters and setters are hidden for brevity

    @Override
    public String toString() {
        return id + ". " + item + " - " + amount + " USD";
    }
}