import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Management {
    protected List <Human> persons = new ArrayList<Human>();
    protected Set <Hotel> hotels = new HashSet<Hotel>();
    WriteFile audit = WriteFile.getInstance();

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    String method = new Throwable().getStackTrace()[0].getMethodName();

    public Management() throws IOException {

       File clients = new File("C:\\Users\\Andrei\\Desktop\\ANUL 2.2\\PAO\\Tema\\src\\clients.csv");
        Scanner inClient = new Scanner(clients);
        while (inClient.hasNextLine()) {
            String temp = inClient.nextLine();
            String[] data = temp.split(",");
            this.addClient(new Client(data[0], data[1], new Reservation(Integer.valueOf(data[2]), Integer.valueOf(data[3]), Integer.valueOf(data[4]))));
        }


        File candidates = new File("C:\\Users\\Andrei\\Desktop\\ANUL 2.2\\PAO\\Tema\\src\\candidates.csv");
        Scanner inCandidates = new Scanner(candidates);
        while (inCandidates.hasNextLine()) {
            String temp = inCandidates.nextLine();
            String[] data = temp.split(",");
            this.addCandidates(new Candidate(data[0], Integer.valueOf(data[1]), data[2], Integer.valueOf(data[3])));
        }

        File employees = new File("C:\\Users\\Andrei\\Desktop\\ANUL 2.2\\PAO\\Tema\\src\\employees.csv");
        Scanner inEmployees = new Scanner(employees);
        while (inEmployees.hasNextLine()) {
            String temp = inEmployees.nextLine();
            String[] data = temp.split(",");
            this.addEmployees(new Employee(data[0], Integer.valueOf(data[1]), Integer.valueOf(data[2]), Integer.valueOf(data[3])));
        }

        File leaders = new File("C:\\Users\\Andrei\\Desktop\\ANUL 2.2\\PAO\\Tema\\src\\Leaders.csv");
        Scanner inLeaders = new Scanner(leaders);
        while (inLeaders.hasNextLine()) {
            String temp = inLeaders.nextLine();
            String[] data = temp.split(",");
            this.addLeader(new Leader(data[0], Integer.valueOf(data[1]), Integer.valueOf(data[2])));
        }

        File royals = new File("C:\\Users\\Andrei\\Desktop\\ANUL 2.2\\PAO\\Tema\\src\\Royals.csv");
        Scanner inRoyal = new Scanner(royals);
        while (inRoyal.hasNextLine()) {
            String temp = inRoyal.nextLine();
            String[] data = temp.split(",");
            this.addRoyal(new RoyalClient(data[0], data[1], new Reservation(Integer.valueOf(data[2]), Integer.valueOf(data[3]), Integer.valueOf(data[4])),data[5],data[6],data[7]));
        }


        File hotels = new File("C:\\Users\\Andrei\\Desktop\\ANUL 2.2\\PAO\\Tema\\src\\hotels.csv");
        Scanner inHotel = new Scanner(hotels);
        while (inHotel.hasNextLine()) {
            String temp = inHotel.nextLine();
            String[] data = temp.split(",");
            this.addHotel(new Hotel(data[0],Integer.valueOf(data[1])));
        }



           CEO ceo = CEO.getInstance();


            System.out.println("\n ********** CEO : "  + ceo.getName() + " ********** \n");
            this.persons.add(ceo);


    }





    void addClient(Client c) {
        this.persons.add(c);
    }

    void addEmployees(Employee e) {
        this.persons.add(e);
    }

    void addCandidates(Candidate c) {
        this.persons.add(c);
    }

    void addRoyal(RoyalClient r) {this.persons.add(r);}

    void addLeader(Leader l) { this.persons.add(l);}

    void addHotel(Hotel h){ this.hotels.add(h); }



    void listAllClients() {

        for (Human c : this.persons)
            if (c instanceof Client)
                System.out.println(c.print());
            audit.audit();
    }

    void listAllRoyalClients() {


        for (Human c : this.persons)
            if (c instanceof RoyalClient)
                System.out.println(c.print());
        audit.audit();
    }

    void listAllCandidates() {

        for (Human c : this.persons)
            if (c instanceof Candidate)
                System.out.println(c.print());
        audit.audit();

    }

    void listAllEmployees() {



        for (Human c : this.persons)
        if (c instanceof Employee)
            System.out.println(c.print());
        audit.audit();
    }

    void listAllLeaders(){


        for (Human c : this.persons)
            if (c instanceof Leader)
                System.out.println(c.print());
        audit.audit();
    }

    void listAllHotels()
    {


        for(Hotel h : this.hotels)
            h.print();
        audit.audit();
    }

    int calculateProfit() {

        audit.audit();

        int salaries = 0;
        int earnings = 0;

        for (Human c : this.persons)
            if (c instanceof Employee)
              salaries += ((Employee)c).getSalary();

        for (Human c : this.persons)
            if (c instanceof Client)
            earnings += ((Client) c).getRezervation().getTotal_price();

        return earnings - salaries;
    }


    String getBestEmployee() {


        Human best = new Employee();
        audit.audit();


        for (Human c : this.persons) {

            if (((Employee)best).getBenefic_actions() < ((Employee)c).getBenefic_actions() && c instanceof Employee)
                best = c;
        }
        return " Employee of the month: " + best.getName();
    }




    void searchClient(String name) {

        audit.audit();

        for (Human c : this.persons) {
            if (name.equals(c.getName()) && c instanceof Client)
                System.out.println(c.print());
        }
    }


    void increaseSalary(String name, int bonus) {

        audit.audit();

        for (Human e : this.persons)
            if (e.getName().equals(name) && e instanceof Employee)
                {
                    ((Employee) e).setSalary(((Employee) e).getSalary() + bonus);
                    System.out.println("New salary: " + ((Employee) e).getSalary());
                return;
                 }
    }

    void decreaseSalary(String name, int bonus) {

       audit.audit();

        for (Human e : this.persons)
            if (e.getName().equals(name) && e instanceof Employee) {

                ((Employee) e).setSalary(((Employee) e).getSalary() - bonus);
                System.out.println("New salary: " + ((Employee) e).getSalary());
                return;

            }
    }

    void hireCandidate(String name) {

    audit.audit();

        for (Human c : this.persons) {
            if (c.getName().equals(name) && c instanceof Candidate) {
                persons.remove(c);
                this.addEmployees(new Employee(c.getName(), Employee.starting_salary , 27, 0));
                System.out.println("Hired");
                return;
            }
        }
    }

    void promote(String name) {

     audit.audit();

        for ( Human e : this.persons) {
            if (e.getName().equals(name) && e instanceof Employee) {
                persons.remove(e);
                this.addLeader(new Leader(e.getName(), Calendar.getInstance().get(Calendar.YEAR), ((Employee) e).getSalary()+1000));
                System.out.println("Promoted");
                return;
            }
        }
    }

    void sortCandidatesByExp()
    {
        audit.audit();

        Collections.sort(persons, new Comparator<Human>() {
            public int compare(Human h1, Human h2) {

                    if(h1 instanceof Candidate && h2 instanceof Candidate)
                    return Float.compare(((Candidate) h1).getYears_of_experience(), ((Candidate) h2).getYears_of_experience());
                    return 0;
            }
        });
        listAllCandidates();
    }


    void ChooseYourOption()
    {
        Scanner s = new Scanner(System.in);

        boolean input = true;
        printMenu();

        do{
            int option = s.nextInt();

        switch(option) {

            case 1:
                listAllClients();
                System.out.println("Press 0 to display the menu again");
                break;

            case 2:
                listAllRoyalClients();
                System.out.println("Press 0 to display the menu again");
                break;

            case 3:
                listAllEmployees();
                System.out.println("Press 0 to display the menu again");
                break;

            case 4:
                listAllLeaders();
                System.out.println("Press 0 to display the menu again");
                break;

            case 5:
                sortCandidatesByExp();
                System.out.println("Press 0 to display the menu again");
                break;

            case 6:

                Scanner s2 = new Scanner(System.in);
                hireCandidate(s2.nextLine());
                System.out.println("Press 0 to display the menu again");
                break;

            case 7:

                Scanner s3 = new Scanner(System.in);

                System.out.println("Type the name of the client : ");
                searchClient(s3.nextLine());

                System.out.println("Press 0 to display the menu again");
                break;

            case 8:
               System.out.println(getBestEmployee());
                System.out.println("Press 0 to display the menu again");
                break;


            case 9:

                Scanner s4 = new Scanner(System.in);
                System.out.println("Type the name of the employee : ");
                promote(s4.nextLine());
                System.out.println("Press 0 to display the menu again");
                break;


            case 10:
                Scanner s5 = new Scanner(System.in);
                System.out.println("Type the name of the employee and the bonus");
                increaseSalary(s5.nextLine(), s5.nextInt());
                System.out.println("Press 0 to display the menu again");
                break;


            case 11:

                Scanner s6 = new Scanner (System.in);
                System.out.println("Type the name of the employee and the amount");
                decreaseSalary(s6.nextLine(), s6.nextInt());
                System.out.println("Press 0 to display the menu again");
                break;


            case 12:
                System.out.println(calculateProfit());
                System.out.println("Press 0 to display the menu again");
                break;

            case 13:
                listAllHotels();
                System.out.println("Press 0 to display the menu again");
                break;

            case 0:
                printMenu();
                System.out.println(timestamp);
                break;

            default:
                System.out.println("Something went wrong");
                input = false;
                break;

        }

        } while(input);


    }

    void printMenu()
    {

        System.out.println("------------------ MENU ------------------ \n");

        System.out.println(" 1 --> Display all your clients ");
        System.out.println(" 2 --> Display royal clients");
        System.out.println(" 3 --> Display all your employees");
        System.out.println(" 4 --> Display the leading staff ");
        System.out.println(" 5 --> Display candidates sorted by experience ");
        System.out.println(" 6 --> Hire a candidate");
        System.out.println(" 7 --> Search client by name");
        System.out.println(" 8 --> Show your best employee ");
        System.out.println(" 9 --> Promote an employee");
        System.out.println(" 10 --> Increase salary of an employee ");
        System.out.println(" 11 --> Decrease salary of an employee ");
        System.out.println(" 12 --> Calculate  profit ");
        System.out.println(" 13 --> List all our hotels");

    }

}













