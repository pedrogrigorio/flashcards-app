import { Router } from 'express'
import userRoutes from './user'
import authRoutes from './auth'
import friendRoutes from './friends'

const routes = Router()
routes.use(userRoutes)
routes.use(authRoutes)
routes.use(friendRoutes)

export default routes
