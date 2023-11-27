import { prisma } from '../lib/prisma'

interface Friend {
  id: number
  username: string
  name: string
  imgSrc: string
  dayStreak: number
  cardsReviewed: number
}

class FriendRepository {
  async createFriend(friend: Friend, friendOf: Friend) {
    const newFriend = await prisma.friend.create({
      data: {
        userId: friend.id,
        friendOfId: friendOf.id,
        name: friend.name,
        username: friend.username,
        imgSrc: friend.imgSrc,
        dayStreak: friend.dayStreak,
        cardsReviewed: friend.cardsReviewed,
      },
    })

    return newFriend
  }

  async getAllFriends(friendOfId: number) {
    const friends = await prisma.friend.findMany({
      where: {
        friendOfId,
      },
    })

    return friends
  }

  async getFriendById(friendId: number, friendOfId: number) {
    const friend = await prisma.friend.findUnique({
      where: {
        userId_friendOfId: {
          userId: friendId,
          friendOfId,
        },
      },
    })

    return friend
  }

  async deleteFriend(friendId: number, friendOfId: number) {
    const friend = await prisma.friend.delete({
      where: {
        userId_friendOfId: {
          userId: friendId,
          friendOfId,
        },
      },
    })

    return friend
  }
}

export default new FriendRepository()
