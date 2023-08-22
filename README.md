# postDemo
api for posting and messaging

This API is only for learning purpose.
POST: http://localhost:8080/message/schedule
API Sample Request:
{
    "message" : "Testing Scheduler 15/08/2023 14:45!",
    "time" : "14:45:30",
    "senderId" : "db2c8b2f-d108-4edf-a639-96d62c5f571e",
    "receiverId" : "db2c8b2f-d108-4edf-a639-96d62c5f571e",
    "isScheduled" : "true"
}

using **isScheduled** for scheduling the message published to message broker. **time** format need to be HH:MM:SS

Client can absorb message from kafka.
