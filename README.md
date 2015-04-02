## Spring Bootでの認証・認可・セッションの動作を確認する


## spring-boot-form-and-basic-authプロジェクト

https://github.com/cosmic-cowboy/spring-boot-security-and-session/tree/master/spring-boot-form-and-basic-auth

### 目的

basic認証とフォーム認証を併用する（Multiple HttpSecurity）動作を確認する

http://docs.spring.io/spring-security/site/docs/4.0.0.RELEASE/reference/htmlsingle/#multiple-httpsecurity

### basic認証（API）
ログイン
```
$ curl -v -X GET "http://localhost:8080/api/login" -u user01:{password}
```

WebSocket通信
```
$ curl -v "http://localhost:8080/api/echo" -N -u user01:{password} -H "Connection: Upgrade" -H "Upgrade: websocket" -H "Sec-WebSocket-Version: 13" -H "Sec-WebSocket-Key: `perl -MMIME::Base64 -e 'print encode_base64("13-hogehgeo")'`"
```


### フォーム認証（webページ）
ログインフォーム
```
http://localhost:8080/admin/loginForm
```


## spring-sessionプロジェクト

https://github.com/cosmic-cowboy/spring-boot-security-and-session/tree/master/spring-session

### 目的

Spring Sessionの動作を確認する

http://projects.spring.io/spring-session/

### APIs
ログイン（x-auth）
```
$ curl -v -X GET "http://localhost:8080/" -u user01:{password}
```
ログアウト（x-auth）
```
$ curl -v -H 'x-auth-token:{token}' http://localhost:8080/logout
```
ブックマーク
```
$ curl -v -H 'x-auth-token:{token}' http://localhost:8080/bookmark
```

## 各プロジェクト共通

### Requirements

redisのインストールと起動（spring-sessionプロジェクトで使用）

http://redis.io/
```
$ redis-server /usr/local/etc/redis.conf
```

java,maven

### Running

```
$ mvn spring-boot:run
```

### Debug

Eclipseなどを使って5005ポートでリモート・デバッグしてください。

```
$ mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
```
