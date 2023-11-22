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
      throw new Error('Passwords not match')
    }

    let user = await UserRepository.findByUsername(username)
    if (user) {
      throw new Error('Username already taken')
    }

    user = await UserRepository.findByEmail(email)
    if (user) {
      throw new Error('Email already in use')
    }

    const hashPassword = await hash(password, 8)
    user = await UserRepository.createUser(username, email, hashPassword)

    return user
  }
}
export default new UserService()
