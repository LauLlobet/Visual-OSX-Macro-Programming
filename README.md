## Visual OSX Macro Programming

Inspired by event driven programming language [Pure Data](https://en.wikipedia.org/wiki/Pure_Data), this proof of concept aims to build a visual macro creator. It tracks certain parts of the screen by placing floating widget on it and it takes some actions at other part of the screen where other floating widgets are located.

The project came into a critical point when its real use case started to be done. It showed that it's hard to build the macro because regular computer users constantly minimize and moves windows.

This lead to a "window-following" objects, for this action some test have been done using "image feature finding". 
With this project code graphic patterns such as window corners can be tracked. Though it appeared to be that it's computational time is out of reach by current desktop computers.


![alt text](https://github.com/LauLLobet/Visual-OSX-Macro-Programming/raw/master/animation.gif "Logo Title Text 1")
See video of how it works [here](https://youtu.be/gX0Ift1YbQM)

### How to run it
** Execute with java 1.6 in OSX /src/test/java/view/UI/VObjectTSTest.java*

### Libraries used:
* Java's Robot (for mouse macro actions and screen capture)
* Java's Swing (for transparent and clickable through windows)
* Jackson (for the persistence)

#### Design patterns
* MVC using mirroring, for the view - persistance - logic interaction.
* 1:1 Producer-Consumer, for the comunication between macro objects.
* Factory, for creating the objects
* Template, for implementing concrete reactions to standard input of macro objects
* 
