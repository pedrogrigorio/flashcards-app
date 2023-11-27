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

      return res.json(notification)
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

      return res.json(notification)
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

      return res.json(notification)
    } catch (error) {
      handleError(res, error)
    }
  }

  async getAllNotifications(req: Request, res: Response) {
    try {
      const userId = parseInt(req.userId)

      const notifications =
        await NotificationService.getAllNotifications(userId)

      return res.json(notifications)
    } catch (error) {
      handleError(res, error)
    }
  }

  async deleteNotification(req: Request, res: Response) {
    try {
      const notificationId = parseInt(req.params.id)
      const userId = parseInt(req.userId)

      await NotificationService.deleteNotification(notificationId, userId)

      return res.status(204).send()
    } catch (error) {
      handleError(res, error)
    }
  }
}

export default new NotificationController()
