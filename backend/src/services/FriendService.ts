import FriendRepository from '../repositories/FriendRepository'
import UserService from './UserService'

class FriendService {
  async addFriend(userId: number, newFriendId: number) {
    const user = await UserService.getUser(userId)
    const newFriend = await UserService.getUser(newFriendId)

    if (!user || !newFriend) {
      throw new Error('User not found')
    }

    const friendForward = await FriendRepository.createFriend(newFriend, user)
    const friendBackward = await FriendRepository.createFriend(user, newFriend)

    return [friendForward, friendBackward]
  }

  async getAllFriends(userId: number) {
    return await FriendRepository.getAllFriends(userId)
  }

  async getFriend(friendId: number, userId: number) {
    const friend = await FriendRepository.getFriendById(friendId, userId)

    if (!friend) {
      throw new Error('Friend not found.')
    }

    return friend
  }

  async deleteFriend(friendId: number, userId: number) {
    const friend = await FriendRepository.getFriendById(friendId, userId)

    if (!friend) {
      throw new Error('Friend not found.')
    }

    const deletedFriendForward = await FriendRepository.deleteFriend(
      friendId,
      userId,
    )

    const deletedFriendBackward = await FriendRepository.deleteFriend(
      userId,
      friendId,
    )

    return [deletedFriendForward, deletedFriendBackward]
  }
}

export default new FriendService()
