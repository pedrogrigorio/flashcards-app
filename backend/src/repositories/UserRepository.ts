import { prisma } from '../lib/prisma'

class UserRepository {
  async createUser(username: string, email: string, password: string) {
    const user = await prisma.user.create({
      data: {
        username,
        email,
        password,
      },
    })

    return user
  }

  async findByUsername(username: string) {
    const user = await prisma.user.findUnique({
      where: {
        username,
      },
    })

    return user
  }

  async findByEmail(email: string) {
    const user = await prisma.user.findUnique({
      where: {
        email,
      },
    })

    return user
  }
}

export default new UserRepository()
