# Part 1 (Week 2 - Remote Server and Search Engine)

**my code**

The following was my code for my implementation of SearchEngine (heavily based off NumberServer) from the week 2 lab. I also copied over the same Server.java file, Handler.class, ServerHttpHandler.class, and URLHandler.class from the given lab materials.

```
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
```
These are the variables that I will be refering to throughout the rest of Part 1:
```
ArrayList<String> myList = new ArrayList<String>();
ArrayList<String> searchResults = new ArrayList<String>();
String newString = "hello";
String searchString = "hello2";
```


**homepage (empty path)**

This is what the server looks like when first started:

![Image](./week%203/serverempty.png)

The search engine navigates to the home page because the path of the url (gotten by the url.getPath() method) is empty. This is not technically a method, it's an if statement (because I copied the formatting of the original NumberServer and used to implement my version of SearchEngine).
```
if (url.getPath().equals("/"))
```
In order for this if statement to run, the parameter (the path in the url) must be either blank or /. It checks so by calling the equals() method. This if statement doesn't change anything about the relevant fields of the class. It only interacts with them in that it calls
```
myList.toString()
```
in order to print it on the page. (it also calls String.format() to format said output with the message into a string)

**add**

This is the message that the server shows when the add method is called through the url. It checks whether or not "add" is in the url with the contains() method.

![Image](./week%203/serveradd.png)

The search engine executes the add if statement (shown below) because the url (gotten by the url.getPath() method) contains add.


```
if (url.getPath().contains("/add"))
```

It sets the array String[] parameters to the output of the URI getQuery() method (split at the = sign). getQuery() essentially returns the part of the url after the Query (the ? mark). The portion of the url after ? is split into "s" (which is used to indicate the string) and the actual string to be added by "=". The split() is used to separate the query into the two parts that we will use separately. The two parts are stored as two indices of the array String[].

```
String[] parameters = url.getQuery().split("=");
```

The code then sets newString, our placeholder variable that is currently set to "hello", to second element in parameters (index 1) which is the second part of the url after the query.

```
newString = parameters[1];
```
Afterwards, it uses the add() method to add the newString to myList and prints a message saying that it did so.

![Image](./week%203/serveradd2.png)

If we revisit the home page (blank path), then we can see that myList now contains the string we just added.

**search**

Before we dive into the search method, I have added more strings to myList with the add method so that we have a larger volume to search through.

![Image](./week%203/serveradd3.png)

This is the message that the server shows when the search method is called through the url. It checks whether or not "search" is in the url with the contains() method.

![Image](./week%203/serversearch.png)

The search engine executes the search if statement (shown below) because the url (gotten by the url.getPath() method) contains search.

```
if (url.getPath().contains("/search"))
```

It splits the query into two and stores it in an array just like the add method, so I won't repeat it again, just that it is essentially the same functionality for this part.

```
String[] parameters = url.getQuery().split("=");
if (parameters[0].equals("s")) {
    searchString = parameters[1];
```
The only difference here is that in the line after, it calls the clear() method to clear the searchResults array of any previous search results.

```
searchResults.clear();
```

It then iterates through myList to look for elements containing the search string (with the contains() method). Whether or not it finds any results dictates what direction the code goes moving forward. Everytime it finds a result, it uses the add() method to add it to searchResults.

```
for (int i = 0; i < myList.size(); i++) {
    if (myList.get(i).contains(searchString)) {
        searchResults.add(myList.get(i));
    }
}
```

If it doesn't find any results (indicated by calling the size() method on searchResults and seeing if it equals 0), it will simply print a message on the page indicating that it did not find any results.

However, if searchResults is not empty, then it will call the toString() method on search results (it must be reformatted from an array in order to be printed as a string). It then runs String.format on the message combined with the searchResults string in order to print it to the page.

```
if (searchResults.size() == 0) {
    return "didn't find any results";
} else {
    return String.format("Results found: %s", searchResults.toString());
```

I've pasted the search screenshot again to show the string that is printed on the page "Results found: [hello, hello, jello, mello]"

![Image](./week%203/serversearch.png)

# Part 2 (Week 3 - Symptoms and Failure-inducing Inputs)

## Array Method: reversedInPlace (first bug)
For the three screenshots below, we only care about the reversedInPlace method. This means that the only testcase, terminal output, and code changes that I'm explaining are those referring to reversedInPlace (i.e. testReversedInPlace1). I know that we are supposed to do one bug per file for the lab report, but the screenshots I have already include both and I didn't take new ones.

**the failure-inducing input (the code of the test)**

![Image](./week%203/arrayfailureinducinginput.png)

The failure inducing input was that when given an array {1, 2, 3} that was larger than one element, the method failed to reverse it. (essentially, it was never able to reverse an array)

**the symptom (the failing test output)**

![Image](./week%203/arraysymptom.png)

As shown by the error report, the array differed from the expected output at the second index (the third element). The disfunctional method outputted {3, 2, 3} as the reversed array instead of the expected {3, 2, 1}. This is because in the for loop, when the loop is executed for i = 2
```
arr[2] = arr[arr.length - 2 - 1]
```
results in
```
arr[2] = arr[0]
```
but at this point in the loop, arr[0] had already been set to the original arr[2], so arr[0] = 3 (and thus arr[2] is set to 3).

**the bug (the code fix needed)**

![Image](./week%203/arraychange.png)

**explaination of changes to fix**

The reason for this behavior is that in the for loop (the second for loop in the screenshot above) arr was setting each index of itself to the "opposite" index in the array. This works fine for the first half of the array; however, by the time it gets to the second half of the array, the information for what the "opposite" indices should be for the second half of the array has already been replaced (because it had already been overriden by its own "opposite" index).

In order to fix it, i made a temporary deep copy of arr using another for loop. This temp array can be used as a reference for reversing arr. This way, when the second for loop gets to the second half of the array, the information for the first half of the array is still stored and therefore can still be copied over by setting each element in arr to the "opposite" element in temp.


## LinkedList Method: append (second bug)
For the next bug, it concerns the functionality of the append method of the LinkedListExample file. 

**the failure-inducing input (the code of the test)**

![Image](./week%203/linkedlistfailureinducinginput.png)

The failure inducing input was trying to append an element to a LinkedList that already contains more than one element. (The assertEquals statement does not have an effect on the test case failing because later we can see that the test case never even gets to that point in the code)

**the symptom (the failing test output)**

![Image](./week%203/linkedlistsymptom1.png)

The method failed to output anything, instead it times out after being stuck on a loop for a while. 

![Image](./week%203/linkedlistsymptom2.png)

**the bug (the code fix needed)**

![Image](./week%203/linkedlistchange.png)

**explaination of changes to fix**

The reason for this behavior was that the line of code
```
n.next = new Node(value, null)
```
was placed within the while loop instead of outside of it. This resulted in it setting the next node after n to a new node indefinitely. Therefore, the while loop can never end because n.next can never be null since it is always set to a new node with the parameter of "value" with each while loop execution. This results in the method appending new nodes of "value" to the end of the LinkedList until it runs out of memory.

The solution is to move that line of code to outside and after the while loop. This way, it only executes once and appends to the end of the LinkedList once, which is the behavior we are expecting.