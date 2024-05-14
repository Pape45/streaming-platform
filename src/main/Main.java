package main;

import main.model.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.util.DatabaseManager;
import main.util.DatabaseSetup;

import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {

    private static Stage stg;
    private static User currentUser;
    private static Profile currentProfile;

    public static void main(String[] args) {
        DatabaseSetup.setupDatabase();

        /*
            De-comment this block for the first run of the application to create the database and setting up test data
        DatabaseSetup.setupDatabase();
        User user1 = new User("John@john.com", "Doe202202", "01/01/1990", "123456789", DatabaseManager.getSubscriptionID("Single Use"));
        User user2 = new User("Jane@jane.com", "Doe202203", "01/01/1990", "987654321", DatabaseManager.getSubscriptionID("Two Simultaneous Uses"));
        Subscription singleUse = new Subscription("Single Use");
        Subscription twoSimultaneousUses = new Subscription("Two Simultaneous Uses");
        Subscription fourSimultaneousUses = new Subscription("Four Simultaneous Uses");
        singleUse.saveSubscription();
        twoSimultaneousUses.saveSubscription();
        fourSimultaneousUses.saveSubscription();

         */
        Subscription singleUse = new Subscription("Single Use");
        singleUse.saveSubscription();
        Subscription twoSimultaneousUses = new Subscription("Two Simultaneous Uses");
        twoSimultaneousUses.saveSubscription();
        Subscription fourSimultaneousUses = new Subscription("Four Simultaneous Uses");
        fourSimultaneousUses.saveSubscription();
        User user3 = new User("", "", "01/01/1990", "123456789", DatabaseManager.getSubscriptionID("Four Simultaneous Uses"));
        user3.saveUser();
        ArrayList<String> theDarkKnightActors = new ArrayList<>();
        theDarkKnightActors.add("Christian Bale");
        theDarkKnightActors.add("Heath Ledger");
        theDarkKnightActors.add("Aaron Eckhart");
        theDarkKnightActors.add("Maggie Gyllenhaal");

        Movies theDarkKnight = new Movies("Action", "The Dark Knight",
                "Batman faces the Joker, who is causing chaos in Gotham City", "13", "2008", "2h32m", "Christopher Nolan", "theDarkKnight.jpg",
                theDarkKnightActors, "94%", "https://www.youtube.com/watch?v=EXeTwQWrcwY", true);

        ArrayList<String> inceptionActors = new ArrayList<>();
        inceptionActors.add("Leonardo DiCaprio");
        inceptionActors.add("Joseph Gordon-Levitt");
        inceptionActors.add("Ellen Page");
        inceptionActors.add("Tom Hardy");

        Movies inception = new Movies("Sci-Fi", "Inception",
                "A thief who enters the dreams of others to steal their secrets", "13", "2010", "2h28m", "Christopher Nolan", "inception.jpg",
                inceptionActors, "87%", "https://www.youtube.com/watch?v=YoHD9XEInc0", true);

        ArrayList <String> homeAloneActors = new ArrayList<>();
        homeAloneActors.add("Macaulay Culkin");
        homeAloneActors.add("Joe Pesci");
        homeAloneActors.add("Daniel Stern");
        homeAloneActors.add("Catherine O'Hara");

        Movies homeAlone = new Movies("Comedy", "Home Alone","An eight-year-old troublemaker, mistakenly left home alone, must defend his home against a pair of burglars on Christmas Eve.", "7", "1990", "1h43m", "Chris Columbus", "homealone.jpg", homeAloneActors, "80%",
                "https://www.youtube.com/watch?v=CK2Btk6Ybm0", true);

        ArrayList <String> breakingBadActors = new ArrayList<>();
        breakingBadActors.add("Bryan Cranston");
        breakingBadActors.add("Aaron Paul");
        breakingBadActors.add("Anna Gunn");
        breakingBadActors.add("Dean Norris");

        Series breakingBad = new Series("Drama", "Breaking Bad",
                "A high school chemistry teacher turned meth producer", "18", "2008", "5 seasons", "Vince Gilligan", "breakingbad.jpeg", breakingBadActors, "96%",
                "https://www.youtube.com/watch?v=HhesaQXLuRY", true);

        ArrayList <String> theMandalorianActors = new ArrayList<>();
        theMandalorianActors.add("Pedro Pascal");
        theMandalorianActors.add("Gina Carano");
        theMandalorianActors.add("Giancarlo Esposito");
        theMandalorianActors.add("Carl Weathers");

        Series theMandalorian = new Series("Sci-Fi", "The Mandalorian",
                "A lone gunfighter makes his way through the outer reaches of the galaxy", "13", "2019", "2 seasons", "Jon Favreau", "themandalorian.jpg", theMandalorianActors, "93%",
                "https://www.youtube.com/watch?v=eW7Twd85m2g", true);

        ArrayList <String> familyReunionActors = new ArrayList<>();
        familyReunionActors.add("Tia Mowry-Hardrict");
        familyReunionActors.add("Anthony Alabi");
        familyReunionActors.add("Talia Jackson");
        familyReunionActors.add("Isaiah Russell-Bailey");

        Series familyReunion = new Series("Comedy", "Family Reunion",
                "A family from Seattle moves to the South", "7", "2019", "3 seasons", "Meg DeLoatch", "familyreunion.jpeg", familyReunionActors, "90%",
                "https://www.youtube.com/watch?v=kMuC1TJaCKE", false);

        Documentary planetEarth = new Documentary("Documentary", "Planet Earth", "A documentary series about the planet Earth", "13", "2006",
                "1 season", "planetearth.jpg", "David Attenborough", "98%", "https://www.youtube.com/watch?v=aETNYyrqNYE", true);

        Documentary cosmos = new Documentary("Documentary", "Cosmos: A Spacetime Odyssey", "A documentary series about the universe", "13", "2014", "1 season",
                "cosmos.jpg", "Neil deGrasse Tyson", "95%", "https://www.youtube.com/watch?v=I4Cv8XtHuZc", true);

        Documentary catPeople = new Documentary("Documentary", "Cat People", "A documentary series about people who love cats", "7", "2021", "1 season",
                "catpeople.jpg", "Glen Zipper", "85%", "https://www.youtube.com/watch?v=I4Cv8XtHuZc", false);
        Documentary catPeople2 = new Documentary("Documentary", "Cat People", "A documentary series about people who love cats", "19", "2021", "1 season",
                "catpeople.jpg", "Glen Zipper", "85%", "https://www.youtube.com/watch?v=I4Cv8XtHuZc", false);

        Documentary catPeople3 = new Documentary("Documentary", "Cat People", "A documentary series about people who love cats", "19", "2021", "1 season",
                "catpeople.jpg", "Glen Zipper", "85%", "https://www.youtube.com/watch?v=I4Cv8XtHuZc", false);
        Documentary catPeople4 = new Documentary("Documentary", "Cat People", "A documentary series about people who love cats", "19", "2021", "1 season",
                "catpeople.jpg", "Glen Zipper", "50%", "https://www.youtube.com/watch?v=I4Cv8XtHuZc", false);
        Documentary catPeople5 = new Documentary("Documentary", "Cat People", "A documentary series about people who love cats", "19", "2021", "1 season",
                "catpeople.jpg", "Glen Zipper", "25%", "https://www.youtube.com/watch?v=I4Cv8XtHuZc", false);


        theDarkKnight.saveMovie();
        inception.saveMovie();
        homeAlone.saveMovie();
        breakingBad.saveSeries();
        theMandalorian.saveSeries();
        familyReunion.saveSeries();
        planetEarth.saveDocumentary();
        cosmos.saveDocumentary();
        catPeople.saveDocumentary();
        catPeople2.saveDocumentary();
        catPeople3.saveDocumentary();
        catPeople4.saveDocumentary();
        catPeople5.saveDocumentary();

        Season breakingBadSeason1 = new Season(7, breakingBad.getPiece_id());
        Season breakingBadSeason2 = new Season(13, breakingBad.getPiece_id());

        Season theMandalorianSeason1 = new Season(8, theMandalorian.getPiece_id());
        Season theMandalorianSeason2 = new Season(8, theMandalorian.getPiece_id());

        Episode breakingBadSeason1Episode1 = new Episode("Pilot", 58, breakingBadSeason1.getId());
        Episode breakingBadSeason1Episode2 = new Episode("Cat's in the Bag...", 48, breakingBadSeason1.getId());

        Episode breakingBadSeason2Episode1 = new Episode("Seven Thirty-Seven", 47, breakingBadSeason2.getId());
        Episode breakingBadSeason2Episode2 = new Episode("Grilled", 48, breakingBadSeason2.getId());

        Episode theMandalorianSeason1Episode1 = new Episode("Chapter 1: The Mandalorian", 39,
                theMandalorianSeason1.getId());
        Episode theMandalorianSeason1Episode2 = new Episode("Chapter 2: The Child", 32, theMandalorianSeason1.getId());

        Episode theMandalorianSeason2Episode1 = new Episode("Chapter 9: The Marshal", 52,
                theMandalorianSeason2.getId());
        Episode theMandalorianSeason2Episode2 = new Episode("Chapter 10: The Passenger", 41, theMandalorianSeason2.getId());

        User user1 = new User("John", "Doe", "01/01/1990", "123456789", DatabaseManager.getSubscriptionID("Single Use"));
        User user2 = new User("Jane", "Doe", "01/01/1990", "987654321", DatabaseManager.getSubscriptionID("Two Simultaneous Uses"));

        UserList userList1 = new UserList(DatabaseManager.getUserID(user1.getLogin()), "Favorites");
        UserList userList2 = new UserList(DatabaseManager.getUserID(user2.getLogin()), "Watch Later");

        Evaluation evaluation1 = new Evaluation(DatabaseManager.getUserID(user1.getLogin()), theDarkKnight.getPiece_id());
        Evaluation evaluation2 = new Evaluation(DatabaseManager.getUserID(user1.getLogin()), breakingBad.getPiece_id());

        Evaluation evaluation3 = new Evaluation(DatabaseManager.getUserID(user2.getLogin()), inception.getPiece_id());
        Evaluation evaluation4 = new Evaluation(DatabaseManager.getUserID(user2.getLogin()), theMandalorian.getPiece_id());

        evaluation1.setScore(5);
        evaluation2.setScore(4);

        evaluation3.setScore(5);
        evaluation4.setScore(4);

        userList1.addPieceId(theDarkKnight.getPiece_id());
        userList1.addPieceId(breakingBad.getPiece_id());

        userList2.addPieceId(inception.getPiece_id());
        userList2.addPieceId(theMandalorian.getPiece_id());
        userList2.addPieceId(planetEarth.getPiece_id());
        userList2.addPieceId(cosmos.getPiece_id());

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stg = primaryStage;
            stg.setResizable(false);
            stg.setWidth(1000);
            stg.setHeight(800);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/login.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("view/style.css").toExternalForm());
            primaryStage.setTitle("FlixNet");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void changeScene(String fxml) {
        try {
            Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/" + fxml + ".fxml")));
            stg.getScene().setRoot(pane);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Profile getCurrentProfile() {
        return currentProfile;
    }

    public static void setCurrentProfile(Profile currentProfile) {
        Main.currentProfile = currentProfile;
    }
}