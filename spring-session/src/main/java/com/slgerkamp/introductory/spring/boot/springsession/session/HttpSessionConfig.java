package com.slgerkamp.introductory.spring.boot.springsession.session;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * HttpSessionの設定をカスタマイズする。
 * 
 * EnableRedisHttpSession アノテーションは、
 * springSessionRepositoryFilterと呼ばれるSpring Beanを作成する
 * このフィルタ（filter）は、HttpSessionの実装をSpring Sessionに切り替える。
 * 
 */
@EnableRedisHttpSession
public class HttpSessionConfig {

	// cookiesではなくHTTP headersに現在のセッション情報を保持する
    @Bean
    HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

}