import FriendRepository from '../repositories/FriendRepository'

class FriendService {
  async addFriend() {
    return null
  }

  async acceptFriendRequest() {
    return null
  }

  async deleteFriend(friendId: number) {
    return await FriendRepository.deleteFriend(friendId)
  }

  async getFriendById(friendId: number) {
    return await FriendRepository.getFriendById(friendId)
  }
}

export default new FriendService()
