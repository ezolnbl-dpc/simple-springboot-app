Spring Boot Test App
- Create a Spring Boot application (runnable jar, Java 9):
- Build with Gradle,
- Create two html pages:
- Index page with input fields
- first name (text),
- last name (text),
- country (drop down),
- date (date picker),
- submit button: On submit, show the CV page;
- CV page: showing the candidate's CV file or inline HTML5;
- At the top of the page, display the data from the form,
- No validation is required.

n�lam java8 volt telep�tve �gy ha command line szeretn�nk futtatni akkor ezzel a paranccsal lehet, amennyiben nem 8as java van telep�tve:
./gradlew cle bootRun -i -Dorg.gradle.java.home="PATH_TO_JDK"

vagy az IDE-ben kell be�ll�tani defult compilernek a 9es java-t a projecthez �s run as -> java application

ha esetleg jart szeretne csin�lni valaki
./gradlew cle ass -i && java -jar ./build/libs/newpush-0.0.1-SNAPSHOT.jar (ha 9es a default java a g�pen)

test adatok amikhez tartozik cv:
'Kov�cs', 'B�la', 'Hungary', '2018-02-06'
'Nagy', 'Ferenc', 'Germany', '2018-03-06'
'Kis', 'J�nos', 'Ukraine', '2018-04-06'