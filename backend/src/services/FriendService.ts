import FriendRepository from '../repositories/FriendRepository'
import UserService from './UserService'

class FriendService {
  async addFriend(userId: number, newFriendId: number) {
    const user = await UserService.getUser(userId)
    const newFriend = await UserService.getUser(newFriendId)

    if (user && newFriend) {
      const friendForward = await FriendRepository.createFriend(newFriend, user)
      const friendBackward = await FriendRepository.createFriend(
        user,
        newFriend,
      )

      return friendForward
    }
  }

  async getFriend(friendId: number, userId: number) {
    return await FriendRepository.getFriendById(friendId, userId)
  }

  async deleteFriend(friendId: number, userId: number) {
    return await FriendRepository.deleteFriend(friendId, userId)
  }
}

export default new FriendService()
