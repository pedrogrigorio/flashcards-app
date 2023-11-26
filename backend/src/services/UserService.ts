import { hash } from 'bcryptjs'
import UserRepository from '../repositories/UserRepository'

interface UserDataSchema {
  username: string
  email: string
  password: string
  passwordConfirmation: string
}

class UserService {
  async register(userData: UserDataSchema) {
    const { username, email, password, passwordConfirmation } = userData

    if (password !== passwordConfirmation) {
      throw new Error('Passwords not match.')
    }

    let user = await UserRepository.findUserByUsername(username)
    if (user) {
      throw new Error('Username already taken.')
    }

    user = await UserRepository.findUserByEmail(email)
    if (user) {
      throw new Error('Email already in use.')
    }

    const hashPassword = await hash(password, 8)
    user = await UserRepository.createUser(username, email, hashPassword)

    return user
  }

  async getUser(userId: number) {
    const user = await UserRepository.findUserById(userId)

    if (!user) {
      throw new Error('User not found')
    }

    return user
  }

  async updateProfile(userId: number, name: string, imgSrc: string) {
    const user = await UserRepository.findUserById(userId)

    if (!user) {
      throw new Error('User not found')
    }

    const updatedUser = await UserRepository.updateProfile(userId, name, imgSrc)

    return updatedUser
  }

  async updateStats(userId: number, cardsReviewed: number) {
    const user = await UserRepository.findUserById(userId)

    if (!user) {
      throw new Error('User not found')
    }

    // TO DO: logic to calculate dayStreak
    const dayStreak = 0
    const newCardsReviewedCount = user?.cardsReviewed + cardsReviewed

    const updatedUser = await UserRepository.updateStats(
      userId,
      dayStreak,
      newCardsReviewedCount,
    )

    return updatedUser
  }

  async searchUsers(query: string) {
    return await UserRepository.searchUsers(query)
  }
}

export default new UserService()
