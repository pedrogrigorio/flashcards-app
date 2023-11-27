import { Request, Response } from 'express'
import UserService from '../services/UserService'
import { handleError } from '../utils/errorHandler'
import * as Validators from '../validators/user'

class UserController {
  async register(req: Request, res: Response) {
    try {
      const userData = Validators.registerSchema.parse(req.body)
      await UserService.register(userData)

      return res.status(204).send()
    } catch (error) {
      handleError(res, error)
    }
  }

  async getUser(req: Request, res: Response) {
    try {
      const userId = parseInt(req.params.id)
      const user = await UserService.getUser(userId)

      return res.json(user)
    } catch (error) {
      handleError(res, error)
    }
  }

  async updateProfile(req: Request, res: Response) {
    try {
      const userId = parseInt(req.params.id)
      const file = req.file
      const { name } = Validators.updateProfileSchema.parse(req.body)

      const updatedUser = await UserService.updateProfile(userId, name, file)

      return res.json(updatedUser)
    } catch (error) {
      handleError(res, error)
    }
  }

  async updateStats(req: Request, res: Response) {
    try {
      const userId = parseInt(req.params.id)
      const { cardsReviewed } = Validators.updateStatsSchema.parse(req.body)
      const updatedUser = await UserService.updateStats(userId, cardsReviewed)

      return res.json(updatedUser)
    } catch (error) {
      handleError(res, error)
    }
  }

  async searchUsers(req: Request, res: Response) {
    try {
      const { query } = Validators.searchUsersSchema.parse(req.body)
      const users = await UserService.searchUsers(query)

      return res.json(users)
    } catch (error) {
      handleError(res, error)
    }
  }
}

export default new UserController()
