import { Router } from 'express'
import userRoutes from './user'
import authRoutes from './auth'
import friendRoutes from './friends'
import deckRoutes from './deck'
import cardRoutes from './cards'
import notificationRoutes from './notification'
import highlightRoutes from './Highlight'

const routes = Router()
routes.use(userRoutes)
routes.use(authRoutes)
routes.use(friendRoutes)
routes.use(notificationRoutes)
routes.use(deckRoutes)
routes.use(cardRoutes)
routes.use(highlightRoutes)

export default routes
