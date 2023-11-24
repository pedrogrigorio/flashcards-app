import { prisma } from '../lib/prisma'

class FriendRepository {
  async deleteFriend(friendId: number) {
    const friend = await prisma.friend.delete({
      where: {
        id: friendId,
      },
    })

    return friend
  }

  async getFriendById(friendId: number) {
    const friend = await prisma.friend.findUnique({
      where: {
        id: friendId,
      },
    })

    return friend
  }
}

export default new FriendRepository()
