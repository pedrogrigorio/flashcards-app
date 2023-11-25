import { Request, Response } from 'express'
import AuthService from '../services/AuthService'

class AuthController {
  async login(req: Request, res: Response) {
    const { email, password } = req.body

    try {
      const token = await AuthService.login(email, password)
      res.json(token)
    } catch (error) {
      if (error instanceof Error) {
        return res.status(400).json({ error: error.message })
      }

      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }
}

export default new AuthController()
