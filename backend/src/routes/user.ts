import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import UserController from '../controllers/UserController'

const userRoutes = Router()

userRoutes.get('/users/:id', AuthMiddleware, UserController.getUser)

userRoutes.post('/users/register', UserController.register)
userRoutes.post('/users/search', AuthMiddleware, UserController.searchUsers)

userRoutes.put(
  '/users/:id/profile',
  AuthMiddleware,
  UserController.updateProfile,
)
userRoutes.put('/users/:id/stats', AuthMiddleware, UserController.updateStats)

export default userRoutes
