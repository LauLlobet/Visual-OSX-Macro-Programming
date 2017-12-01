## Visual OSX Macro Programming

Inspired by event driven programming language [Pure Data](https://en.wikipedia.org/wiki/Pure_Data), this proof of concept aims to build a visual macro connecting floating widgets. It tracks certain parts of the screen widgets on it and it triggers signals that can be routed and tied to other widgets that have a certian UI reaction (click, write text..).

The project came into a critical point when its real use cases where done. They failed to be practical due to the constant minimizings and moving of windows that a regular user performs in their daily use.

This lead to a "window-following" objects, for this action some test have been done using "image feature finding". 
With this project code, graphical patterns (such as window corners) can be tracked. But it's computational time is out of reach by current desktop computers for a normal use.


![alt text](https://github.com/LauLLobet/Visual-OSX-Macro-Programming/raw/master/animation.gif "Logo Title Text 1")

See video of how it works [here](https://youtu.be/gX0Ift1YbQM)

### How to run it
**Execute with java 1.6 in OSX /src/test/java/view/UI/VObjectTSTest.java**

### Libraries used:
* Java's Robot (for mouse macro actions and screen capture)
* Java's Swing (for transparent and clickable through windows)
* Jackson (for the persistence)

#### Design patterns
* MVC using mirroring, for the view - persistance - logic interaction.
* 1:1 Producer-Consumer, for the comunication between macro objects.
* Factory, for creating the objects
