import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;
import org.nocrala.tools.texttablefmt.*;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

public class Book{

    private static int nextId = 1;
    private int id;
    private String title;
    private Author author;
    private String publishedYear;
    private String status;


    // Constructor to generate a unique ID automatically
    public Book(String title, String name,String activeyear, String publishedYear, String status) {

        this.id = nextId++;
        this.title = title;
        this.author = new Author(name, activeyear);
        this.publishedYear = publishedYear;
        this.status = status;
    }
    public Book() {
        id = nextId++;
        title = "";
      //  author = null; // or create an empty Author object
        this.author = new Author();
        publishedYear = "";
        status = "";
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_Green = "\u001B[32m";
    public static final String ANSI_Red = "\u001B[31m";
    public static final String ANSI_Blue = "\u001B[34m";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(String publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void menu(String name, String location) {

        System.out.println(ANSI_YELLOW + "========= " + name + " LIBRARY," + location + " =========");
        System.out.println("1- Add Book");
        System.out.println("2- Show All Books");
        System.out.println("3- Show Available Books");
        System.out.println("4- Borrow Book");
        System.out.println("5- Return Book");
        System.out.println("6- Search Book");
        System.out.println("7- Remove Book");
        System.out.println("8- Exit");
        System.out.println("---------------------------------------------------------------" + ANSI_RESET);


    }

    // Getters and setters for all attributes

    // Method to check if the book was published within the author's active years
//    public boolean isPublishedDuringAuthorActiveYears() {
//        return author.getBirthYear() <= publishedYear && author.getDeathYear() >= publishedYear;
//    }
    void input() {
        System.out.println("Book ID :"+getId());
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book's name :");;
        title=scanner.nextLine();
        System.out.print("Enter Author's name :");
        author.name = scanner.nextLine();
        System.out.print("Enter Author Year Active: ");
        author.activeYear = scanner.nextLine();
        System.out.print("Enter Published Year :");
        publishedYear=scanner.nextLine();
        status="Available";



    }
    public static void main(String agrs[]) {

        String libraryName = "", location = "";
        Scanner scanner = new Scanner(System.in);
        // timezone
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Phnom_Penh"));
        int i = 0;
        System.out.println(ANSI_YELLOW + "========= SET UP LIBRARY =========" + ANSI_RESET);
        System.out.print("=> Enter Library's Name :");
        libraryName = scanner.nextLine();
        System.out.print("=>Enter Library's Address :");
        location = scanner.nextLine();
        System.out.println(ANSI_Green + "\" " + libraryName + " \" Library is already created in \"" + location + "\" address\n" +
                "successfully on " + now.format(DateTimeFormatter.ofPattern("EEE, MMM dd HH:mm:ss")));



        int option=0;
        String status;
        // initailized book array

        Book bk1[] = new Book[]{
                new Book("The Lord of the Rings", " scholar J. R. R. Tolkien", "1950-Present","1950", "Available"),
                new Book("Rich Dad Poor Dad", "Robert T. Kiyosaki ","1997-Present", "1997", "Available"),
                new Book("The Power of Life", "Isamu Mochizuki","2007-Present", "2007", "Unavailable")

        };


        do{
            menu(libraryName, location);
            System.out.print("Choose option(1-8) :");
            option=scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1: {
                    Book[] newBk1 = new Book[bk1.length + 1];
                    Book newBook = new Book();  // Use default constructor or provide necessary arguments
                    // Create a new array with one extra element
                    newBk1[bk1.length] = newBook;
                    // Copy elements from the original array to the new array
                    for (i = 0; i < bk1.length; i++) {
                        newBk1[i] = bk1[i];
                    }

                    // Get input for the new book
                    newBk1[bk1.length].input();

                    // Assign the new array back to bk1
                    bk1 = newBk1;

                    System.out.println("Book is added successfully");
                }
                break;
                case 2: {
                    // style table
                    CellStyle numstyle = new CellStyle(CellStyle.HorizontalAlign.center);
                    Table t = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER);
                    System.out.println("========= ALL BOOKS INFO =========");
                    if (bk1.length == 0) {
                        System.out.println("No Book Available");
                    }

                    t.setColumnWidth(0, 10, 10);
                    t.setColumnWidth(1, 20, 50);
                    t.setColumnWidth(2, 15, 50);
                    t.setColumnWidth(3, 15, 15);
                    // Add a row with variables, applying the style to the first cell
                    t.addCell(ANSI_Blue + "ID", numstyle);
                    t.addCell(ANSI_Blue + "Title", numstyle);
                    t.addCell(ANSI_Blue + "Author", numstyle);
                    t.addCell(ANSI_Blue + "PublishedYear", numstyle);
                    t.addCell(ANSI_Blue + "Status", numstyle);
                    for (i = 0; i < bk1.length; i++) {


                        t.addCell(String.valueOf(bk1[i].id), numstyle);
                        t.addCell(bk1[i].title, numstyle);
                        t.addCell(bk1[i].author.getAuthor(), numstyle);
                        t.addCell(String.valueOf(bk1[i].publishedYear), numstyle);
                        if (bk1[i].status.equals("Available") == true)
                            t.addCell(ANSI_Green + bk1[i].status, numstyle);
                        else
                            t.addCell(ANSI_Red + bk1[i].status, numstyle);
                    }

                    System.out.println(t.render());


                    System.out.print("Press \"ENTER\" to continue...");
                    scanner.nextLine();

                }
                break;
                case 3: {

                    CellStyle numstyle1 = new CellStyle(CellStyle.HorizontalAlign.center);
                    Table t1 = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER);
                    System.out.println("========= AVAILABLE BOOKS INFO =========");
                    int f = 0;
                    boolean printedAvailableBooks = false;
                    t1.addCell(ANSI_Blue+"ID", numstyle1);
                    t1.addCell(ANSI_Blue+"Title", numstyle1);
                    t1.addCell(ANSI_Blue+"Author", numstyle1);
                    t1.addCell(ANSI_Blue+"PublishedYear", numstyle1);
                    t1.addCell(ANSI_Blue+"Status", numstyle1);
                    for (i = 0; i < bk1.length; i++) {

                        if (bk1[i].status.equals("Available") == true) {
                            t1.addCell(String.valueOf(bk1[i].id), numstyle1);
                            t1.addCell(bk1[i].getTitle(), numstyle1);
                            t1.addCell(bk1[i].author.getAuthor(), numstyle1);
                            t1.addCell(String.valueOf(bk1[i].publishedYear), numstyle1);
                            t1.addCell(ANSI_Green + bk1[i].status, numstyle1);
                            f = 1;
                            printedAvailableBooks = true;


                        }


                    }
                    if (printedAvailableBooks = true)
                        System.out.println(t1.render());


                    else
                        System.out.println("No available book");
                    System.out.print("Press \"ENTER\" to continue...");
                    scanner.nextLine();
                }
                break;
                case 4: {
                    int sid;
                    boolean found = false;
                    System.out.println("========= BORROW BOOK INFO =========");
                    System.out.print("Enter Book ID to Borrow :");
                    sid = scanner.nextInt();
                    scanner.nextLine();
                    for (i = 0; i < bk1.length; i++) {

                        if (bk1[i].getId() == sid) {
                            System.out.println("Book ID :" + sid);
                            System.out.println("Book Title :" + bk1[i].getTitle());
                            System.out.println("Book Author :" + bk1[i].author.getActiveYear());
                            System.out.println("Published Year : " + bk1[i].getPublishedYear() +ANSI_Blue+ " is borrowed successfully..."+ANSI_RESET);
                            bk1[i].setStatus("Unavailable");
                            found = true;

                        }
                    }
                    if (found == false)
                        System.out.println("Book ID : " + sid + " is not Exist…");
                    System.out.print("Press \"ENTER\" to continue...");
                    scanner.nextLine();

                }
                break;
                case 5: {
                    System.out.println("========= RETURN BOOK INFO =========");
                    System.out.print("Enter Book ID to Return : ");
                    int sid = scanner.nextInt();
                    scanner.nextLine();
                    boolean found = false;
                    for (i = 0; i < bk1.length; i++) {

                        if (bk1[i].getId() == sid && bk1[i].getStatus().equals("Unavailable") == true) {
                            System.out.println("Book ID :" + sid);
                            System.out.println("Book Title :" + bk1[i].getTitle());
                            System.out.println("Book Author :" + bk1[i].author.getActiveYear());
                            System.out.println("Published Year : " + bk1[i].getPublishedYear() + ANSI_Green+" is returned successfully..."+ANSI_RESET);
                            bk1[i].setStatus("Available");
                            found = true;

                        }


                    }
                    if (found == false)
                        System.out.println("Book ID : " + sid + " is failed to return...");
                    System.out.print("Press \"ENTER\" to continue...");
                    scanner.nextLine();
                }
                break;
                case 6: {
                    CellStyle numstyle1 = new CellStyle(CellStyle.HorizontalAlign.center);
                    Table t1 = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER);
                    System.out.print("Enter search book :");
                    String search = scanner.nextLine();
                    boolean found=false;
                    t1.setColumnWidth(0, 10, 10);
                    t1.setColumnWidth(1, 20, 50);
                    t1.setColumnWidth(2, 15, 50);
                    t1.setColumnWidth(3, 15, 15);
                    // Add a row with variables, applying the style to the first cell
                    t1.addCell(ANSI_Blue + "ID", numstyle1);
                    t1.addCell(ANSI_Blue + "Title", numstyle1);
                    t1.addCell(ANSI_Blue + "Author", numstyle1);
                    t1.addCell(ANSI_Blue + "PublishedYear", numstyle1);
                    t1.addCell(ANSI_Blue + "Status", numstyle1);
                    String str;
                    for (i = 0; i < bk1.length; i++) {
                        if (bk1[i].title.toLowerCase().indexOf(search.toLowerCase()) != -1) {// Book title matches the regex pattern
                            t1.addCell(String.valueOf(bk1[i].id), numstyle1);
                            t1.addCell(bk1[i].getTitle(), numstyle1);
                            t1.addCell(bk1[i].author.getAuthor(), numstyle1);
                            t1.addCell(String.valueOf(bk1[i].publishedYear), numstyle1);
                            str=bk1[i].getStatus();
                            if(str=="Unavailable")
                              t1.addCell(ANSI_Red+str);
                            else
                                t1.addCell(ANSI_Green+str);
                            found = true;
                        }
                    }


                    if (found == false)
                        System.out.println("Book  : " + search + " is not Exist…");

                    else
                        System.out.println(t1.render());
                    System.out.print("Press \"ENTER\" to continue...");
                    scanner.nextLine();
                    break;
                }
                case 7: {
                    int sid;
                    boolean found = false;
                    System.out.println("========= Remove BOOK  =========");
                    System.out.print("Enter Book ID to Remove :");
                    sid = scanner.nextInt();
                    scanner.nextLine();
                    int s;
                    for (i = 0; i < bk1.length; i++) {

                        if (bk1[i].getId() == sid) {
                            System.out.println("Book ID :" + sid);
                            System.out.println("Book Title :" + bk1[i].getTitle());
                            System.out.println("Book Author :" + bk1[i].author.getActiveYear());
                            System.out.println("Published Year : " + bk1[i].getPublishedYear() );
                            System.out.println(ANSI_Red+"Are you sure to remove this book ?");
                            System.out.print("Press 1 to remove or 0 to Cancle :"+ANSI_RESET);
                            s= scanner.nextInt();
                            while(s!=0)
                            {
                                bk1[i].setTitle(ANSI_Red+"Remove");
                                bk1[i].author.setActiveYear(ANSI_Red+"Remove");
                                bk1[i].author.setName(ANSI_Red+"Remove");
                                bk1[i].setPublishedYear(ANSI_Red+"Remove");
                                bk1[i].setStatus(ANSI_Red + "Unavailable");
                                s=0;
                            }
                            found = true;

                        }
                    }
                    if (found == false)
                        System.out.println("Book ID : " + sid + " is not Exist…");
                     System.out.print("Press \"ENTER\" to continue...");
                    scanner.nextLine();

                    break;
                }
            }
        }while(option!=8);
        System.out.println(ANSI_Blue+"(^-^) Good Bye Thanks Cher! >>(^-^)<<");

    }
}

 class Author {
    String activeYear;
    String name;
     Author(String name,String activeYear)
     {
         this.name=name;
         this.activeYear=activeYear;
     }
     Author ()
     {
         name="";
         activeYear="";
     }
     public String getActiveYear() {
         return activeYear;
     }

     public void setActiveYear(String activeYear) {
         this.activeYear = activeYear;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }
     public String getAuthor()
     {
         return name+" ("+activeYear+")";
     }


}


