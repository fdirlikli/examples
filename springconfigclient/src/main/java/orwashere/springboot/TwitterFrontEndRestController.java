package orwashere.springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.stream.Collectors;

/**
 * Created by fdirlikl on 1/28/2016.
 */
@RestController
@RequestMapping("/twitter")
@EnableRabbit
class TwitterFrontEndRestController {

    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    private TwitterProfileRepository profileRepository;



    @Inject
    public TwitterFrontEndRestController(Twitter twitter, ConnectionRepository connectionRepository, TwitterProfileRepository profileRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
        this.profileRepository = profileRepository;
    }

    @RequestMapping(method= RequestMethod.GET, produces = "application/json")
    @Transactional
    public CursoredList<Long> helloTwitter(@RequestParam(name = "profileName") String profileName) {

        org.springframework.social.twitter.api.TwitterProfile twitProfile = twitter.userOperations().getUserProfile(profileName);
        CursoredList<Long> followers = twitter.friendOperations().getFollowerIds(profileName);
        CursoredList<Long> friends = twitter.friendOperations().getFriendIds(profileName);
        orwashere.springboot.TwitterProfile profile = new orwashere.springboot.TwitterProfile(twitProfile.getId(),twitProfile.getName());
        profile.setFollowers(followers.stream().map(id -> new orwashere.springboot.TwitterProfile(id, null)).collect(Collectors.toList()));
        profile.setFriends(friends.stream().map(id-> new orwashere.springboot.TwitterProfile(id,null)).collect(Collectors.toList()));
        profileRepository.save(profile,0);
        profileRepository.saveFollowers(profile.getFollowers(),profile.getTwitterId());
        profileRepository.saveFriends(profile.getFriends(),profile.getTwitterId());
        //List<Tweet> tweetList = twitter.timelineOperations().getUserTimeline("kennybastani", 200);
        //List<Tweet> filtered = tweetList.stream().filter(t -> t.getText().contains("boot")).collect(Collectors.toList());

        return followers;
    }

}