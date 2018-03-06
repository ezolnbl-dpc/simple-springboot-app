Spring Boot Test App
- Create a Spring Boot application
- Build with Gradle
- Create two html pages
- Index page with input fields
- first name (text)
- last name (text)
- country (drop down)
- date (date picker)
- submit button: On submit, show the CV page
- CV page: showing the candidate's CV file or inline HTML5
- At the top of the page, display the data from the form
- No validation is required

run with custom jdk
./gradlew cle bootRun -i -Dorg.gradle.java.home="PATH_TO_JDK"

./gradlew cle ass -i && java -jar ./build/libs/newpush-0.0.1-SNAPSHOT.jar

test data
'Kov�cs', 'B�la', 'Hungary', '2018-02-06'
'Nagy', 'Ferenc', 'Germany', '2018-03-06'
'Kis', 'J�nos', 'Ukraine', '2018-04-06'