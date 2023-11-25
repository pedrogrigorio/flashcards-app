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

  async deleteFriend(friendId: number) {
    // const friend = await prisma.friend.delete({
    //   where: {
    //     id: friendId,
    //   },
    // })
    // return friend
  }

  async getFriendById(friendId: number) {
    // const friend = await prisma.friend.findUnique({
    //   where: {
    //     id: friendId,
    //   },
    // })
    // return friend
  }
}

export default new FriendRepository()
