import { Router } from 'express'
import userRoutes from './user'
import authRoutes from './auth'
import friendRoutes from './friends'
import deckRoutes from './deck'
import notificationRoutes from './notification'

const routes = Router()
routes.use(userRoutes)
routes.use(authRoutes)
routes.use(friendRoutes)
routes.use(notificationRoutes)
routes.use(deckRoutes)

export default routes
