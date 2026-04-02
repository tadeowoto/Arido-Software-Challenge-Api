package com.arido.manufacturing_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "User_Security")
@AllArgsConstructor
@NoArgsConstructor
public class UserSecurity {


    @EmbeddedId
    private UserSecurityKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private SecurityGroup group;

    @ManyToOne
    @JoinColumn(name = "access_level_id")
    private AccessLevel accessLevel;

}
