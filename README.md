# IguanaFix Reseller API Authentication Examples

This project holds code examples on how to generate a one time password for IguanaFix's Reseller API in multiple programming languages.
You can find the API documentation [here](https://iguanafixresellerapi.docs.apiary.io).

In pseudocode the password generation can be implemented as follows:

```
plainPassword = clientId + “$” + currentTimeInMillis + “$” + apiSecret
encryptedPassword = encrypt(“AES”, “AES/ECB/PKCS5Padding”, apiKey, plainPassword)
password = hexEncode(encryptedPassword)
```

As an alternative to implementing this, a developer can make an HTTP call to the following endpoint:

```
GET /external/api/reseller/rest/v1/login/<CUSTOMER_ID>/<API_KEY>
```
This will return a new token that is valid for 10 minutes.
