package users;

public interface FollowingUserResultListener {
    void successfullyFollowed(String followed, String follower);
}
