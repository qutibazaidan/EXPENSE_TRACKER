 import com.fasterxml.jackson.annotation.JsonCreator;
 import com.fasterxml.jackson.annotation.JsonProperty;

 import java.math.BigDecimal;
 import java.time.LocalDate;

 public class Expense {
    private int id;
    private  String description;
    private LocalDate date;
    private BigDecimal amount;


    @JsonCreator
    public Expense(@JsonProperty("id")int id,
                   @JsonProperty("description") String description,
                   @JsonProperty("amount") BigDecimal amount,
                   @JsonProperty("date") LocalDate date
                   )
    {
        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

     public BigDecimal getAmount() {
         return amount;
     }

     public LocalDate getDate() {
         return date;
     }

     public String getDescription() {
         return description;
     }

     public int getId() {
         return id;
     }

     @Override
     public String toString() {
         return "Expence{" +
                 "amount=" + amount +
                 ", id=" + id +
                 ", description='" + description + '\'' +
                 ", date=" + date +
                 '}';
     }

     public void setAmount(BigDecimal amount) {
         this.amount = amount;
     }

     public void setDescription(String description) {
         this.description = description;
     }
 }
