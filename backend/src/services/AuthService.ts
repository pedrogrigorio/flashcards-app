import { compare } from 'bcryptjs'
import UserRepository from '../repositories/UserRepository'
import { sign } from 'jsonwebtoken'

class AuthService {
  async login(email: string, password: string) {
    const user = await UserRepository.findUserByEmail(email)
    if (!user) {
      throw new Error('Email not found.')
    }

    const isValidPassword = await compare(password, user.password)

    if (!isValidPassword) {
      throw new Error('Invalid password.')
    }

    const SECRET_KEY = process.env.SECRET_KEY

    if (!SECRET_KEY) {
      throw new Error('Secret key not provided')
    }

    const token = sign({ id: user.id }, SECRET_KEY, {
      expiresIn: '30d',
    })

    return token
  }
}
export default new AuthService()
