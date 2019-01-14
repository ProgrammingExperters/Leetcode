package org.ggq.com.code;

/**
 * 人们会互相发送好友请求，现在给定一个包含有他们年龄的数组，ages[i] 表示第 i 个人的年龄。
 * <p>
 * 当满足以下条件时，A 不能给 B（A、B不为同一人）发送好友请求：
 * <p>
 * numberB <= 0.5 * numberA + 7
 * numberB > numberA
 * numberB > 100 && numberA < 100
 * 否则，A 可以给 B 发送好友请求。
 * <p>
 * 注意如果 A 向 B 发出了请求，不等于 B 接受了 A 的请求。而且，人们不会给自己发送好友请求。
 * <p>
 * 求总共会发出多少份好友请求?
 *
 * @author guoqingg_1 Date: 2018-11-30 Time: 20:23
 * @version $Id$
 */
public class FriendsAges {

    public static void main(String[] args) {
        int[] temp = {16,16};
        FriendsAges friendsAges = new FriendsAges();
        int result = friendsAges.numFriendRequests(temp);
        System.out.println(result);
    }

    // 可以发送请求
    public Boolean requestFriend(int numberA, int numberB) {

        if (numberB > 100 && numberA < 100) {
            return false;
        }

        if (numberB > numberA) {
            return false;
        }

        if (numberB <= 0.5 * numberA + 7) {
            return false;
        }

        return true;
    }

    public int numFriendRequests(int[] ages) {

        int result = 0;

        // 遍历
        for (int i = 0; i < ages.length; i++) {
            for (int j = 0; j < ages.length; j++) {
                if (i == j) {
                    continue;
                }
                if (requestFriend(ages[i], ages[j])) {
                    result++;
                }
            }
        }

        return result;
    }
}
