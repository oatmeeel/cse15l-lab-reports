# Part 1
## Fixing the broken testcase in TestDocSearch.java with vim (I don't remember if this was supposed to be on the lab report but I put it in anyway just in case)
```
/10<Enter>lxi391<Esc>:wq<Enter>
```
* After entering the vim editor, typing "/10`<Enter>`" will result in the cursor jumping to the beginning of the 10 in "There are 10 total files to search." on line 14. "/" activates the search bar, which searches the current file for whatever the user types next. After typing "10", the cursor jumps to the desired area to edit. `<Enter>` exits search and moves the cursor to the result of the search.

* Typing "l" while in normal mode moves the cursor right by one character, so the cursor moves from |10 to 1|0. ("|" is supposed to represent the cursor)

* Typing "x" while in normal mode deletes the character to the right of the cursor, so "10" becomes "1".

* Typing "i" while in normal mode switches vim to input mode.

* While in input mode, typing "391" will insert those characters at the cursor (essentially how any text editor works). So "1" becomes "1391". Typing `<Esc>` while in input mode will return vim to normal mode.

* Typing ":wq`<Enter>`" saves the changes to the current file and exits the vim editor. Typing ":" enters vim into command mode. Typing "w" saves the changes to the current file. Typing "q" quits the vim editor. `<Enter>` runs the commands.

## Changing the name of the `start` parameter and its uses to `base`
```
:%s/start/base/g<Enter>:wq<Enter>
```
* ":" enters the command mode, whatever is typed after this is considered part of the command
![Image](./week7/Screen%20Shot%202022-11-13%20at%2011.09.25%20PM.png)

* "%" indicates to repeat the following command for all lines.

* "s" is the command for "substitute".
![Image](./week7/Screen%20Shot%202022-11-13%20at%2011.09.49%20PM.png)

* "/start" indicates what to find.

* "/base" indicates what to replace.

* "/g" indicates to replace every instance of "start" on a line with "base".
![Image](./week7/Screen%20Shot%202022-11-13%20at%2011.10.06%20PM.png)

* `<Enter>` executes the command and exits command mode.
![Image](./week7/Screen%20Shot%202022-11-13%20at%2011.10.17%20PM.png)

* Typing ":wq`<Enter>`" saves the changes to the current file and exits the vim editor. Typing ":" enters vim into command mode. Typing "w" saves the changes to the current file. Typing "q" quits the vim editor. `<Enter>` runs the commands.
![Image](./week7/Screen%20Shot%202022-11-13%20at%2011.10.36%20PM.png)
![Image](./week7/Screen%20Shot%202022-11-13%20at%2011.10.39%20PM.png)

# Part 2

Both methods took me about 20 seconds.

**Which of these two styles would you prefer using if you had to work on a program that you were running remotely, and why?**

* I would still prefer using scp because it is what I am more familiar with, if I forced myself to use vim my workflow would be slowed down by my unfamiliarity with the movememnt, shortcuts, and commands.

**What about the project or task might factor into your decision one way or another? (If nothing would affect your decision, say so and why!)**

* The frequency of edits to the file. If I could make a bunch of edits to a file between a few scp's then scp is much more efficient in my opinion. If I have to make line by line edits between each scp, then at some point it would take so long to scp the entire file every single time that I would be very tempted to just use vim.

