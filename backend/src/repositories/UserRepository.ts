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

  async findUserByUsername(username: string) {
    const user = await prisma.user.findUnique({
      where: {
        username,
      },
    })

    return user
  }

  async findUserByEmail(email: string) {
    const user = await prisma.user.findUnique({
      where: {
        email,
      },
    })

    return user
  }

  async findUserById(id: number) {
    const user = await prisma.user.findUnique({
      where: {
        id,
      },
    })

    return user
  }

  async updateProfile(id: number, name: string, imgSrc: string) {
    const user = await prisma.user.update({
      where: {
        id,
      },
      data: {
        name,
        imgSrc,
      },
    })

    return user
  }
}

export default new UserRepository()
