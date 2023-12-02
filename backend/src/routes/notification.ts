import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import NotificationController from '../controllers/NotificationController'

const notificationRoutes = Router()

notificationRoutes.get(
  '/notifications',
  AuthMiddleware,
  NotificationController.getAllNotifications,
)

notificationRoutes.post(
  '/notifications',
  AuthMiddleware,
  NotificationController.sendFriendRequest,
)

notificationRoutes.put(
  '/notifications/:id/accept',
  AuthMiddleware,
  NotificationController.acceptFriendRequest,
)

notificationRoutes.put(
  '/notifications/:id/reject',
  AuthMiddleware,
  NotificationController.rejectFriendRequest,
)

notificationRoutes.delete(
  '/notifications/:id',
  AuthMiddleware,
  NotificationController.deleteNotification,
)

export default notificationRoutes
