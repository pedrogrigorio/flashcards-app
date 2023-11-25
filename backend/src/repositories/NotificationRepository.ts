import NotificationStatus from '../enum/NotificationStatus'
import NotificationType from '../enum/NotificationType'
import { prisma } from '../lib/prisma'

class NotificationRepository {
  async createNotification(
    type: NotificationType,
    receiverId: number,
    senderId?: number,
    status?: NotificationStatus,
  ) {
    const notification = await prisma.notification.create({
      data: {
        type,
        status,
        receiverId,
        senderId,
      },
    })

    return notification
  }

  async updateNotificationStatus(
    notificationId: number,
    status: NotificationStatus,
  ) {
    const notification = await prisma.notification.update({
      where: {
        id: notificationId,
      },
      data: {
        status,
      },
    })

    return notification
  }
}

export default new NotificationRepository()
