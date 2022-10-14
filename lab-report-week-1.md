# 1. Installing VScode

Go to the [Visual Studio Code](https://code.visualstudio.com/) website to install VScode for your operating system.

Once installed, you should be able to open the application. (it should look something like this but it won't have any files)

![Image](./week%201/installingvscode.png)

# 2. Remotely Connecting
Go to [this website](https://sdacs.ucsd.edu/~icc/index.php) to find your CSE 15L account name. The first nine characters should be cs15lfa22 and the last two letters are unique to each user.

Then, [reset your password](https://docs.google.com/document/d/1hs7CyQeh-MdUfM9uv99i8tqfneos6Y8bDU0uhn1wqho/edit) by following these instructions.



In order to open the terminal (the place where you can directly input commands), go to the toolbar at the top of your screen, click on the "Terminal" tab and then click on "New Terminal."

![Image](./week%201/openterminal.png)

Once the previous steps have been completed, you should be able to remotely connect to your account through the VScode terminal by running "ssh [your username]" and entering your password.

Once you have remotely connected, the terminal should display something similar to the image below.

![Image](./week%201/remotelyconnecting.png)

# 3. Trying Some Commands
Once remotely connected, you can run some basic commands from the terminal. A list of possible commands can be found online, but here are some that can get you started:

    cd <directory name>

cd can be used to change the directory to a directory of your choice.

    ls

ls lists the contents of the current directory.

    pwd

pwd can be used to display the location of the current working directory.

    mkdir <directory name>

mkdir can be used to create a new directory at a specific location.

    cp <filename> <new filename>

cp can be used to copy a file.

    cat <filename>

cat, short for concatenate, can be used to print the contents of a given file.

Plus there are many more commands not listed here.

![Image](./week%201/tryingsomecommands.png)

# 4. Moving Files with SCP
One easy way to tell if you have copied a file to the remote system is a java file that runs some commands that give information about the system it is located on. Create a file named WhereAmI.java on your client and paste this code into it:

    class WhereAmI {
        public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
        }
    }

Above is **not a screenshot**. You can highlight the code and copy/paste it just like any other text on the page (if you go to the website and view the page instead of the pdf, but I've pasted the code again below because it was one of the things I got marked down on and I don't want to argue).

class WhereAmI {  
public static void main(String[] args) {  
System.out.println(System.getProperty("os.name"));  
System.out.println(System.getProperty("user.name"));  
System.out.println(System.getProperty("user.home"));  
System.out.println(System.getProperty("user.dir"));  
}  
}

Afterwards, locally run "javac WhereAmI.java" and "java WhereAmI". You should get an output that shows you your operating system, your username, and the location of WhereAmI.java on your system.

![Image](./week%201/movingfileswithscp.png)

Next, you can copy the WhereAmI.java file to the remote system by running the command:

    scp WhereAmI.java <your username>@ieng6.ucsd.edu:~/

It will prompt you to enter your password just like it does with ssh.

Now, log onto the remote system. Run the same two commands in the previous step ("javac WhereAmI.java" and "java WhereAmI") and it should output the information of the remote system like this screenshot.

![Image](./week%201/movingfileswithscp2.png)

# 5. Setting an SSH Key
Next, you can run the command "ssh-keygen" on your local system to generate a public/private rsa key pair. One copy of this key will be saved locally and one will be saved on the remote system. This key serves as a fingerprint so that you don't have to re enter your password everytime you want to log onto the remote server.

See screenshot for specifics on what commands to run.

![Image](./week%201/settingansshkey.png)

It will prompt you to enter the file in which to save the key. I was a bit confused on this step because I didn't know what exactly to enter, but my TA said that I could just leave it blank by default.

When prompted to enter a passphrase, you can press "enter" to enter no passphrase (twice, because it asks you to enter your passphrase again).

After this, we need to copy the public rsa key to the remote system. To do this, first log onto the remote sytem as you have already and make a directory to store the copy of the key like so:

    $ mkdir .ssh

Then log out of the remote system and (back on your local system) enter the following command to copy the file from your system to the remote system (also shown in the screenshot but listed again here for convenience):

    $ scp /Users/<your user>/.ssh/id_rsa.pub <your username>@ieng6.ucsd.edu:~/.ssh/authorized_keys

It will prompt you for your password one more time, but after this you should be able to log in without entering a password.

# 6. Optimising Remote Running
Lastly, you can now run commands on the remote system directly from your local system. in order to do this, run your commands in this format:

    $ ssh cs15lfa22@ieng6.ucsd.edu "[put your command here, or multiple commands separated by commas]"

Other shortcuts/optimisations you can use to be more efficient include running multiple commands at once by separating them with semicolons, for example:

    $ your first command; your second command; additional commands; etc

Additionally, you can access your previously run commands by pressing the up arrow on your keyboard (you can keep pressing the up arrow to back further in your history of commands).

Below is a screenshot demonstrating some of the said shortcuts:

![Image](./week%201/optimisingremoterunning.png)