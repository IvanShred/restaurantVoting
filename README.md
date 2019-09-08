[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9c079bde902a4b70863dda8c8924c2fc)](https://www.codacy.com/app/IvanShred/restaurantVoting?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=IvanShred/restaurantVoting&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/IvanShred/restaurantVoting.svg?branch=master)](https://travis-ci.org/IvanShred/restaurantVoting)

# restaurantVoting
### curl samples (application deployed in application context `restaurantVoting`)
> For windows use `Git Bash`

#### get Menu
`curl -s http://localhost:8080/restaurantVoting/rest/restaurants/menu --user user@yandex.ru:password`

#### get Meal 100014
`curl -s http://localhost:8080/restaurantVoting/rest/meals/100014 --user admin@gmail.com:admin`

#### create Meal
`curl -s -X POST -d '{"dateMeal": "2019-07-20","mealTypeId": 100006,"price": 236,"restaurantId": 100002}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurantVoting/rest/meals --user admin@gmail.com:admin`

#### delete Meal
`curl -s -X DELETE http://localhost:8080/restaurantVoting/rest/meals/100014 --user admin@gmail.com:admin`

#### update Meal
`curl -s -X PUT -d '{"dateMeal": "2019-07-25","mealTypeId": 100010,"price": 535,"restaurantId": 100003}' -H 'Content-Type: application/json' http://localhost:8080/restaurantVoting/rest/meals/100014 --user admin@gmail.com:admin`

#### get Meals by restaurantId
`curl -s http://localhost:8080/restaurantVoting/rest/meals/by?restaurantId=100003 --user admin@gmail.com:admin`

#### get Vote
`curl -s http://localhost:8080/restaurantVoting/rest/vote --user user@yandex.ru:password`

#### vote for Restaurant
`curl -s -X POST -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurantVoting/rest/restaurants/100003/vote --user admin@gmail.com:admin`

#### get Restaurant 100002
`curl -s http://localhost:8080/restaurantVoting/rest/restaurants/100002 --user admin@gmail.com:admin`

#### create Restaurant
`curl -s -X POST -d '{"name": "Новые ресторан", "address": "ул. Новая, 5"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurantVoting/rest/restaurants --user admin@gmail.com:admin`

#### delete Restaurant
`curl -s -X DELETE http://localhost:8080/restaurantVoting/rest/restaurants/100002 --user admin@gmail.com:admin`

#### update Restaurant
`curl -s -X PUT -d '{"id": 100002, "name": "Обновленный ресторан", "address": "ул. Обновленная, 3"}' -H 'Content-Type: application/json' http://localhost:8080/restaurantVoting/rest/restaurants/100002 --user admin@gmail.com:admin`

#### get Restaurants
`curl -s http://localhost:8080/restaurantVoting/rest/restaurants --user admin@gmail.com:admin`

#### get Restaurant 100002 with Meals
`curl -s http://localhost:8080/restaurantVoting/rest/restaurants/100002/menu --user admin@gmail.com:admin`