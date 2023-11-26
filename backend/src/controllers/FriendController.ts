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

      const deletedFriendForward = await FriendService.deleteFriend(
        friendId,
        userId,
      )

      const deletedFriendBackward = await FriendService.deleteFriend(
        userId,
        friendId,
      )

      return res.json(deletedFriendForward)
    } catch (error) {
      handleError(res, error)
    }
  }
}

export default new FriendController()
