package orwashere.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.stream.Collectors;

/**
 * Created by fdirlikl on 1/28/2016.
 */
@Component
public class TwitterCrawlers {

    private Twitter twitter;
    private ConnectionRepository connectionRepository;
    private TwitterProfileRepository profileRepository;
    private AmqpTemplate amqpTemplate;
    private ObjectMapper objectMapper;



    @Inject
    public TwitterCrawlers(Twitter twitter, ConnectionRepository connectionRepository, TwitterProfileRepository profileRepository,AmqpTemplate amqpTemplate,ObjectMapper mapper) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
        this.profileRepository = profileRepository;
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = mapper;
    }

    @RabbitListener(queues = "newTwitterProfileQueue")
    public void crawlNewUser(String message)
    {
        TwitterProfile profile = null;
        try
        {
            profile = objectMapper.readValue(message, TwitterProfile.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        org.springframework.social.twitter.api.TwitterProfile twitProfile = twitter.userOperations().getUserProfile(profile.getProfileName());
        profile.setTwitterId(twitProfile.getId());
        profileRepository.save(profile,0);
        amqpTemplate.convertAndSend("followersQueue",profile);
    }

    @RabbitListener(queues = "followersQueue")
    public void crawlFollowers(String message)
    {
        TwitterProfile profile = null;
        try
        {
            profile = objectMapper.readValue(message, TwitterProfile.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        CursoredList<Long> followers = twitter.friendOperations().getFollowerIds(profile.getProfileName());
        profileRepository.saveFollowers(profile.getFollowers(),profile.getTwitterId());
        amqpTemplate.convertAndSend("friendsQueue",profile);
    }

    @RabbitListener(queues = "friendsQueue")
    public void crawlFriends(String message)
    {
        TwitterProfile profile = null;
        try
        {
            profile = objectMapper.readValue(message, TwitterProfile.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        CursoredList<Long> friends = twitter.friendOperations().getFriendIds(profile.getProfileName());
        profileRepository.saveFriends(profile.getFriends(),profile.getTwitterId());
        amqpTemplate.convertAndSend("twitsQueue",profile);
    }

    @RabbitListener(queues = "twitsQueue")
    public void crawlTwits()
    {
        //List<Tweet> tweetList = twitter.timelineOperations().getUserTimeline("kennybastani", 200);
        //List<Tweet> filtered = tweetList.stream().filter(t -> t.getText().contains("boot")).collect(Collectors.toList());
    }

}
