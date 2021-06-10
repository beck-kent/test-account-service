# User-Account Sample Application
Тестовое задание account-service

<h3>Извлечение и загрузка образов Docker:</h3>
<code>docker-compose up -d</code>

<h2>Список API:</h2>

<h3>Синхронный запрос для получения истории запросов</h3>
GET: <code>localhost:8087/api/v1/user-balance/history?page=0&length=10</code>

<h4>response:</h4>

``` json
{
    "timestamp": "2021-06-10T14:46:44+03:00",
    "success": true,
    "data": {
        "totalPages": 1,
        "totalElements": 3,
        "content": [
            {
                "id": 1,
                "operation_type": "ADD",
                "amount": 1200,
                "currency": "USD",
                "created_at": "2021-06-10T17:46:37+06:00"
            },
            {
                "id": 2,
                "operation_type": "ADD",
                "amount": 1200,
                "currency": "USD",
                "created_at": "2021-06-10T17:46:38+06:00"
            },
            {
                "id": 3,
                "operation_type": "SUB",
                "amount": 1200,
                "currency": "USD",
                "created_at": "2021-06-10T17:46:42+06:00"
            }
        ]
    }
}
```

<h3>Синхронный запрос для сохранения баланса</h3>
PUT: <code>localhost:8087/api/v1/user-balance/saveBalance</code>

<h4>request:</h4>

``` json
{
    "user_id": 1,
    "operation_type": "ADD",
    "currency": "USD",
    "amount": 1200
}
```

<h4>response:</h4>

``` json
{
    "timestamp": "2021-06-10T11:55:01+03:00",
    "success": true
}
```

<h3>Синхронный запрос для сохранения баланса через кафку</h3>
PUT: <code>localhost:8087/api/v1/user-balance/saveBalanceKafka</code>

<h4>request:</h4>

``` json
{
    "user_id": 1,
    "operation_type": "ADD",
    "currency": "USD",
    "amount": 1200
}
```

<h4>response:</h4>

``` json
{
    "timestamp": "2021-06-10T11:55:01+03:00",
    "success": true
}
```

<h3>Синхронный запрос для получения текущего баланса пользователя</h3>
GET: <code>localhost:8087/api/v1/user-balance/1</code>

<h4>response:</h4>

``` json
{
    "timestamp": "2021-06-10T14:47:47+03:00",
    "success": true,
    "data": {
        "id": 1,
        "balance_usd": 1200,
        "updated_at": "2021-06-10T17:46:42+06:00"
    }
}
```