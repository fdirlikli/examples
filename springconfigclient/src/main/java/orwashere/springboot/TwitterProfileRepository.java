package orwashere.springboot;


import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by fdirlikl on 1/21/2016.
 */

public interface TwitterProfileRepository  extends GraphRepository<TwitterProfile> {

    @Query("FOREACH(x in {profiles} | MERGE (a:TwitterProfile { twitterId: x.twitterId })\n" +
            "MERGE (b:TwitterProfile { twitterId: {profileId} })\n" +
            "MERGE (a)-[:FOLLOWS]->(b))")
    List<TwitterProfile> saveFollowers(@Param("profiles") List<TwitterProfile> profiles, @Param("profileId") Long targetProfile);

    @Query("FOREACH(x in {profiles} | MERGE (a:TwitterProfile { twitterId: x.twitterId })\n" +
            "MERGE (b:TwitterProfile { twitterId: {profileId} })\n" +
            "MERGE (b)-[:FOLLOWS]->(a))")
    List<TwitterProfile> saveFriends(@Param("profiles") List<TwitterProfile> profiles,@Param("profileId") Long twitterId);
}
