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
      select: {
        id: true,
        username: true,
        name: true,
        imgSrc: true,
        dayStreak: true,
        cardsReviewed: true,
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
      select: {
        id: true,
        username: true,
        name: true,
        imgSrc: true,
        dayStreak: true,
        cardsReviewed: true,
      },
    })

    return user
  }

  async updateUserDayStreak(id: number, dayStreak: number) {
    const user = await prisma.user.update({
      where: {
        id,
      },
      data: {
        dayStreak,
      },
      select: {
        id: true,
        username: true,
        name: true,
        imgSrc: true,
        dayStreak: true,
        cardsReviewed: true,
      },
    })

    return user
  }

  async updateUserCardsReviewed(id: number, cardsReviewed: number) {
    const user = await prisma.user.update({
      where: {
        id,
      },
      data: {
        cardsReviewed,
      },
      select: {
        id: true,
        username: true,
        name: true,
        imgSrc: true,
        dayStreak: true,
        cardsReviewed: true,
      },
    })

    return user
  }

  async searchUsers(query: string) {
    const users = await prisma.user.findMany({
      where: {
        username: {
          contains: query,
        },
      },
      select: {
        id: true,
        username: true,
        name: true,
        imgSrc: true,
        dayStreak: true,
        cardsReviewed: true,
      },
    })

    return users
  }

  async getAllFriends(id: number) {
    const friends = await prisma.user.findMany({
      where: {
        id,
      },
      include: {
        friends: true,
      },
    })

    return friends
  }
}

export default new UserRepository()
