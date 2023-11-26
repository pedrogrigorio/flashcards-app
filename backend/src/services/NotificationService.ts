import NotificationStatus from '../constants/NotificationStatus'
import NotificationType from '../constants/NotificationType'
import NotificationRepository from '../repositories/NotificationRepository'
import FriendService from './FriendService'

class NotificationService {
  async sendFriendRequest(receiverId: number, senderId: number) {
    const notification = await NotificationRepository.createNotification(
      NotificationType.FriendRequest,
      NotificationStatus.Pending,
      receiverId,
      senderId,
    )

    return notification
  }

  async acceptFriendRequest(notificationId: number, userId: number) {
    const notificationExists =
      await NotificationRepository.findNotificationById(notificationId)

    if (!notificationExists) {
      throw new Error('Notification not found.')
    }

    if (userId !== notificationExists.receiverId) {
      throw new Error(
        'You do not have permission to perform this action on another user notification',
      )
    }

    const notification = await NotificationRepository.updateStatus(
      notificationId,
      NotificationStatus.Accepted,
    )

    if (notification.senderId) {
      FriendService.addFriend(notification.senderId, notification.receiverId)

      const responseNotification =
        await NotificationRepository.createNotification(
          NotificationType.FriendRequestAccepted,
          NotificationStatus.Accepted,
          notification.senderId,
          notification.receiverId,
        )

      return [notification, responseNotification]
    }
  }

  async rejectFriendRequest(notificationId: number, userId: number) {
    const notificationExists =
      await NotificationRepository.findNotificationById(notificationId)

    if (!notificationExists) {
      throw new Error('Notification not found.')
    }

    if (userId !== notificationExists.receiverId) {
      throw new Error(
        'You do not have permission to perform this action on another user notification',
      )
    }

    const notification = await NotificationRepository.updateStatus(
      notificationId,
      NotificationStatus.Rejected,
    )

    return notification
  }
}

export default new NotificationService()
