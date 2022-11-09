### Meetup launching
1) Download this project to your local machine: ``` $ git clone https://github.com/Rippar/meetup-api ```

2) Then there are two ways:
* 1st way (launch in Docker):
   * Run ```$ mvn install``` from the _root_ folder of the downloaded project
   * Set up all the environment variables in ```.env``` file
   * Run ```$ docker compose up```
   * Start to send requests to the endpoints 

* 2nd way (launch locally):
   * Set up all the necessary properties in _resources/application.properties_
   * Run ```$ mvn install``` from the _root_ folder of the project
   * Run ```$ java -jar meetup-api-0.0.1-SNAPSHOT.jar``` in _target_ folder
   * Start to send requests to the endpoints

DB structure can be found in ```resources/db/changelog/2022-10-22/1-create-table.yaml```

### Endpoints

![endpoints](https://user-images.githubusercontent.com/61383438/200791632-2bdd88b9-670c-42e7-914f-9a1f29c3a6a3.png)

*```/api/meetups/getFiltered``` endpoint you can use by combining any of these parameters: _topic_, _organizer_, _time_ and set the sorting order by adding _orderby_ parameter. For example: ```/api/meetups/getFiltered?topic=Java Conference``` or ```api/meetups/getFiltered?topic=Java Conference&organizer=Spring community``` or ```api/meetups/getFiltered?time=2022-11-23 10:00&orderby=location``` or just ```/api/meetups/getFiltered?orderby=time``` . You can use all the params or none.