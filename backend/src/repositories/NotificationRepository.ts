import NotificationStatus from '../constants/NotificationStatus'
import NotificationType from '../constants/NotificationType'
import { prisma } from '../lib/prisma'

class NotificationRepository {
  async createNotification(
    type: NotificationType,
    status: NotificationStatus,
    receiverId: number,
    senderId?: number,
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

  async findNotificationById(notificationId: number) {
    const notification = await prisma.notification.findUnique({
      where: {
        id: notificationId,
      },
    })

    return notification
  }

  async updateStatus(notificationId: number, status: NotificationStatus) {
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

  async getAllNotifications(userId: number) {
    const notifications = await prisma.notification.findMany({
      where: {
        receiverId: userId,
      },
    })

    return notifications
  }

  async deleteNotification(notificationId: number) {
    const notification = await prisma.notification.delete({
      where: {
        id: notificationId,
      },
    })

    return notification
  }
}

export default new NotificationRepository()
