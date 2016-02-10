package orwashere.springboot;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by fdirlikl on 1/25/2016.
 */
@RelationshipEntity(type = "FOLLOWS")
public class Follows {

    @GraphId
    private Integer id;

    @StartNode
    private TwitterProfile profileOutgoing;

    @EndNode
    private TwitterProfile profileIncoming;

    public Follows(TwitterProfile profileOutgoing, TwitterProfile profileIncoming) {
        this.profileOutgoing = profileOutgoing;
        this.profileIncoming = profileIncoming;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TwitterProfile getProfileOutgoing() {
        return profileOutgoing;
    }

    public void setProfileOutgoing(TwitterProfile profileOutgoing) {
        this.profileOutgoing = profileOutgoing;
    }

    public TwitterProfile getProfileIncoming() {
        return profileIncoming;
    }

    public void setProfileIncoming(TwitterProfile profileIncoming) {
        this.profileIncoming = profileIncoming;
    }
}
