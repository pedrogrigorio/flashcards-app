import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'
import FriendController from '../controllers/FriendController'

const friendsRoutes = Router()

friendsRoutes.delete(
  '/friends/:id',
  AuthMiddleware,
  FriendController.deleteFriend,
)

friendsRoutes.get('/friends/:id', FriendController.getFriendById)

export default friendsRoutes
