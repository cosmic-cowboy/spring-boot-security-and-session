# spring-boot-security-and-session
Spring Bootでの認証・認可・セッションの動作を確認する

## spring-sessionプロジェクト
https://github.com/cosmic-cowboy/spring-boot-security-and-session/tree/master/spring-session


### Requirements

redisのインストールと起動

http://redis.io/
```
$ redis-server /usr/local/etc/redis.conf
```

java,maven

### Running

```
$ mvn spring-boot:run
```

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
