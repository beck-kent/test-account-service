# User-Account Sample Application
Тестовое задание account-service

<h2>Извлечение и загрузка образов Docker:</h2>
<code>docker-compose up -d</code>

<h2>Список API:</h2>

<h3>GET: Синхронный запрос для получения истории запросов</h3>
<code>localhost:8087/api/v1/user-balance/history?page=0&length=10</code>

<h3>PUT: Синхронный запрос для сохранения баланса</h3>
<code>localhost:8087/api/v1/user-balance/saveBalance</code>

<h4>request:</h4>
<code>
{
    "user_id": 1,
    "operation_type": "ADD",
    "currency": "USD",
    "amount": 1200
}
</code>

<h4>response:</h4>
<code>
{
    "timestamp": "2021-06-10T11:55:01+03:00",
    "success": true
}
</code>

<h3>PUT: Синхронный запрос для сохранения баланса через кафку</h3>
<code>localhost:8087/api/v1/user-balance/saveBalanceKafka</code>

<h4>request:</h4>
<code>
{
    "user_id": 1,
    "operation_type": "ADD",
    "currency": "USD",
    "amount": 1200
}
</code>

<h4>response:</h4>
<code>
{
    "timestamp": "2021-06-10T11:55:01+03:00",
    "success": true
}
</code>

<h3>GET: Синхронный запрос для получения текущего баланса пользователя</h3>
<code>localhost:8087/api/v1/user-balance/1</code>