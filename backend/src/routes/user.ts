import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import UserController from '../controllers/UserController'

const userRoutes = Router()

// only for token authorization test
userRoutes.post('/users/register', UserController.register)
userRoutes.get('/users/:id', AuthMiddleware, UserController.getUser)
userRoutes.put('/users/:id', AuthMiddleware, UserController.updateProfile)
userRoutes.post('/users/search', AuthMiddleware, UserController.searchUsers)

export default userRoutes
