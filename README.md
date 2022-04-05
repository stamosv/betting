This is a Java 11 project.

1. Move to path: /betting-project                                                     
2. Use cmd and run: mvn clean install jib:dockerBuild                                 | You may dockerize the whole project with this command
3. Move to path: /betting-project/docker-images                                       | There is a yaml file you may run to start both project and database containers
4. Use cmd and run: docker-compose -f init.yml up -d                                  | After command execution both containers are up and running
5. Use a DB IDE (e.g Datagrip) and make a new connection
   1. Host: localhost
   2. Port: 5432
   3. User: postgres
   4. Pwd:  postgres
6. Create a new database with name: 'betting'
7. Move to path: src/main/resources/dbinit/betting-project/tables.sql                 | There is a sql file you may run to DB IDE, so you may create both tables
8. Download postman collection and import to a Postman tool                           | You may start project service via exposed Rest Api.
9. Close both containers using docker-compose -f init.yml down
