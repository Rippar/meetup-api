Meetup launching:
1) Download this project to your local machine: ``` $ git clone https://github.com/Rippar/meetup-api ```

2) Run ```$ mvn install``` from the _root_ folder of the project

3) Then there are two ways:
* 1st way:
   * Set up all the environment variables in ```.env``` file
   * Run ```$ docker compose up```
   * Start to send requests to the endpoints 

* 2nd way:
   * Set up all the necessary properties in _resources/application.properties_
   * Run ```$ java -jar meetup-api-0.0.1-SNAPSHOT.jar``` in _target_ folder
   * Start to send requests to the endpoints



