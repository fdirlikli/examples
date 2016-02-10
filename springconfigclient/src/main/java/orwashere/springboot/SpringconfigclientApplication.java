package orwashere.springboot;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableSocial
public class SpringconfigclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringconfigclientApplication.class, args);
	}
}

@Configuration
@EnableTransactionManagement
class SocialConfiguration extends Neo4jConfiguration {

	@Bean
	public Neo4jServer neo4jServer() {
		return new RemoteServer("http://localhost:7474","neo4j","morinek1983");
	}

	@Bean
	public SessionFactory getSessionFactory() {
		// with domain entity base package(s)
		return new SessionFactory("orwashere.springboot");
	}

	// needed for session in view in web-applications
	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Session getSession() throws Exception {
		return super.getSession();
	}

}

