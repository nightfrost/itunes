# itunes prototype
Look-a-like itunes prototype, focusing on API endpoints. The application is built on Spring with Maven. It supports Thymeleaf and API endpoints.

The application can be found on Heroku:
[Prototype on Heroku](https://lit-island-36772.herokuapp.com/)

The application comes with a postman collection, testing the Heroku endpoints. The collection can be found in resources.


## Thymeleaf:

Thymeleaf is a part of the application, and can be found at the root path.
The thymeleaf site has:
1. 5 random tracks
2. 5 random genres
3. 5 random artists
4. A Search bar for searching for songs.

## API Endpoints:

The application responds to severel different API calls. They can be found in /api/
The following endpoints exist:
1. Read all customers (GET /api/customers)
2. Read specific customer (GET /api/customers/X)
3. Update an existing customer (PUT /api/customers/X)
4. List countries by most customers ( GET /api/customers/countries)
5. List customers by highest spending ( GET /api/customers/spenders)
6. Most popular genre for specific customer ( GET /api/customers/X/genre)
7. Add customer (POST /api/customers)
