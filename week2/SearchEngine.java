import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> myList = new ArrayList<String>();
    ArrayList<String> searchResults = new ArrayList<String>();
    String newString = "hello";
    String searchString = "hello2";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("List of Things: %s", myList.toString());
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    newString = parameters[1];
                    myList.add(newString);
                    return String.format("%s was added to List of Things!", newString);
                }
            }

            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    searchString = parameters[1];
                    searchResults.clear();
                    for (int i = 0; i < myList.size(); i++) {
                        if (myList.get(i).contains(searchString)) {
                            searchResults.add(myList.get(i));
                        }
                    }
                    if (searchResults.size() == 0) {
                        return "didn't find any results";
                    } else {
                        return String.format("Results found: %s", searchResults.toString());
                    }
                }
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
