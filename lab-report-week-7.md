# Part 1
```
/10<Enter>lxi391<Esc>:wq<Enter>
```
* After entering the vim editor, typing "/10`<Enter>`" will result in the cursor jumping to the beginning of the 10 in "There are 10 total files to search." on line 14. "/" activates the search bar, which searches the current file for whatever the user types next. After typing "10", the cursor jumps to the desired area to edit. `<Enter>` exits search and moves the cursor to the result of the search.

* Typing "l" while in normal mode moves the cursor right by one character, so the cursor moves from |10 to 1|0. ("|" is supposed to represent the cursor)

* Typing "x" while in normal mode deletes the character to the right of the cursor, so "10" becomes "1".

* Typing "i" while in normal mode switches vim to input mode.

* While in input mode, typing "391" will insert those characters at the cursor (essentially how any text editor works). So "1" becomes "1391". Typing `<Esc>` while in input mode will return vim to normal mode.

* Typing ":wq`<Enter>`" saves the changes to the current file and exits the vim editor. Typing ":" enters vim into command mode. Typing "w" saves the changes to the current file. Typing "q" quits the vim editor. `<Enter>` runs the commands.

```
:%s/start/base/g<Enter>:wq<Enter>
```
* ":" enters the command mode, whatever is typed after this is considered part of the command

* "%" indicates to repeat the following command for all lines.

* "s" is the command for "substitute".

* "/start" indicates what to find.

* "/base" indicates what to replace.

* "/g" indicates to replace every instance of "start" on a line with "base".

* `<Enter>` executes the command and exits command mode.

* Typing ":wq`<Enter>`" saves the changes to the current file and exits the vim editor. Typing ":" enters vim into command mode. Typing "w" saves the changes to the current file. Typing "q" quits the vim editor. `<Enter>` runs the commands.

# Part 2

