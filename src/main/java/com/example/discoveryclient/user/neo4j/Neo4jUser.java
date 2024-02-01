package com.example.discoveryclient.user.neo4j;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
@Getter
@Setter
public class Neo4jUser {

    @Id // Marks this field as the identifier of the node
    private Long id;

    private String name;
    private String email;

    // Defines a relationship. Assuming FRIENDS_WITH is a relationship type in your graph.
    @Relationship(type = "FRIENDS_WITH", direction = Relationship.Direction.OUTGOING)
    private Set<Neo4jUser> friends;

    public Neo4jUser() {
        this.friends = new HashSet<>();
    }

    public void addFriend(Neo4jUser user) {
        friends.add(user);
    }
}
