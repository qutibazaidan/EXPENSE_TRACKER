import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpensesStorage {
    private final String filePath;
    private final ObjectMapper mapper;

    public ExpensesStorage(String filePath) {
        this.filePath = filePath;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void saveExpenses(List<Expense> expenses)  {
        try{
            mapper.writeValue(new File(filePath), expenses);
        }
        catch(IOException e){
            System.out.println("Error while saving expenses to file "+e.getMessage());        }
    }

    public List<Expense> loadExpenses()  {
        File file = new File(filePath);
        if(!file.exists()){
        return new ArrayList<>();
        }
        try{
            Expense[] expenses = mapper.readValue(file, Expense[].class);
            List<Expense> result = new ArrayList<>();
            for(Expense expense : expenses){
                result.add(expense);
            }
            return result;
        }
        catch(IOException e){
            System.out.println("Error while reading expenses from "+filePath);
            return new ArrayList<>();
        }
    }
}
