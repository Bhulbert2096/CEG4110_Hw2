# CEG 4110_HW2  Brandon Hulbert

Homework 2 has several basic functionalities.  The first is the creation of a digital clock to display the current time and allow the user to set the time.  The next is allowing the user to display a analog clock to display the current time and to set the time.  The third is the users ability to update the date and to obtain the current date.  The final function is the ability to have more than one analog clock or digital clock present at a time. 


### Tech
The basic structure for my drawanalog class was take from https://www.ssaurel.com/blog/learn-to-draw-an-analog-clock-on-android-with-the-canvas-2d-api/
which is a free and open source project on creating a analog clock custom view.
Hw1 itself is open source with a [public repository]
 on GitHub.

### Installation

Hw2 can be either be run through the APK on any system or download the source code
and opened in an IDE.  I used Android Studio and the programs minimum OS requirement is Android Oreo.
There is an APK added to this repository for ease of download and installation.

# Structure

TimeChangeController:
This classes purpose is to be a conduit between the view and the model class.  This class will allow the user to set the time, obtain the current time, set the date, obtain the current date, undo actions, redo actions, and ticking for the clock.  This class will get all the variables from model do some manipulation and send them back to the model and the view.  The undo and redo are implemented from the Command interface.

AnalogView:
This is a view class that will allow the user to actually click on specific buttons and do some action.  For example, change date, time, undo, and redo buttons action is recorded here.  The analog clock is able to be seen in a linear layout wrapped in a scrollview to add multiple analog clocks for the users viewing please.  

MainPage:
This will allow the user to click on the digital clock button or analog clock button and activate those views.

DrawAnalog:
This class will actually draw the circle, numbers, and the hands for the clock.  The hands length is calculated and updated every second or everytime tick(from timechangecontroller) is called.  This will get what the user had set as the time and draw it on the clock appropriately.  This class will also obtain the current local time and draw that where it will be displayed from the analogview class.  This class extends the view to create a custom view that can be accessed from the analogview class to be displayed to the user.

Model:
This class will be all the getters and setters that are needed for comunication with the view class.  For example, hour,day,second,minute,month, and year are just some examples of the variables accessable from the model class.  

myDigitalClock:
This class will allow the user to display mutliple digital clocks.  This will show the current time and allow the user to set the date and time that they want.  It also allows the user to undo and redo actions.  Now this class only takes in the users button actions and sends that data to the TimeChangeController to be manipulated and sent back to the myDigitalClock view to be displayed to the user.  
 
 # Examples
 ![1](https://github.com/Bhulbert2096/CEG4110_Hw2/blob/master/myClock/tmp2.PNG)
 ![1](https://github.com/Bhulbert2096/CEG4110_Hw2/blob/master/myClock/tmp3.PNG)
 ![1](https://github.com/Bhulbert2096/CEG4110_Hw2/blob/master/myClock/tmp.PNG)
 
 
# License
This project and all its dependencies are licensed under MIT
