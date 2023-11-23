import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import UserController from '../controllers/UserController'

const userRoutes = Router()

// only for token authorization test
userRoutes.get('/users', AuthMiddleware, UserController.getAllUsers)

userRoutes.post('/users/register', UserController.register)

export default userRoutes
