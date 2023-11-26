import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import FriendController from '../controllers/FriendController'

const friendRoutes = Router()

friendRoutes.get('/friends', AuthMiddleware, FriendController.getAllFriends)
friendRoutes.get('/friends/:id', AuthMiddleware, FriendController.getFriend)
friendRoutes.delete(
  '/friends/:id',
  AuthMiddleware,
  FriendController.deleteFriend,
)

export default friendRoutes
