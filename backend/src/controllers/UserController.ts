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
      res.json(user)
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
    const newData = req.body

    try {
      const updatedUser = await UserService.updateUser(
        userId,
        authenticatedUserId,
        newData,
      )

      return res.json(updatedUser)
    } catch (error) {
      if (error instanceof Error) {
        return res.status(400).json({ error: error.message })
      }

      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }
}

export default new UserController()
