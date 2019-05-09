[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9c079bde902a4b70863dda8c8924c2fc)](https://www.codacy.com/app/IvanShred/restaurantVoting?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=IvanShred/restaurantVoting&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/IvanShred/restaurantVoting.svg?branch=master)](https://travis-ci.org/IvanShred/restaurantVoting)

# restaurantVoting
SoapUi file in the project root 
### curl samples (application deployed in application context `restaurantVoting`).
> For windows use `Git Bash`

#### get menu
`curl -s http://localhost:8080/restaurantVoting/rest/menu --user user@yandex.ru:password`

#### get Meals by restaurantId
`curl -s http://localhost:8080/restaurantVoting/rest/meals/by?restaurantId=100003 --user admin@gmail.com:admin`

#### get Meals 100008
`curl -s http://localhost:8080/restaurantVoting/rest/meals/100008 --user admin@gmail.com:admin`

#### update Meals
`curl -s -X PUT -d '{"dateMeal": "2019-05-05","description": "Обновленная еда","price": 70,"restaurantId": 100003}' -H 'Content-Type: application/json' http://localhost:8080/restaurantVoting/rest/meals/100008 --user admin@gmail.com:admin`

#### create Meals
`curl -s -X POST -d '{"dateMeal": "2019-05-05","description": "Новая еда","price": 70,"restaurantId": 100003}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurantVoting/rest/meals --user admin@gmail.com:admin`

#### delete Meals
`curl -s -X DELETE http://localhost:8080/restaurantVoting/rest/meals/100008 --user admin@gmail.com:admin`

#### vote for Restaurant
`curl -s -X PUT -H "Content-Type: application/json" http://localhost:8080/restaurantVoting/rest/vote/100003 --user admin@gmail.com:admin`

#### cancel Vote
`curl -s -X DELETE http://localhost:8080/restaurantVoting/rest/vote --user user@yandex.ru:password`

