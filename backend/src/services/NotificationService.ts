import NotificationStatus from '../constants/NotificationStatus'
import NotificationType from '../constants/NotificationType'
import NotificationRepository from '../repositories/NotificationRepository'
import UserRepository from '../repositories/UserRepository'
import FriendService from './FriendService'

class NotificationService {
  async sendFriendRequest(receiverId: number, senderId: number) {
    if (receiverId === senderId) {
      throw new Error('You cannot send an notification to yourself.')
    }

    const sender = await UserRepository.findUserById(senderId)

    if (!sender) {
      throw new Error('Sender not found')
    }

    const notification = await NotificationRepository.createNotification(
      NotificationType.FriendRequest,
      NotificationStatus.Pending,
      receiverId,
      sender.name,
      senderId,
      sender.imgSrc,
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

      const text = 'Friend request accepted'

      await NotificationRepository.createNotification(
        NotificationType.FriendRequestAccepted,
        NotificationStatus.Accepted,
        notification.senderId,
        text,
        notification.receiverId,
      )

      return notification
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

  async getAllNotifications(userId: number) {
    return await NotificationRepository.getAllNotifications(userId)
  }

  async deleteNotification(notificationId: number, userId: number) {
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

    const deletedNotification =
      await NotificationRepository.deleteNotification(notificationId)

    return deletedNotification
  }
}

export default new NotificationService()
