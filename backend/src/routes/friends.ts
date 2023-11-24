import { Router } from 'express'
import { AuthMiddleware } from '../middlewares/auth'

const friendsRoutes = Router()

friendsRoutes.get('/friends', AuthMiddleware)

export default friendsRoutes
