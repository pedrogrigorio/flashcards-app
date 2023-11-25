import { Request, Response } from 'express'
import FriendService from '../services/FriendService'

class FriendController {
  async addFriend(req: Request, res: Response) {
    const { newFriendId } = req.body
    try {
      // send friend request notification
    } catch (error) {
      return res.status(400).json(error)
    }

    return res.status(400).json({ to_do: 'TO DO' })
  }

  async deleteFriend(req: Request, res: Response) {
    const friendId = parseInt(req.params.id)

    try {
      const deletedFriend = await FriendService.deleteFriend(friendId)

      return res.json(deletedFriend)
    } catch (error) {
      return res.status(400).json(error)
    }
  }

  async getFriendById(req: Request, res: Response) {
    const friendId = parseInt(req.params.id)

    try {
      const friend = await FriendService.getFriendById(friendId)

      return res.json(friend)
    } catch (error) {
      return res.status(400).json(error)
    }
  }
}

export default new FriendController()
