import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import FriendController from '../controllers/FriendController'

const friendRoutes = Router()

friendRoutes.delete(
  '/friends/:id',
  AuthMiddleware,
  FriendController.deleteFriend,
)

friendRoutes.get('/friends/:id', AuthMiddleware, FriendController.getFriendById)

export default friendRoutes
