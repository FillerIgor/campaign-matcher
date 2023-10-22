# Simple Current Campaign Checker for Profile

# Known issues

- Foreign keys for Clan and Device tables are not saved. This implies devices and clan information impossible to retrieve
- Due to lack of time ProfileExceptionHandler Controller Advice needs to be updated
- Some todo's are left to demonstrate intention of best practices. Unfortunately this is still work in progress due to lack of time
- CampaignDatesValidator is disabled to demonstrate positive user scenario
- Flyway or Liquibase or sql scripts run should be implemented to fill the DB with initial data
- Lack of logs

# Assumptions

- Campaign dates should be part of validation
- Relationship between Profile and PlayerID is One to One for the sake of simplicity

# Pre requested actions

- After the start up of application please execute (Profile request body example from the task):

curl -X POST --location "http://localhost:8080/profiles" \
-H "Content-Type: application/json" \
-d "{
\"player_id\": \"97983be2-98b7-11e7-90cf-082e5f28d836\",
\"credential\": \"apple_credential\",
\"created\": \"2021-01-10 13:37:17Z\",
\"modified\": \"2021-01-23 13:37:17Z\",
\"last_session\": \"2021-01-23 13:37:17Z\",
\"total_spent\": 400,
\"total_refund\": 0,
\"total_transactions\": 5,
\"last_purchase\": \"2021-01-22 13:37:17Z\",
\"active_campaigns\": [],
\"devices\": [
{
\"model\": \"apple iphone 11\",
\"carrier\": \"vodafone\",
\"firmware\": \"123\"
}
],
\"level\": 3,
\"xp\": 1000,
\"total_playtime\": 144,
\"country\": \"CA\",
\"language\": \"fr\",
\"birthdate\": \"2000-01-10 13:37:17Z\",
\"gender\": \"male\",
\"inventory\": {
\"cash\": 123,
\"coins\": 123,
\"item_1\": 1,
\"item_34\": 3,
\"item_55\": 2
},
\"clan\": {
\"name\": \"Hello world clan\"
},
\"_customfield\": \"mycustom\"
}"



