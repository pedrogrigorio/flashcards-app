import { Request, Response } from 'express'
import FriendService from '../services/FriendService'
import { handleError } from '../utils/errorHandler'

class FriendController {
  async getAllFriends(req: Request, res: Response) {
    try {
      const userId = parseInt(req.userId)
      const friends = await FriendService.getAllFriends(userId)

      return res.json(friends)
    } catch (error) {
      handleError(res, error)
    }
  }

  async getFriend(req: Request, res: Response) {
    try {
      const friendId = parseInt(req.params.id)
      const userId = parseInt(req.userId)

      const friend = await FriendService.getFriend(friendId, userId)

      return res.json(friend)
    } catch (error) {
      handleError(res, error)
    }
  }

  async deleteFriend(req: Request, res: Response) {
    try {
      const friendId = parseInt(req.params.id)
      const userId = parseInt(req.userId)

      const deletedFriend = await FriendService.deleteFriend(friendId, userId)

      return res.json(deletedFriend)
    } catch (error) {
      handleError(res, error)
    }
  }
}

export default new FriendController()
