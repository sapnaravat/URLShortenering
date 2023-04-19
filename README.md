# URLShortenering

Project Build
To build this project, run

git clone https://github.com/zeeshaanahmad/url-shortener.git
cd url-shortener
gradle clean build


API Endpoints
You can access following API endpoints at http://localhost:8080

POST /shorten
It takes a JSON object in the following format as payload

{
  "fullUrl":"<The URL to be shortened>"
}
cURL
curl -X POST \
  http://localhost:8080/shorten \
  -H 'Content-Type: application/json' \
  -d '{"fullUrl":"https://example.com/example/1"}'
Response:

{
  "shortUrl": "<shortened url for the fullUrl provided in the request payload>"
}
Please note that API works only with valid HTTP or HTTPS Urls. In case of malformed Url, it returns 400 Bad Request error with response body containing a JSON object in the following format

{
  "field":"fullUrl",
  "value":"<Malformed Url provided in the request>",
  "message":"<Exception message>"
}
	
	GET /<shortened_text>
This endpoint redirects to the corresponding fullUrl.

GET /actuator/health
Included the spring boot actuator dependency for API metrics. You can try this endpoint for health checks.

cURL
curl -X GET   http://localhost:8080/actuator/health
	
	
	
	
