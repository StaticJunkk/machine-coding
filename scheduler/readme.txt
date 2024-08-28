How to run the code?
These are the pre-requisites:
1. Have mysql installed on local
2. Have java installed on local
3. An IDE installed on local (Intellj)
4. Gradle installed on local

Steps to run the code
1. first run the scheduler/src/main/resources/init.sql file to bootstrap the db
2. run gradle clean build
3. run java -jar build/libs/scheduler-0.0.1-SNAPSHOT.jar server application.yml





cURLs
1. Create appointment
curl --location 'localhost:8080/api/appointments/create' \
--header 'Content-Type: application/json' \
--data '{
    "service_provider_id": 1,
    "slot_id": 1,
    "start_date_time": "2024-08-28T18:30",
    "user_id": 789
}'


2. Update appointment (RESCHEDULE)

curl --location --request PUT 'localhost:8080/api/appointments/update' \
--header 'Content-Type: application/json' \
--data '{
    "appointment_id": 1,
    "service_provider_id": 1,
    "new_slot_id": 1,
    "start_date_time": "2024-08-28T14:30",
    "modification_type": "RESCHEDULE"
}'

3. Update appointment (CANCEL)
curl --location --request PUT 'localhost:8080/api/appointments/update' \
--header 'Content-Type: application/json' \
--data '{
    "appointment_id": 2,
    "modification_type": "CANCEL"
}'

4. Fetch Appointments
curl --location 'localhost:8080/api/appointments/CAR_SERVICE/providers/1/appointments/'

5. Fetch Availability for single service provider
curl --location 'localhost:8080/api/appointments/CAR_SERVICE/providers/1/availability/'

6. Fetch Availability for all
curl --location 'localhost:8080/api/appointments/CAR_SERVICE/providers/availability/'
