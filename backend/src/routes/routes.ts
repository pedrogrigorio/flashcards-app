import { Router } from 'express'
import userRoutes from './user'
import authRoutes from './auth'

const routes = Router()
routes.use(userRoutes)
routes.use(authRoutes)

export default routes
