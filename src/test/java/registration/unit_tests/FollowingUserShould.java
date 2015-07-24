package registration.unit_tests;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import users.*;

public class FollowingUserShould {
    private RegisteredUsers registeredUsers;
    private FollowingUserResultListener resultListener;

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void make_a_registered_user_follow_another_registered_user(){
        final String followed = "userA";
        final String follower = "userB";
        registeredUsers = context.mock(RegisteredUsers.class);
        resultListener = context.mock(FollowingUserResultListener.class);
        FollowingUser followingUser = new FollowingUser(registeredUsers);
        followingUser.tellResultTo(resultListener);

        context.checking(new Expectations() {{
            oneOf(registeredUsers).addFollower(new User(followed), new User(follower));
            oneOf(resultListener).successfullyFollowed(followed, follower);
        }});

        followingUser.follow(followed, follower);
    }


}
