import { Request, Response } from 'express'
import UserService from '../services/UserService'
import { z } from 'zod'

class UserController {
  async register(req: Request, res: Response) {
    const bodySchema = z.object({
      username: z.string(),
      email: z.string().email(),
      password: z.string(),
      passwordConfirmation: z.string(),
    })

    const userData = bodySchema.parse(req.body)

    try {
      const user = await UserService.register(userData)
      return res.json(user)
    } catch (error) {
      if (error instanceof Error) {
        return res.status(400).json({ error: error.message })
      }

      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async getUser(req: Request, res: Response) {
    const userId = parseInt(req.params.id)
    const user = await UserService.getUser(userId)

    return res.json(user)
  }

  async updateProfile(req: Request, res: Response) {
    const userId = parseInt(req.params.id)
    const authenticatedUserId = parseInt(req.userId)
    const bodySchema = z.object({
      name: z
        .string()
        .min(2, { message: 'Name must contain at least 2 character(s).' }),
      imgSrc: z.string(),
    })

    try {
      const newData = bodySchema.parse(req.body)
      const updatedUser = await UserService.updateProfile(
        userId,
        authenticatedUserId,
        newData,
      )

      return res.json(updatedUser)
    } catch (error) {
      if (error instanceof z.ZodError) {
        return res.status(400).json({ error: error.errors[0].message })
      } else if (error instanceof Error) {
        return res.status(400).json({ error: error.message })
      }

      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async updateUserDayStreak(req: Request, res: Response) {
    const userId = parseInt(req.params.id)
    const authenticatedUserId = parseInt(req.userId)
    const { dayStreak } = req.body

    try {
      const updatedUser = await UserService.updateUserDayStreak(
        userId,
        authenticatedUserId,
        dayStreak,
      )

      return res.json(updatedUser)
    } catch (error) {
      if (error instanceof Error) {
        return res.status(400).json({ error: error.message })
      }

      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async updateUserCardsReviewed(req: Request, res: Response) {
    const userId = parseInt(req.params.id)
    const authenticatedUserId = parseInt(req.userId)
    const { cardsReviewed } = req.body

    try {
      const updatedUser = await UserService.updateUserCardsReviewed(
        userId,
        authenticatedUserId,
        cardsReviewed,
      )

      return res.json(updatedUser)
    } catch (error) {
      if (error instanceof Error) {
        return res.status(400).json({ error: error.message })
      }

      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async searchUsers(req: Request, res: Response) {
    const { query } = req.body
    const users = await UserService.searchUsers(query)

    return res.json(users)
  }

  async getAllFriends(req: Request, res: Response) {
    const userId = parseInt(req.params.id)
    const friends = await UserService.getAllFriends(userId)

    return res.json(friends)
  }
}

export default new UserController()
