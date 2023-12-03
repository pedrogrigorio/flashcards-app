import { Request, Response } from 'express'
import AuthService from '../services/AuthService'
import * as Validators from '../validators/auth'
import { handleError } from '../utils/errorHandler'

class AuthController {
  async login(req: Request, res: Response) {
    try {
      const { email, password } = Validators.loginSchema.parse(req.body)
      const authData = await AuthService.login(email, password)

      return res.json(authData)
    } catch (error) {
      handleError(res, error)
    }
  }
}

export default new AuthController()
