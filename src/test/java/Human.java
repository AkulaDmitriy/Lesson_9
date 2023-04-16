import java.util.List;

public class Human {
    public String name;
    public Integer age;
    public Boolean isClever;
    public List<String> hobbies;
    public Passport passport;

    public static class Passport {
        public String number;
        public String creationDate;
    }
}
