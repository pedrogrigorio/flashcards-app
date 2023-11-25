import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import UserController from '../controllers/UserController'

const userRoutes = Router()

userRoutes.get('/users/:id', AuthMiddleware, UserController.getUser)
userRoutes.get(
  '/users/:id/friends',
  AuthMiddleware,
  UserController.getAllFriends,
)

userRoutes.post('/users/register', UserController.register)
userRoutes.post('/users/search', AuthMiddleware, UserController.searchUsers)

userRoutes.put(
  '/users/:id/profile',
  AuthMiddleware,
  UserController.updateProfile,
)
userRoutes.put(
  '/users/:id/day-streak',
  AuthMiddleware,
  UserController.updateUserDayStreak,
)
userRoutes.put(
  '/users/:id/cards-reviewed',
  AuthMiddleware,
  UserController.updateUserCardsReviewed,
)

export default userRoutes
