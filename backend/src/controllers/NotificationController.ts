import { Request, Response } from 'express'
import NotificationService from '../services/NotificationService'

class NotificationController {
  async sendFriendRequest(req: Request, res: Response) {
    const { senderId, receiverId } = req.body

    const notification = await NotificationService.sendFriendRequest(
      receiverId,
      senderId,
    )

    res.json(notification)
  }

  async acceptFriendRequest(req: Request, res: Response) {
    const notificationId = parseInt(req.params.id)

    const response =
      await NotificationService.acceptFriendRequest(notificationId)
    res.json(response)
  }
}

export default new NotificationController()
