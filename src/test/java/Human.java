import java.util.List;

//{
//        "name": "Dima",
//        "age": "37",
//        "isClever": "true",
//        "hobbies": ["Reading","Running"],
//        "passport": {
//        "number": 555555,
//        "creationDate": "15 DEC 2022"
//        }
//        }
public class Human {
    public String name;
    public Integer age;
    public Boolean isClever;
    public List<String> hobbies;
    public Passport passport;

    public static class Passport {
        public Integer number;
        public String creationDate;
    }
}
