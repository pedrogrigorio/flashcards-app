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

  async getAllUsers(req: Request, res: Response) {
    const users = await UserService.getAllUsers()

    return res.json(users)
  }
}

export default new UserController()
