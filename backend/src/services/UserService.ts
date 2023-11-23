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
    return await UserRepository.findUserById(userId)
  }

  async updateProfile(
    userId: number,
    authenticatedUserId: number,
    newData: { name: string; imgSrc: string },
  ) {
    if (userId !== authenticatedUserId) {
      throw new Error('Unauthorized: You cannot modify other user data.')
    }

    return await UserRepository.updateProfile(
      userId,
      newData.name,
      newData.imgSrc,
    )
  }

  async searchUsers(query: string) {
    return await UserRepository.searchUsers(query)
  }
}

export default new UserService()
