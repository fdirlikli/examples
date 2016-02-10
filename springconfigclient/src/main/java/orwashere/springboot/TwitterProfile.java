package orwashere.springboot;

import org.neo4j.ogm.annotation.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by fdirlikl on 1/21/2016.
 */
@NodeEntity
public class TwitterProfile {

    @GraphId
    private Long id;

    @Property
    @Index(unique = true)
    private Long twitterId;

    @Property
    private String profileName;

    @Relationship(type="FOLLOWS", direction = Relationship.INCOMING)
    private List<TwitterProfile> followers;

    @Relationship(type = "FOLLOWS", direction = Relationship.OUTGOING)
    private List<TwitterProfile> friends;

    public TwitterProfile(Long twitterId, String profileName) {
        this.twitterId = twitterId;
        this.profileName = profileName;
    }

    public TwitterProfile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(Long twitterId) {
        this.twitterId = twitterId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public List<TwitterProfile> getFollowers() {
        return followers;
    }

    public void setFollowers(List<TwitterProfile> followers) {
        this.followers = followers;
    }

    public List<TwitterProfile> getFriends() {
        return friends;
    }

    public void setFriends(List<TwitterProfile> friends) {
        this.friends = friends;
    }
}
