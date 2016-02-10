package orwashere.springboot;

import org.springframework.data.neo4j.repository.GraphRepository;
import orwashere.springboot.Follows;

/**
 * Created by fdirlikl on 1/25/2016.
 */
public interface FollowsRepository extends GraphRepository<Follows> {
}
