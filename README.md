# Cat Orphanage Application
For my application, I have decided on making a program that helps in assigning cats into compatible
foster homes. The program will have multiple functionalities: it will store all cats belonging to the shelter,
store all fosters working for the shelter, assign cats into compatible foster homes and so on, aswell as a myriad of
evolving other functionalities. However, the main purpose of the program is to help in determining compatible
foster homes for new cats, as well as cats that haven't found a home yet.

<br>
This application is meant to be used by a foster shelter for cats. Specifically it is meant to be able to help the 
process of assigning cats to foster families, as many databases current foster shelters have are outdated and lack
functionality. There are virtually no automated systems to my knowledge that check for compatibility of fosters,
as it is generally a manual process. This project was personally of interest to me, since from when I was 8 years old I 
have been a volunteer for a kitten orphanage rescue named VOKRA, where my family was also a foster for many years.
I along with this, saw how certain cats were put into less than ideal homes, for reasons such as cats sometimes needing
another cat to live with. I felt like this problem is one that is a fixable one, and there is no better way than
to automate it, as well as provide even further functionality to aid in helping the orphanage, fosters, and cats.

## User Stories

* As a **User**, I want to be able to *create* a new foster and assign it to my shelter.
* As a **User**, I want to be able to *create* a new cat and add it to my shelter.
* As a **User**, I want to be able to *select* a cat and view a description of the cat.
* As a **User**, I want to be able to *select* a cat at my shelter and assign it to one of it's compatible fosters.
* As a **User**, I want to be able to *remove* a cat from my shelter.
* As a **User**, I want to be able to *remove* a foster from my shelter.
* As a **User**, I want to be able to *unassign* a foster from a cat at my shelter.
* As a **User**, I want to be able to *save* my shelter's registry (if I so choose).
* As a **User**, I want to be able to *load* my shelter from a file (if I so choose).
* As a **User**, I want to be able to *view* my shelters registry of cats.
* As a **User**, I want to be able to *view* my shelters registry of fosters.

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by adding a 
cat to the Shelter using the Add Cat button after launching.
- You can generate the second required action by then adding a foster with the add foster button (that is compatible),
and assigning it to the cat with the Assign button
  (you can verify this by looking at the data folder after saving the new state if you need to).
- You can locate my visual component by running main as it is on the front page.
- You can save the state of my application by clicking the save to file button after running main.
- You can reload the state of my application by clicking the load file button after running main.

 All code design in the persistence package, aswell as the persistence test package is derived and based on the code
 from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
 The GUI observer code can be attributed to: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.