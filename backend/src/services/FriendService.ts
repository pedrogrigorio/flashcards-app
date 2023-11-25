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

  async deleteFriend(friendId: number) {
    return await FriendRepository.deleteFriend(friendId)
  }

  async getFriendById(friendId: number) {
    return await FriendRepository.getFriendById(friendId)
  }
}

export default new FriendService()
