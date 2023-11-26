import { Request, Response } from 'express'
import NotificationService from '../services/NotificationService'
import * as Validators from '../validators/notification'
import { handleError } from '../utils/errorHandler'

class NotificationController {
  async sendFriendRequest(req: Request, res: Response) {
    try {
      const senderId = parseInt(req.userId)
      const { receiverId } = Validators.sendFriendRequestSchema.parse(req.body)

      const notification = await NotificationService.sendFriendRequest(
        receiverId,
        senderId,
      )

      res.json(notification)
    } catch (error) {
      handleError(res, error)
    }
  }

  async acceptFriendRequest(req: Request, res: Response) {
    try {
      const notificationId = parseInt(req.params.id)
      const userId = parseInt(req.userId)

      const notification = await NotificationService.acceptFriendRequest(
        notificationId,
        userId,
      )

      res.json(notification)
    } catch (error) {
      handleError(res, error)
    }
  }

  async rejectFriendRequest(req: Request, res: Response) {
    try {
      const notificationId = parseInt(req.params.id)
      const userId = parseInt(req.userId)

      const notification = await NotificationService.rejectFriendRequest(
        notificationId,
        userId,
      )

      res.json(notification)
    } catch (error) {
      handleError(res, error)
    }
  }
}

export default new NotificationController()
