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

    const token = sign({ id: user.id }, process.env.SECRET, {
      expiresIn: '30d',
    })

    return token
  }
}
export default new AuthService()
