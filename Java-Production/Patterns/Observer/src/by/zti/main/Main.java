package by.zti.main;

import by.zti.main.model.Person;
import by.zti.main.model.SimpleStringObserver;
import by.zti.main.scanner.Command;
import by.zti.main.scanner.ConsoleScanner;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    private static ArrayList<Person> persons = new ArrayList<>();
    private static ConsoleScanner scanner = new ConsoleScanner("/", "-");
    private static boolean running = true;
    private static SimpleStringObserver observer = new SimpleStringObserver();

    public static void main(String[] args) {
        populateScanner();
        while (running){
            scanner.scan();
        }
    }

    private static void populateScanner() {
        scanner.addCommand(new Command("exit", "").setConsumer(map -> running = false));
        scanner.addCommand(new Command("add", "").setConsumer(map -> {
            if (!map.containsKey("n")){return;}
            if (!map.containsKey("s")){return;}
            Random random = new Random();
            Person person = new Person();
            map.get("n").forEach(person::setFirstName);
            map.get("s").forEach(person::setLastName);
            person.setId(random.nextInt(100));
            person.addStringObserver(observer);
//            person.addStringObserver((oldValue, newValue) -> System.out.println(oldValue+" "+newValue));
            persons.add(person);
        }));
        scanner.addCommand(new Command("remove", "").setConsumer(map -> {
            if (!map.containsKey("id")||map.keySet().size()<1){return;}
            final Person[] person = {null};
            map.get("id").forEach(s -> persons.stream().filter(person1 -> person1.getId()==Integer.parseInt(s))
                    .collect(Collectors.toList()).forEach(person1 -> person[0] = person1));
            if (person[0]!=null){persons.remove(person[0]);}
        }));
        scanner.addCommand(new Command("print", "").setConsumer(map -> persons.forEach(System.out::println)));
        scanner.addCommand(new Command("edit", "").setConsumer(map -> {
            if (map.keySet().size()<1||!map.containsKey("id")){return;}
            final Person[] person = {null};
            map.get("id").forEach(s -> persons.stream().filter(person1 -> person1.getId()==Integer.parseInt(s))
                    .collect(Collectors.toList()).forEach(person1 -> person[0] = person1));
            if (map.containsKey("n")){map.get("n").forEach(person[0]::setFirstName);}
            if (map.containsKey("s")){map.get("s").forEach(person[0]::setLastName);}
        }));
    }
}
