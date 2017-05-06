# contentment_v0
This is the latest gradle-ized contentment project.

What Is Contentment?
--------------------

contentment is a program for creating presentations that feel "live" and "warm" by drawing lines, curves, and text on a screen, based on scripts composed of steps. It came out of my desire to make presentations that worked a certain way, and videos of those. The central concerns were and are:

1) "just java", because i am java-strong, and I specifically did not want to support the internet stack, which i despise working with.

2) "live drawing", because i wanted to watch lines and circles and text being drawn, as if i were drawing them myself while i spoke. i know people who can do that with a pad, but i am not one of them.

3) "replicability and editability", because my presentations don't emerge whole-grown, but develop and vary over time.

4) "interaction", because most of my presentations are live, and i need to be able to move around in them, going forward & backward, both skipping through steps and also playing them slowly.

5) "theory & practice", because i wanted to explore my current programming style, experiment with some new "rules", and use examples and insights from programming contentment in the actual content of my presentations.

6) "open source", because.

What State Is It In As Of 2017.5.6?
-----------------------------------

very alpha. it does just barely what i have needed it to do to date. tho the elapsed time is almost 8 months, i'd estimate i've spent about 60 hours in coding, and another 100-150 in not-coding.

How Does One Play With It?
--------------------------
the project has several sub-projects in it. however, that's an artifact of a partial step that i haven't had time to complete. all of the code is in the 'player' subfolder.

i use eclipse with the buildship plugin. if you have that plugin installed, you can import|gradle and aim it at the build.gradle in the root directory of the project. in theory, eclipse+buildship will take care of the rest. if it does not, don't hesitate to holler. just now, it adds a bunch of boilerplate sub-projects and the essential player project.

once it's in, you can run all of the microtests using the standard junit 4 run configuration, pointing it at player and selecting run all tests in project.

you can run the player itself by adding a java application run configuration and just making it call Main.main(...).

as of 2017.05.06, the scripts that the player runs are hardwired right into the code. if u glance at (ctrl-shift-T) PlayerView, you'll see a method 'makeScripts()' with several commented out lines. each one of those lines runs one of the scripts i've written. this is, of course, icky, and not intended to last for long. going forward, the next major change to contentment is to teach it how to find and load scripts from external jars. at that point, we won't have to change code to change scripts. This is non-trivial, not because classloaders are hard, but because teasing apart the things that are purposely locked into the system from things that just represent stuff i in particular needed is going to be a bear.

About Gradle
------------
i hate gradle. unfortunately, it is the best of a very bad lot. although i didn't need gradle for the basic app, i do need it for the jar loading stuff, as the dependencies on external jars will increase. eventually, i hope to push the binaries to well-known repos, and that means using a builder that can do complex shell things.

the eclipse/buildship <-> gradle integration is the best i've found so far, tho it still has plenty of flaws. some will wonder why i don't use IDEA's intellij, which is said to have better gradle support. there are two reasons. 1) i can't abide using "community edition" software in professional environments, and a great many of my professional environments don't/won't buy the proper license. i want one tool that i can use in both environments. 2) i am intimate with eclipse, and tho i'd get there sooner or later with intellij, i want this app RIGHT NOW, and feel a great pressure to make every moment i squeeze out of contentment development to be not involving yaks.

How Do I Use It?
----------------
at this crude state, u likely won't. but if u want to see a script run that's somewhat interesting, uncomment the makeScripts line that you want, or all of them, and run the player.

it comes up maximized but not true full-screen. if u mouse over to the right, a slider toolbar comes out with several buttons. one of them is 'full', which turns it to true full-screen. one of them is "->", which plays the next step. one of them is "-->", which jumps the next step. one of them is "-->||" which jumps to the very end. there are back-arrow versions of these, too.

if u are in full-screen mode u can hit escape to leave it.

if u just left-click anywhere it will play the next step. if i just right-click it will go backwards.

I Need More Info
----------------

a) look in the support subfolder. i will try to add some useful stuff there. the journal entries can be thot of as longer notes about the progress. the .uxf files are from the app 'umlet', which can be had here: http://www.umlet.com/

b) find me on twitter as @geepawhill or in email as geepawhill@geepawhill.org. ask me questions. yell at me to describe or explain this or that or the other.

